import pandas as pd
from pandas.core.frame import DataFrame

auto = pd.read_excel("Autos.xlsx")
df = pd.DataFrame(auto)

#print table
print(auto)

#print number of rows
print("Number of cars: "+ str(auto.shape[0]))

ef = df[df["Potencia"]<=95]

print(ef)

marcas = auto["Marca"]


print(marcas)