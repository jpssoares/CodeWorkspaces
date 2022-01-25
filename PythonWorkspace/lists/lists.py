if __name__ == '__main__':
    N = int(input())

    command = []
    values = []
    cmd = ""

    while(N > 0):
        command = input().strip().split(" ")
        cmd = command[0]
        if (cmd == "insert"):
            values.insert(int(command[1]),int(command[2]))
        if (cmd == "print"):
            print(values)
        if (cmd == "remove"):
            values.remove(int(command[1]))
        if (cmd == "append"):
            values.append(int(command[1]))
        if (cmd == "sort"):
            values.sort()
        if (cmd == "pop"):
            values.pop()
        if (cmd == "reverse"):
            values.reverse()

        N = N-1
    

        

    