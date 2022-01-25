from os import write
import random as r
import string
from create_atractions import *
from auxfile import *

file = open("atracoes.txt", "w")
file2 = open("listaRes.txt", "w")
res = []

atractions = get_atractions()
tipos_atracao = ["\'Radical\'","\'Moderado\'","\'Criancas\'"]
alturas = [1.00,1.20,1.30,1.50,1.60,1.65]

for i in range(len(atractions)):
    atraction = atractions[i]
    idZona = get_zona_id(atraction[1])
    idLocal = 1000000+i 
    nomeLocal = get_printable_string(atraction[0])
    tipo = tipos_atracao[r.randint(0,2)]
    alturaMin = alturas[r.randint(0,5)]
    acessibilidade = r.randint(0,1)
    file.write("insert into locais values({},{},\'{}\');\n".format(idLocal, idZona, nomeLocal))
    file.write("insert into atracoes values({},{},{},{});\n".format(idLocal, tipo, acessibilidade, alturaMin))
    
    if (i <= (len(atractions)-2)):
        file2.write(str(idLocal) + ",")
    else:
        file2.write(str(idLocal))

