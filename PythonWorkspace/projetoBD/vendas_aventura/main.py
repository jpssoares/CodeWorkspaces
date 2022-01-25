
from auxfile import *
import random as r
import string

file = open("sales.txt", "w")

for i in range(250):
    idVenda = "add_venda_id.nextval"
    idComp = r.randint(81250000,81250499)
    idFunc = r.randint(51280000,51280499)
    idArtigo = r.randint(1234000,1234245)
    data = "to_date({},'YYYY-MM-DD')".format(get_data_contrato())
    file.write("insert into vendas values({},{},{},{},{});\n".format(idVenda,idComp,idFunc,idArtigo,data))