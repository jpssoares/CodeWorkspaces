
import random as r
from auxfile import *

file = open("visitors.txt", "w")

alturas = [1.00,1.20,1.30,1.50,1.60,1.65,1.70,1.75,1.80,1.90,2.00]
sex = ["F","M"]
for i in range(500):
    idPessoa = 81250000 + i
    idade = r.randint(18,100)
    altura = alturas[r.randint(0, len(alturas)-1)]
    sexo = sex[r.randint(0,1)]
    nomePessoa = get_random_name(sexo)
    idVisitante = get_id_from_name(nomePessoa).lower() + str(i)

    file.write("insert into pessoas values({},\'{}\',{},{},\'{}\');\n".format(idPessoa, nomePessoa, idade, altura, sexo))
    file.write("insert into visitantes values({},\'{}\');\n".format(idPessoa, idVisitante))