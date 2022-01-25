
symbol = ".|."

values = input().split(" ")
n = int(values[0])
m = int(values[1])
    
middlerow = int(n / 2)

for line in range(n):
    if (line == middlerow):
        print("WELCOME".center(m).replace(" ", "-"))
    else:
        if(line < middlerow):
            times = line * 2 +1
        else:
            times = (middlerow - (line - middlerow))*2 +1
        print((symbol*times).center(m).replace(" ", "-"))
