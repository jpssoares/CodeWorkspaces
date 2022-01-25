from bs4 import BeautifulSoup as soup
from urllib.request import Request, urlopen
#import tqdm

#totalPages = 14
totalPages = 1
currentPage = 1
#pbar = tqdm.tqdm(total=totalPages)
#file = open("mapro_properties.csv","w")
#file = open("mapro_property_list.txt", "w")
website_url = "https://www.maprorealestate.com"
url = "/en/properties/sale/?page={}&sort=pa&sort=pa"

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
        print(title  + "\nLink - " + link)