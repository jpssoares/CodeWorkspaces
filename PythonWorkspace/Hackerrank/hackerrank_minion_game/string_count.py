vowels_list = ["A", "E", "I", "O", "U"]

def get_subs(string):
    vcount = 0
    ccount = 0

    for i in range(len(string)):
        for j in range(len(string[i:])):
                word = string[i:i+j+1]
                if(word[0] in vowels_list):
                    vcount = vcount + 1
                else:
                    ccount = ccount + 1
    print(vcount)
    print(ccount)
                
                
if __name__ == '__main__':
    get_subs("BANANA")
