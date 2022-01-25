import tkinter as tk

errormsg = "Invalid name, try again"
sucessmsg = "Project created sucessfully!"
class Application(tk.Frame):
    
    def create_project():
        INPUT = textbox.get("1.0","end-1c") 
	    print(INPUT)

    def __init__(self, master=None):
        super().__init__(master)
        self.master = master
        self.pack()
        self.create_widgets()

    def create_widgets(self):
        self.textbox = tk.Text(self, height = 2, width = 25, bg = "light cyan")
        self.textbox.pack(side="top")

        self.quit = tk.Button(self, text="Cancel", fg="red",
                              command=self.master.destroy)
        self.quit.pack(side="bottom")

        self.hi_there = tk.Button(self)
        self.hi_there["text"] = "Create project"
        self.hi_there["command"] = create_project
        self.hi_there.pack(side="bottom")


root = tk.Tk()
root.geometry("300x150")
root.title("Enter project name:")
app = Application(master=root)
app.mainloop()