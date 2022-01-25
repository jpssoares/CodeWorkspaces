from bs4 import BeautifulSoup as soup
from urllib.request import Request, urlopen
import tqdm

totalPages = 69
currentPage = 1
pbar = tqdm.tqdm(total=totalPages)
file = open("games_worten_ps4.csv", "w")

header = "Name,Price,Seller\n"

file.write(header)

url = "https://www.worten.pt/gaming/playstation/jogos?page="

while(currentPage <= totalPages):
    my_url = url + str(currentPage)
    currentPage = currentPage + 1
    
    req = Request(my_url, headers={'User-Agent': 'Mozilla/5.0'})
    webpage = urlopen(req).read()

    #html parsing
    page_soup = soup(webpage, "html.parser")

    #grabs each product
    containers = page_soup.findAll("div", {"class":"w-product__content"})

    totalContainers = len(containers)

    for i in range(totalContainers):
        container = containers[i]
        details = container.find("div", {"class":"w-product__details"})

        #grabs each title
        title = container.div.div.h3.text

        #grabs price
        main_price = details.find("span", {"class":"w-product-price__main"}).text
        dec_price = details.find("sup", {"class":"w-product-price__dec"}).text
        price = main_price + "," + dec_price

        #grabs seller name
        seller = details.find("div", {"class":"w-product__description-excerpt"}).span.text
        
        #prints to csv file
        file.write( "\"" + title + "\"" + "," + "\"" + price + "\"" + "," +  "\"" + seller +  "\"" + "\n")
    
    pbar.update(1)
pbar.close()
    