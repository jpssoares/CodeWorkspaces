from auxfile import *

file1 = open("nomes1.txt", encoding="utf8")
file2 = open ("nomesM.txt", "w")

# Get next line from file
line = file1.readline()
 
# if line is empty
# end of file is reached
names = line.split(",")

for name in names:
    print("1000" + name)
 
file1.close() 
file2.close()
