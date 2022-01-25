
values = input().split(" ")
value = int(values[0])
result = int(values[1])

f = lambda x: eval(input())
y = f(value)

print (y==result)