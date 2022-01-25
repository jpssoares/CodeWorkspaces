
from auxfile import *
import random as r
import string

file = open("funcionarios.txt", "w")

alturas = [1.00,1.20,1.30,1.50,1.60,1.65,1.70,1.75,1.80,1.90,2.00]
sex = ["F","M"]
tipofunc = ["Seguranca", "Limpeza", "Cozinheiro", "Vendedor"]

for i in range(500):
    idPessoa = 51280000 + i
    idade = r.randint(18,100)
    altura = alturas[r.randint(0, len(alturas)-1)]
    sexo = sex[r.randint(0,1)]
    nomePessoa = get_random_name(sexo)
    
    funcao = tipofunc[r.randint(0,len(tipofunc)-1)]

    if (funcao == "Seguranca" or funcao == "Vendedor"):
        workplace = open("funcionarios_aventura/listaStores.txt", "r")
    else:
        workplace = open("funcionarios_aventura/listaRestaurants.txt", "r")
    
    line = workplace.readline()
    workplace.close()
    names = line.split(",")
    idLocal = names[r.randint(0,(len(names)-1))]

    file.write("insert into pessoas values({},\'{}\',{},{},\'{}\');\n".format(idPessoa, nomePessoa, idade, altura, sexo))
    file.write("insert into funcionarios values({},{},\'{}\',to_date({},'YYYY-MM-DD'));\n".format(idPessoa, idLocal,funcao, get_data_contrato()))