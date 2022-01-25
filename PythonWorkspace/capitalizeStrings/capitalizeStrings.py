#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the solve function below.
def solve(s):
    b = ""
    names = s.split(" ")
    for i in range (len(names) - 1):
        b = b + names[i].capitalize() + " "
    b = b + names[len(names) - 1].capitalize()
    return b

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()

    result = solve(s)

    fptr.write(result + '\n')

    fptr.close()
