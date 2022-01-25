#!/bin/python3

import math
import os
import random
import re
import sys

def isWeird(n):
    if(n%2!=0):
        return "Weird"
    else:
        if(n>=2) and (n<=5):
            return "Not Weird"
        if(n>=6) and (n<=20):
            return "Weird"
        if(n>20):
            return "Not Weird"

if __name__ == '__main__':
    n = int(input().strip())
    
    print(isWeird(n))