vowels_list = ["a", "e", "i", "o", "u", "A", "E", "I", "O", "U"]

#creates list of vowels and consoants
def letters_list(string):
    cons = []
    vowels = []
    for l in string:
        if(l in vowels_list):
            if (l not in vowels):
                vowels.append(l)
        else:
            if (l not in cons):
                cons.append(l)
    return(cons, vowels)

def get_score(string, substrings):
    score = 0
    for sub in substrings:
        score = score + count_occurences(string,sub)
    return score

#creates array with all substring starting with given letters
def subs_count(string, letters):
    substrings = []
    for l in letters:
        substrings.extend(subs_count_function(string[string.index(l):]))
    return substrings

def subs_count_function(string):
    substrings = []
    for i in range(len(string)):
        substrings.append(string[:i+1])
    return substrings

#counts how many times sub is in string
def count_occurences(string, sub):
    num = 0
    strcopy = string
    for i in range(len(strcopy)):
        index = string.find(sub)
        if(index !=-1):
            num = num + 1
            string = string[(index + 1):]
    return num

def minion_game(string):
    # your code goes here
    stuart_score = kevin_score = 0
    stuart_subs = kevin_subs = [] 
    starting_points = letters_list(string)
    print(starting_points)

    stuart_subs = subs_count(string, starting_points[0])
    kevin_subs = subs_count(string, starting_points[1])
    print(stuart_subs)
    print(kevin_subs)


    stuart_score = get_score(string, stuart_subs)
    kevin_score = get_score(string, kevin_subs)

    if(stuart_score < kevin_score):
        print("Kevin " + str(kevin_score))
    elif (stuart_score == kevin_score):
        print("Draw")
    else:
        print("Stuart " + str(stuart_score))

if __name__ == '__main__':
    s = input()
    minion_game(s)