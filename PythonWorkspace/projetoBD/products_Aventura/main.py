
from get_products import get_products_list
from auxfile import *
import random as r
import string

file = open("artigos.txt", "w")

"""
Souveniers = [1456000, 1456005, 1456015, 1456027]
Roupa = [1456001, 1456004, 1456018, 1456020, 1456025]
Fotografias = [1456003, 1456006, 1456010, 1456011, 1456016, 1456019, 1456021, 1456022, 1456023, 1456024, 1456028, 1456029]
Artesanato = [1456002, 1456007, 1456008, 1456009, 1456014]
"""
#FALTA AQUI OS STOREIDS
Souveniers = [1456001, 1456002, 1456007, 1456012, 1456014, 1456017, 1456027]
Roupa = [1456005, 1456008, 1456025, 1456026]
Fotografias = [1456003, 1456006, 1456010, 1456011, 1456016, 1456019, 1456021, 1456022, 1456023, 1456024, 1456028, 1456029]
Artesanato = [1456009, 1456013, 1456020]

#tipos = ["Souveniers","Roupa", "Fotografias", "Artesanato"]

def get_loja(tipoLoja):
    if (tipoLoja == "Roupa"):
        return Roupa[r.randint(0,(len(Roupa)-1))]
    elif (tipoLoja == "Artesanato"):
        return Artesanato[r.randint(0,(len(Artesanato)-1))]
    elif (tipoLoja == "Fotografias"):
        return Fotografias[r.randint(0,(len(Fotografias)-1))]
    else:
        return Souveniers[r.randint(0,(len(Souveniers)-1))]

products = get_products_list()

for i in range(len(products)):
    product = products[i]
    idArtigo = 1234000+i
    nomeArtigo = get_printable_string(product[0])
    preco = product[1]
    preco = preco[1:]
    tipoProd = get_tipo_artigo(nomeArtigo)
    idLoja = get_loja(tipoProd)
    file.write("insert into artigos values({},\'{}\',{});\n".format(idArtigo, nomeArtigo, preco))
    file.write("insert into lembrancas values({},\'{}\',{});\n".format(idArtigo, tipoProd, idLoja))
    