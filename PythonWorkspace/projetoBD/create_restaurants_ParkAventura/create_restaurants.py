
from bs4 import BeautifulSoup as soup
from urllib.request import Request, urlopen

def get_restaurants():
    url = "https://www.portaventuraworld.com/en/restaurants"

    req = Request(url, headers={'User-Agent': 'Mozilla/5.0'})
    webpage = urlopen(req).read()

    #html parsing
    page_soup = soup(webpage, "html.parser")

    #grabs each product
    containers = page_soup.findAll("div", {"class":"text-inside-card"})

    totalContainers = len(containers)-2
    restaurants = []

    for i in range(totalContainers):
        container = containers[i]
        title = container.h4.text
        zone = container.div.span.text
        restaurants.append([title, zone])

    return restaurants
