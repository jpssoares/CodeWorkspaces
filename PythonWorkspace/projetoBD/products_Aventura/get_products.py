
from bs4 import BeautifulSoup as soup
from urllib.request import Request, urlopen

def get_products_list():
    url = "https://portaventurastore.com/en/products?page="
    products = []

    for i in range(25):
        my_url = url + str(i+1)
        req = Request(my_url, headers={'User-Agent': 'Mozilla/5.0'})
        webpage = urlopen(req).read()

        #html parsing
        page_soup = soup(webpage, "html.parser")

        #grabs each product
        containers = page_soup.findAll("div", {"class":"product-description"})

        totalContainers = len(containers)-2

        for i in range(totalContainers):
            container = containers[i]
            title = container.h1.a.text
            price = container.div.span.text
            products.append([title, price])

    return products
