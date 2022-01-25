import xlsxwriter

# Create an new Excel file and add a worksheet.
workbook = xlsxwriter.Workbook('makeup_exel/output.xlsx')
worksheet = workbook.add_worksheet()

worksheet.set_column('A:A', 50)
worksheet.set_column('B:A', 50)
worksheet.set_column('C:A', 50)
worksheet.set_column('D:A', 15)
worksheet.set_column('E:A', 50)


worksheet.write(0,0,"Produto")
worksheet.write(0,1,"Recomendado por")
worksheet.write(0,2,"Titulo video")
worksheet.write(0,3,"Data video")
worksheet.write(0,4,"Link video")

file = open("/Users/joaosoares/Code/PythonWorkspace/makeup_exel/Makeup Recommendations.txt", "r")

def getInfo(line):
    try:
        index1 = line.index("(")
        index2 = line.index(")")
    except:
        name=title=date="ERRO"
    else:
        name = line[1+index1:index2]
        title= line[:index1-1]
        date = line[2+index2:index2+12]

    return name, title, date

lines = file.readlines()

row=0
col=0

tempname = ""
temptitle = ""
tempdate = ""
templink = "NO LINK AT THE MOMENT"

for line in lines:
    if line[0] != "*":
        tempname, temptitle, tempdate = getInfo(line)
        row+=1
    else:
        worksheet.write(row,col,line[1:])
        worksheet.write(row,col+1,tempname)
        worksheet.write(row,col+2,temptitle)
        worksheet.write(row,col+3,tempdate)
        worksheet.write(row,col+4,templink)

workbook.close()