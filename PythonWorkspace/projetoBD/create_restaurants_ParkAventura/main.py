import random as r
import string
from auxfile import *
from create_restaurants import *

file = open("restaurants.txt", "w")
file2 = open("listaRes.txt", "w")
res = []

restaurants = get_restaurants()
tipos_restaurante = ["\'Buffet\'","\'Table Service\'","\'Take Away\'"]

for i in range(len(restaurants)):
    restaurant = restaurants[i]
    idZona = get_zona_id(restaurant[1])
    idLocal = 1230000+i
    nomeLocal = get_printable_string(restaurant[0])
    tipo = tipos_restaurante[r.randint(0,2)]
    file.write("insert into locais values({},{},\'{}\');\n".format(idLocal, idZona, nomeLocal))
    file.write("insert into restaurantes values({},{},{},{},{});\n".format(idLocal, 0,0,0, tipo))

    if (i <= (len(restaurants)-2)):
        file2.write(str(idLocal) + ",")
    else:
        file2.write(str(idLocal))