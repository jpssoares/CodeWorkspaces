from bs4 import BeautifulSoup as soup
from urllib.request import Request, urlopen
import tqdm

totalPages = 5
currentPage = 1
pbar = tqdm.tqdm(total=totalPages)
file = open("names.txt", "w")

url = "https://www.worten.pt/gaming/playstation/jogos?page="
titles = []

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
        title = container.div.div.h3.text
        titles.append(title)
    pbar.update(1)
pbar.close()    
titles.sort()
    
for j in range(len(titles)):
    if(j != (len(titles)-1)):
        file.write(titles[j] + "\n")
    else:
        file.write(titles[j])