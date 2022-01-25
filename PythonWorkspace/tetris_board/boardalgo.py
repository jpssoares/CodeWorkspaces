import pygame
import random
from boardprinter import printPuzzle

#return the coordinate of the first empty slot
def getFirstSlot(mat):
    for y in range(len(mat)):
        for x in range(len(mat[y])):
            if(mat[y][x] == 0):
                return x, y

#checks if it possible to put a piece on the slot on the left, and if it is it change the value to match
def checkLeft(mat,x,y):
    if (x == 0) or (mat[y][x-1] != 0):
        return mat, False, y ,x
    else:
        mat[y][x-1] = mat[y][x]
        return mat, True, y, x-1

#checks if it possible to put a piece on the slot on the right, and if it is it change the value to match
def checkRight(mat,x,y):
    if (x == (len(mat[y]) - 1)) or (mat[y][x+1] != 0):
        return mat, False, y ,x
    else:
        mat[y][x+1] = mat[y][x]
        return mat, True, y, x+1

#checks if it possible to put a piece on the slot bellow, and if it is it change the value to match
def checkDown(mat,x,y):
    if (y == (len(mat) - 1)) or (mat[y+1][x] != 0):
        return mat, False, y ,x
    else:
        mat[y+1][x] = mat[y][x]
        return mat, True, y+1, x

#removes the entire last piece and all the squares from the current piece,
#   by replacing the values of n and n-1 with 0
def removeLastPiece(mat, n):
    for m in range(len(mat)):
        for w in range(len(mat[0])):
            if (mat[m][w] == n) or (mat[m][w] == n-1):
                mat[m][w] = 0
    return mat, n-2


def placeOtherSquares(mat,x,y,n):
    for piece in range(3):
            res = False
            l = r = d = True
            while(res == False):
                
                if (l == False) and (r == False) and (d == False):
                    mat, n = removeLastPiece(mat, n)
                    return mat, n
                else:
                    func = random.randint(1,3)
                    if(func==1):
                        mat, res, y, x = checkLeft(mat,x,y)
                        l = False
                    elif(func == 2):
                        mat, res, y, x = checkRight(mat,x,y)
                        r = False
                    else:
                        mat, res, y, x = checkDown(mat,x,y)
                        d = False
    return mat, n

