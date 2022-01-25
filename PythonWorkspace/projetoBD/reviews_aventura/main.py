
from auxfile import *
import random as r
import string

file = open("reviews1.txt", "w")
listaR = open ("listaRestaurants.txt","r")
listaS = open ("listaStores.txt","r")

lineR = listaR.readline()
lineS = listaS.readline()
listaR.close()
listaS.close()
namesR = lineR.split(",")
namesS = lineS.split(",")

descricoes = ["Horrivel!!!", "Mau!!!", "Podia ser melhor", "Foi bom!","Fantastico!!!"]

for i in range(250):
    idVisitante = r.randint(81250000,81250499)
    idLocal = namesR[r.randint(0,len(namesR)-1)]
    idAval = "add_aval_id.nextval"
    nota = r.randint(0,4)
    desc = descricoes[nota]
    file.write("insert into avaliacoes values({},{},{},{},\'{}\');\n".format("null",idLocal,idAval,(nota+1),desc))