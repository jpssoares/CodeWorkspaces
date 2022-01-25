
def count_substring(string, sub_string):
    num = 0
    strcopy = string
    for i in range(len(strcopy)):
        index = string.find(sub_string)
        if(index !=-1):
            num = num + 1
            string = string[(index + 1):]
    return num

if __name__ == '__main__':
    #counts how many times a substring is present in the string
    string = input().strip()
    sub_string = input().strip()
    
    count = count_substring(string, sub_string)
    print(count)