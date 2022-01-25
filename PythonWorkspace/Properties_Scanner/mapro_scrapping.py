from bs4 import BeautifulSoup as soup
from urllib.request import Request, urlopen
import tqdm

totalPages = 10
currentPage = 1
website_url = "https://www.maprorealestate.com"
url = "/en/properties/sale/?page={}&sort=pa&sort=pa"

header = "Title,Location,Price,Bed,Bath,Plot,Built Area,Link\n"
p_location = p_price = p_bed_count = p_bath_count = p_plot = p_built_area = ""
file = open("Properties_Scanner/mapro_properties.csv","w")
file.write(header)

feature_names = ["Location","Price","Bedroom","Bathroom","Plot size","Built area"]
pbar = tqdm.tqdm(total=totalPages)

def define_location(string):
    global p_location
    p_location = string
def define_price(string):
    global p_price
    p_price = string
def define_bed_count(string):
    global p_bed_count
    p_bed_count = string
def define_bath_count(string):
    global p_bath_count
    p_bath_count = string
def define_plot(string):
    global p_plot
    p_plot = string
def define_built_area(string):
    global p_built_area
    p_built_area = string

switcher = {
        "Location": define_location,
        "Price": define_price,
        "Bedroom": define_bed_count,
        "Bathroom": define_bath_count,
        "Plot size": define_plot,
        "Built area": define_built_area
}

def define_features(arg1,arg2):
    func = switcher.get(arg1,"")
    # Execute the function
    if(feature_names.__contains__(arg1)):
        func(arg2)
    return

while(currentPage <= totalPages):
    my_url = website_url + url.format(str(currentPage))
    currentPage = currentPage + 1
    
    req = Request(my_url, headers={'User-Agent': 'Mozilla/5.0'})
    webpage = urlopen(req).read()

    #html parsing
    page_soup = soup(webpage, "html.parser")
    
    #grabs every property
    containers = page_soup.findAll("div", {"class":"property-list__details"})
    
      
    for container in containers:
        title = container.h5.a.text
        link = website_url + container.h5.a.get('href')
        
        p_location = p_price = p_bed_count = p_bath_count = p_plot = p_built_area = ""
        p_data = "\"" + title + "\",\"{}\",\"{}\",\"{}\",\"{}\",\"{}\",\"{}\"," + link + "\n"
        
        sub_req = Request(link, headers={'User-Agent': 'Mozilla/5.0'})
        property_webpage = urlopen(sub_req).read()
        property_page_soup = soup(property_webpage, "html.parser")

        table = property_page_soup.find("table", {"class":"property-page__features-table"})
        table_data = table.find_all("tr")
    
        for l in range(len(table_data)):
            feature = table_data[l].th.text.strip().lower().capitalize()
            feature_sub = table_data[l].td.text.strip().replace("-","_")
            define_features(feature, feature_sub)

        p_data = p_data.format(p_location,p_price,p_bed_count,p_bath_count,p_plot,p_built_area)
        file.write(p_data)
    pbar.update(1)
pbar.close()