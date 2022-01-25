
from create_stores import get_stores
from auxfile import *
import random as r
import string

file = open("stores.txt", "w")
file2 = open("storeIds.txt", "w")
file3 = open("listaStores.txt", "w")
res = []

stores = get_stores()

Souveniers = []
Roupa = []
Fotografias = []
Artesanato = []

for i in range(len(stores)):
    loja = stores[i]
    idZona = get_zona_id(loja[1])
    idLocal = 1456000+i
    nomeLocal = get_printable_string(loja[0])
    tipoLoja = get_store_type(nomeLocal)
    file.write("insert into locais values({},{},\'{}\');\n".format (idLocal,idZona, nomeLocal))
    file.write("insert into lojas values({},\'{}\');\n".format(idLocal, tipoLoja))

    if (tipoLoja == "Souveniers"):
        Souveniers.append(idLocal)
    elif (tipoLoja == "Roupa"):
        Roupa.append(idLocal)
    elif (tipoLoja == "Artesanato"):
        Artesanato.append(idLocal)
    elif (tipoLoja == "Fotografias"):
        Fotografias.append(idLocal)
    
    if (i <= (len(stores)-2)):
        file3.write(str(idLocal) + ",")
    else:
        file3.write(str(idLocal))

file2.write("Souveniers = {}\n".format(Souveniers))
file2.write("Roupa = {}\n".format(Roupa))
file2.write("Fotografias = {}\n".format(Fotografias))
file2.write("Artesanato = {}\n".format(Artesanato))