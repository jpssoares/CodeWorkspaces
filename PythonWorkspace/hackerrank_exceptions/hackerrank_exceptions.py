
cases = int(input())
inputs = []
for i in range(cases):
    numbers =input().split(" ")
    inputs.append(numbers)

for j in range(len(inputs)):
    try:
        print(int(int(inputs[j][0])/int(inputs[j][1])))
    except ZeroDivisionError as e:
        print ("Error Code: integer division or modulo by zero")
    except ValueError as e:
        print ("Error Code:",e)
  