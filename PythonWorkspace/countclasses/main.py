import os, os.path

basepath = "/Users/joaosoares/jps.soares@campus.fct.unl.pt - Google Drive/My Drive/miei/3º ano/1º Semestre/"
tpath = "/TEORICAS"
ppath = "/PRATICAS"

tCGI = basepath + "CGI" + tpath
pCGI = basepath + "CGI" + ppath
tES = basepath + "ES" + tpath
pES = basepath + "ES" + ppath
tIA = basepath + "IA" + tpath
pIA = basepath + "IA" + ppath
tRC = basepath + "RC" + tpath
pRC = basepath + "RC" + ppath

allpaths = [("tCGI",tCGI), ("pCGI",pCGI), ("tES",tES), ("pES",pES), ("tIA",tIA), ("pIA",pIA), ("tRC",tRC), ("pRC",pRC)]

def count_files(dir):
    return (len([name for name in os.listdir(dir) if os.path.isfile(os.path.join(dir, name))]))

if __name__ == '__main__':
    for path in allpaths:
        print(path[0] + ": " + str(count_files(path[1])) + " files")
