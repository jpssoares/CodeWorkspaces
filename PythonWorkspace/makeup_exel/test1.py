
file = open("/Users/joaosoares/Code/PythonWorkspace/makeup_exel/input.txt", "r")
lines = file.readlines()

def getInfo(line):
    index1 = line.index("(")
    index2 = line.index(")")
    
    name = line[1+index1:index2]
    title= line[:index1-1]
    date = line[2+index2:index2+12]

    return name, title, date

print(getInfo(lines[0]))