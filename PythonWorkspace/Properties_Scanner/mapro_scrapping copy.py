from bs4 import BeautifulSoup as soup
from urllib.request import Request, urlopen
import tqdm

totalPages = 14
currentPage = 1
website_url = "https://www.maprorealestate.com"
url = "/en/properties/sale/?page={}&sort=pa&sort=pa"

header = "Title,Location,Price,Bed,Bath,Plot,Build Area,Link\n"
file = open("mapro_properties.csv","w")
file.write(header)

pbar = tqdm.tqdm(total=totalPages)

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
        
        #header = "Title,Location,Price,Bed,Bath,Plot,Build Area,Link\n"
        p_location,p_price,p_bed_count,p_bath_count,p_plot,p_build_area = ""
        p_data = title + ",{},{},{},{},{},{}," + link
        
        sub_req = Request(link, headers={'User-Agent': 'Mozilla/5.0'})
        property_webpage = urlopen(sub_req).read()
        property_page_soup = soup(property_webpage, "html.parser")

        table = property_page_soup.find("table", {"class":"property-page__features-table"})
        table_data = table.find_all("tr")
    
        for l in range(len(table_data)):
            feature = table_data[l].th.text.strip().lower().capitalize()
            feature_sub = table_data[l].td.text.strip()

            

        p_data = p_data.format(p_location,p_price,p_bed_count,p_bath_count,p_plot,p_build_area)
        file.write(p_data)
    pbar.update(1)
pbar.close()