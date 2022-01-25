
vowels_list = ["A", "E", "I", "O", "U"]

def minion_game(string):
    # your code goes here
    vcount = 0
    ccount = 0

    for i in range(len(string)):
        count = len(string) - i
        if(string[i] in vowels_list):
            vcount = vcount + count
        else:
            ccount = ccount + count

    if(ccount < vcount):
        print("Kevin " + str(vcount))
    elif (ccount == vcount):
        print("Draw")
    else:
        print("Stuart " + str(ccount))

if __name__ == '__main__':
    s = input()
    minion_game(s)