/*
 ============================================================================
 Name        : pratica6ex32.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>

#define BLOCK_SIZE 32

typedef unsigned char Byte;
typedef Byte Block[BLOCK_SIZE];

void blockZero(Block b) {
    int i;
    for( i = 0 ; i < BLOCK_SIZE ; i++ )
        b[i] = 0;
}

void blockPrint(Block b) {
    int i;
    for( i = 0 ; i < BLOCK_SIZE ; i++ )
        printf("%02x ", b[i] & 0xFF);
    printf("\n");
}

void blockFill_char_uint_double(Block b, char c, unsigned int i, double d) {
    Byte *pt = b;
    *(char *)pt = c;
    pt += sizeof(char);
    *(unsigned int *)pt = i;
    pt += sizeof(unsigned int);
    *(double *)pt = d;
}

void blockPrint_char_uint_double(Block b) {
    Byte *pt = b;
    printf("%c\n", *(char *)pt);
    pt += sizeof(char);
    printf("%u\n", *(unsigned int *)pt);
    pt += sizeof(unsigned int);
    printf("%f\n", *(double *)pt); // %f é o valor comercial
    printf("%e\n", *(double *)pt); // %e é o vcalor cientifico
    printf("\n");
}

int main(void) {
    Block b;
    blockZero(b); blockPrint(b);
    blockFill_char_uint_double(b, 'a', ~0, 667.6e-22); blockPrint(b);
    blockPrint_char_uint_double(b);
    return 0;
}

