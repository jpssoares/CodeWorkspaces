#in decimal (ascii) a = 97 & z = 122
#ord(char c)
#chr(int x)

def printString(size, l, col):
    n = 0
    string = ""

    while(n<l):
        string = string + chr(96+size-n) + "-"
        n = n+1
    while(n>=0):
        string = string + chr(96+size-n)
        if (n!=0):
            string = string + "-"
        n = n-1
    print (string.center(col).replace(" ", "-"))


def print_rangoli(size):
    #firstl = chr(96+size)
    lines = size * 2 - 1
    middle = size - 1
    col = (middle)*4 +1
    
    for l in range(lines):
        if(l <= middle):
            printString(size, l, col)
        else:
            #times = (middlerow - (line - middlerow))*2 +1
            newl = (middle) - (l - middle)
            printString(size, newl, col)
                

if __name__ == '__main__':
    n = int(input())
    print_rangoli(n)