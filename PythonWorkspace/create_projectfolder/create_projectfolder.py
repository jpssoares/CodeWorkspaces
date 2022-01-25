from os import makedirs, listdir
from os.path import isdir, join
from shutil import move

mypath = "/Users/Biomh/Documents/PythonWorkspace/"

onlydirs = [f for f in listdir(mypath) if isdir(join(mypath, f))]

def create():
    print("Enter project name:")

    name = str(input())

    folder_path = mypath + name
    if name not in onlydirs:
        makedirs(folder_path)

        file_name = name + ".py"
        file = open(file_name, "w")
        file.write("\n\n\nif __name__ == '__main__':\n\t\n\t\n")
        file.close()
        file_path = mypath + file_name


        move(file_path, folder_path)
        return "Project sucessfully created!!!"
    else:
        print("Invalid name, try again")
        return create()

if __name__ == '__main__':
    print(create())