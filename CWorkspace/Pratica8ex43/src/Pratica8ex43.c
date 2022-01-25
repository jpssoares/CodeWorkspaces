/*
 ============================================================================
 Name        : Pratica8ex43.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdbool.h>
#include <string.h>

bool copy(int nbytes, void *a, int acap, int aidx, void *b, int bcap, int bidx){
	if(aidx<0 || aidx>=acap || bidx<0 || bidx>=bcap)
		return false;

	memcpy(((char *)a)+aidx * nbytes,
		   ((char *)b)+bidx * nbytes,
			nbytes);

	return true;
}

int main(void)
{
    const int ASIZE = 10, BSIZE = 20;
    int a[ASIZE], b[BSIZE];
    copy(sizeof(int), a, ASIZE, 3, b, BSIZE, 6);  // a[3] = b[6]
    printf("ola");
    return 0 ;
}
