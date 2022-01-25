import pygame
import random
from boardalgo import *

def createMatrix(w,h):
    matrix = [[0 for x in range(w)] for y in range(h)]
    return matrix

if __name__ == '__main__':
    print("How many blocks of width:")
    w = int(input())
    print("How many blocks of height:")
    h = int(input())

    if ((w * h) % 4 != 0):
        print("ERROR: invalid values")
    else:
        mat = createMatrix(w,h)
        pieces = (w * h) / 4
        
        n = 1
        while (n <= pieces):
            if (getFirstSlot(mat) == None):
                break
                
            x, y = getFirstSlot(mat)
            mat[y][x] = n
        
            mat, n = placeOtherSquares(mat,x,y,n)
            n = n+1

        printPuzzle(mat)