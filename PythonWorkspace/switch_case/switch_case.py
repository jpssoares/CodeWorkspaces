
# revenue for the first 6 months of the year
months = [0,0,0,0,0,0]

def jan(value):
    months [0] = value
def feb(value):
    months [1] = value
def mar(value):
    months [2] = value
def apr(value):
    months [3] = value
def may(value):
    months [4] = value
def jun(value):
    months [5] = value

def months_revenue(argument,value):
    switcher = {
        "January": jan,
        "February": feb,
        "March": mar,
        "April": apr,
        "May": may,
        "June": jun
    }
    func = switcher.get(argument, lambda: "Invalid month")
    # Execute the function
    return func(value)

#scrambled list with revenue for each month
reg_months = ["May", "June", "February", "March", "January", "April"]
reg_rev = [44,75,78,51,18,21]

def update_months():
    for i in range(len(reg_months)):
        month = reg_months[i]
        func = months_revenue(month,reg_rev[i])
        func

update_months()
print(months) #[18, 78, 51, 21, 44, 75]
