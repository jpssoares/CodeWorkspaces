/*
 ============================================================================
 Name        : exercicio40.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
int a;      // variavel a-global
int b;      // variavel b-global

void p(void) { a = b; }

void f(int b) {   // parâmetro b-local
    int a = b;    // variável a-local
    p();   /* aqui */
}

int main(void) {
    a = 5; b = 6; f(7);
    printf("%d %d\n", a, b);
    return 0;
}

/*
 * 40.
 * a) Qual o output deste programa em C?
 *
 * Resposta:
 * 6 6 (porque a afetação void p() foi as variaveis globais)
 *------------------------------------------------------------
 * b) Qual seria o output deste programa se o C
 * 	utilizasse escopo dinâmico nas chamadas de funções?
 *
 * Resposta:
 * 5 6 (porque a afetação nao foi as variaveis globais,
 * 		afetando apenas as variaveis locais de void f())
 * -----------------------------------------------------------
 * 41 - No programa da pergunta 40, diga qual é ambiente
 * 	no ponto indicado pelo comentário: "aqui".
 *
 * Resposta:
 * A função f(int b) e as variaveis locais a e b, porque estas "tapam" as variaveis
 * globais com o mesmo nome.
 * -----------------------------------------------------------
 * 42 - No programa da pergunta 40, diga qual é o âmbito
 * da variável global "b".

 * Resposta:
 * O ambito inclui todo o programa à excessao da funcao f, porque existe lá uma variavel
 * local com o mesmo nome
 *
 */
