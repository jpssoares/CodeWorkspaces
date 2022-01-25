if __name__ == '__main__':
    n=int(raw_input())

    T=tuple([int(i) for i in raw_input().split()])

    print hash(T)