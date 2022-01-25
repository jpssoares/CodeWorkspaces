/*
 ============================================================================
 Name        : pratica6ex31.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

int x;

int f(int a) { x = a + 1; return x; }
int g(int a) { x = a - 1; return x; }

int h (int a) { printf("%d\n", a); return a;}
int j (int a, int b) {return a+b;}

int main(void) {
	x = 10;
	int s1 =  f(x) + g(x); // 21
	x = 10;
	int s2 = g(x) + f(x); // 19

	printf("%d %d\n", s1, s2);

	printf("%d\n", j(h(1),h(2))); // 2 - 1 - 3
	return 0;
}
