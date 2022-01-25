if __name__ == '__main__':
    string = input()
    
    length = len(string) + 1

    for i in range(length):
        for j in reversed(range(length)):
            if(j>=i+1) and (j <= length):
                if not (i == 0 and j == length):
                    print(string[i:j])
    

