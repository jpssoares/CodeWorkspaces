
if __name__ == '__main__':
    s = input()

    alphanum = "False"
    alphabet = "False"
    dig = "False"
    low = "False"
    upp = "False"

    l = list(s)

    for i in range(len(l)):
        if(l[i].isupper()):
            upp = "True"
        if(l[i].islower()):
            low = "True"
        if(l[i].isdigit()):
            dig = "True"
        if(l[i].isalpha()):
            alphabet ="True"
        if(l[i].isalnum()):
            alphanum ="True"
    
    print(alphanum)
    print(alphabet)
    print(dig)
    print(low)
    print(upp)