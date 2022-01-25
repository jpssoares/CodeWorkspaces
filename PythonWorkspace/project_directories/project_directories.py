from os import listdir, makedirs
from os.path import isfile, join
from shutil import move
import subprocess

mypath = "/Users/Biomh/Documents/PythonWorkspace/"

onlyfiles = [f for f in listdir(mypath) if isfile(join(mypath, f))]

for str in onlyfiles:
    type = str[len(str)-2:]
    if (type == "py"):
        folder_name =str[:len(str)-3]
        makedirs(folder_name)
        file_path = mypath + str
        folder_path = mypath + folder_name
        move(file_path, folder_path)