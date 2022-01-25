def main():
    howto = "How many pages?"
    print(howto)
    pages = int(input())

    even = []
    odd = []
    even44 = []
    odd44 = []

    notodd = False

    for i in range(pages):
        if((i+1)%2==0):
            even.append(i+1)
        else:
            odd.append(i+1)
        
        if(notodd):
            even44.append(i+1)
        else:
            odd44.append(i+1)

        if ((i+1)%4==0):
            notodd = not notodd
    print("1 page:")
    print(" ODD")
    print (odd)
    print(" EVEN")
    print(even)

    print ("4 pages:")
    print(" ODD")
    print (odd44)
    print(" EVEN")
    print(even44)


main()

