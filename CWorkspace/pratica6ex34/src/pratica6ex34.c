/*
 ============================================================================
 Name        : pratica6ex34.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <math.h>
#include <stdlib.h>

#define M_PI 3.14159265358979323846

typedef enum { LINE, CIRCLE, RECTANGLE, TRIANGLE} ShapeKind ;
typedef struct { double x, y; } Point;
typedef struct { Point p1, p2; } Line;
typedef struct { Point centre; double radius; } Circle;
typedef struct { Point top_left; double width, height; } Rectangle;
typedef struct { Point p1, p2, p3; } Triangle;//adicionado na aula

typedef struct {   // Isto é um tipo SOMA, programado com a ajuda duma UNION
    ShapeKind kind;
    int color;
    union {
        Line line;
        Circle circle;
        Rectangle rectangle;
        Triangle triangle;
    } u;
} Shape;

Point point(double x, double y) // construtor de pontos
{
    Point p = {x, y};
    return p;
}

Shape line(Point p1, Point p2, int color) // construtor de linhas
{
    Line l = {p1, p2};
    Shape s = {LINE, color};
    s.u.line = l;
    return s;
}

//codigo escrito em aula

Shape circle(Point centre, double radius, int color) // construtor de linhas
{
    Circle c = {centre, radius};
    Shape s = {CIRCLE, color};
    s.u.circle = c;
    return s;
}

Shape rectangle(Point top_left, double width, double height, int color){
	Rectangle r = {top_left, width, height};
	Shape s = {CIRCLE, color};
	s.u.rectangle = r;
	return s;
}

Shape triangle(Point p1, Point p2, Point p3, int color){
	Triangle t = {p1, p2, p3};
	Shape s = {TRIANGLE, color};
	s.u.triangle = t;
	return s;
}

double area_of_triangle (Triangle t){
	//area do triangulo é igual ao (tamanho da base * altura)/2
	//base = dist(p1,p2)
	//altura = dist(p3, middlePoint(p1,p2))

	Point pa = t.p1;
	Point pb = t.p2;
	Point pc = t.p3;

	double base, altura;
	Point pm = {(pa.x + pb.x)/2, (pa.y + pb.y)/2};

	double a1 = pow((pa.x - pb.x),2);
	double b1 = pow((pa.y - pb.y),2);

	double a2 = pow((pm.x - pc.x),2);
	double b2 = pow((pm.y - pc.y),2);

	base = sqrt(a1 + b1);
	altura = sqrt (a2 + b2);

	return base*altura/2;
}
double area(Shape s){
	switch (s.kind){
	case LINE:
		return 0;
	case CIRCLE:
		return M_PI * s.u.circle.radius * s.u.circle.radius;
	case RECTANGLE:
		return s.u.rectangle.width * s.u.rectangle.height;
	case TRIANGLE:
		return area_of_triangle(s.u.triangle);
	default:
		return 0;
	}
}

//fim de codigo escrito em aula

// usar "gcc pratica6ex34.c -lm -o result" e ./result para testar
int main(void)
{
    Shape c = circle(point(0,0), 1, 99);
    printf("%f\n", area(c)); //3.141593
    Shape t = triangle(point(0,0), point(5,10), point(10,5), 99);
    printf("%f\n", area(t)); //41.926275
    return 0;
}

