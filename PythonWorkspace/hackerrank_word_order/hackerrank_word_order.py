# Enter your code here. Read input from STDIN. Print output to STDOUT

words = []
times = []

lines = int(input())

for l in range(lines):
    string = input()
    while True:
        try:
            i = words.index(string)
            times[i] = times[i] + 1
            break
        except ValueError:
            words.append(string)
            times.append(1)
            break
    
print(len(times))
result = ""

for j in range(len(times)):
   print(str(times[j]), end = ' ')