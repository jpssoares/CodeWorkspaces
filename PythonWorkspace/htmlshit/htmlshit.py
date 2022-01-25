from bs4 import BeautifulSoup as soup
from urllib.request import Request, urlopen

url = "https://www.worten.pt/gaming/playstation/jogos?page=1"
    
req = Request(url, headers={'User-Agent': 'Mozilla/5.0'})
webpage = urlopen(req).read()

#html parsing
page_soup = soup(webpage, "html.parser")

#grabs all products
containers = page_soup.findAll("div", {"class":"w-product__content"})

#grabs each product
container = containers[0]
details = container.find("div", {"class":"w-product__details"})

#grabs price
main_price = details.find("span", {"class":"w-product-price__main"}).text
dec_price = details.find("sup", {"class":"w-product-price__dec"}).text
price = main_price + "," + dec_price

#grabs seller name
seller = details.find("div", {"class":"w-product__description-excerpt"}).span.text
print(seller)
