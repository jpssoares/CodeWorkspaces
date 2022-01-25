from tkinter import *
from os import makedirs, listdir, chdir, startfile
from os.path import isdir, join
from shutil import move

invalidmsg = "Invalid name, try again..."
existsmsg = "Project already exists, try again..."
sucessmsg = "Project sucessfully created!!!"
exampleprogram = "\n\n\nif __name__ == '__main__':\n\tprint(\"Hello World!\")\n\t\n"


mypath = "d:/PythonWorkspace/"
onlydirs = [f for f in listdir(mypath) if isdir(join(mypath, f))]

root = Tk()
root.title("Enter project name:")
root.geometry("300x210")

def create(name):
    if name not in onlydirs:
        
        folder_path = mypath + name
        makedirs(folder_path)

        file_name = name + ".py"
        file_path = mypath + file_name
        file = open(file_path, "w")

        if(varHelloWorld.get() == 0):
            file.write("\n")
        else:
            file.write(exampleprogram)
        file.close()

        move(file_path, folder_path)

        chdir(folder_path)
        startfile(file_name)

        return sucessmsg
    else:
        return existsmsg

#Checks if string only contains valid characters
def is_ascii(s):
    for c in s:
        if not (ord(c) == 45 or ord(c) == 95 or 48<=ord(c)<=57 or 65<=ord(c)<=90 or 97<=ord(c)<=123):
            return False
        else:
            return True 

#Get text from the text box
def get_text():
    name = str(my_text.get("1.0",END)).strip()

    if not (is_ascii(name)):
        my_label.config(text= invalidmsg)
    else:
       my_label.config(text= create(name)) 

my_text = Text(root, height = 1, width = 25, bg = "light cyan")
my_text.pack(pady=20)

varHelloWorld = IntVar()
checkbox = Checkbutton(root, text="Include \"Hello World\" program", variable=varHelloWorld)
checkbox.pack(pady=10)

button_frame = Frame(root)
button_frame.pack()

create_button = Button(button_frame, text= "New Project", command = get_text)
create_button.grid(row = 0, column=0)

cancel_button = Button(button_frame, text= "Quit", command = root.destroy)
cancel_button.grid(row = 0, column=1)

my_label= Label(root, text='')
my_label.pack(pady=20)

root.mainloop()