
def split_and_join(line):
    n = line.split(" ")
    new = "-".join(n)
    return new

if __name__ == '__main__':
    line = input()
    result = split_and_join(line)
    print(result)