import random as r
import string
import unidecode

tiposLoja = ["Souveniers","Roupa", "Doces", "Artesanato"]

#for all
def get_printable_string(my_str):
    my_str = unidecode.unidecode(my_str)
    final_str = ''
    for char in my_str:
        if (char == "-" or char == "_" or char == "\'" or char == "™"
                or char == "®" or char == "´" or char == "`" or char == "’"
                or char == "“" or char == "”"):
            final_str += ''
        else:
            final_str += char
    return final_str

#for locais
def get_zona_id(zone):
    switcher = {
        "Mediterrània": 311,
        "China":312,
        "Polynesia":313,
        "México":314,
        "SésamoAventura":315,
        "Far West":316
    }
    ident = switcher.get(zone, lambda: "InvalidZone")
    return ident

def get_store_type(nome):
    if (nome.find("Foto") != -1 or nome.find("Photo") != -1):
        return "Fotografias"
    else:
        return tiposLoja[r.randint(0,3)]

#for products
def get_tipo_artigo(nome):
    if (nome.find("Foto") != -1 or nome.find("Photo") != -1):
        return "Fotografias"
    elif (nome.find("Men") != -1 or nome.find("Woman") != -1 or nome.find("Child") != -1 or nome.find("Boy") != -1 or
    nome.find("Girl") != -1 or nome.find("Cap") != -1 or nome.find("Hoodie") != -1 or nome.find("Top") != -1 or nome.find("T-Shirt") != -1):
        return "Roupa"
    elif (nome.find("Mug") != -1 or nome.find("Bowl") != -1 or nome.find("Cup") != -1 or nome.find("Bottles") != -1 or
    nome.find("Boxes") != -1 or nome.find("Canteen") != -1 or nome.find("Cushion") != -1 or nome.find("Jug") != -1 or
    nome.find("Blanket") != -1 or nome.find("Dish") != -1 or nome.find("Lunchbox") != -1 or nome.find("Jug") != -1):
        return "Artesanato"
    else:
        return "Souveniers"

#for people

def get_id_from_name(name):
    fchar = name[0]
    lchar = name[len(name)-2]
    return fchar + lchar

def get_random_name(sexo):
    name = ""
    if (ord(sexo) == 70 or ord(sexo) == 102):
        fileNames = open("visitors_aventura/nomesF.txt", "r")     
    else:
        fileNames = open("visitors_aventura/nomesM.txt", "r")
    
    line = fileNames.readline()
    fileNames.close()
    names = line.split(",")
    name = names[r.randint(0,(len(names)-1))]
    
    # 65 - A / 86 - V
    return name + " " + chr(r.randint(65,86)) + "."

def get_data_contrato():
    dia = str(r.randint(1,28))
    mes = str(r.randint(1,12))
    ano = str(r.randint(2000,2019))
    data = "\'{}-{}-{}\'".format(ano,mes,dia)
    return data

