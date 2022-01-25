/*
	Linguagens e Ambientes de Programação - Projeto de 2020/2021

	Imageomatic module body

largura maxima = 100 colunas
tab = 4 espaços
0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789

	Este ficheiro constitui apenas um ponto de partida para o
	seu trabalho. Todo este ficheiro pode e deve ser alterado
	à vontade, a começar por este comentário.


 IDENTIFICAÇÃO DOS AUTORES -
	Aluno 1: 57609, Joao Soares
	Aluno 2: 57498, Vincente Santos

Comentarios:

?????????????????????????
?????????????????????????
?????????????????????????
?????????????????????????
?????????????????????????
?????????????????????????

*/

#include "Imageomatic.h"



/*** TYPE Int2 ***/

/* More Int2 functions, in case you need them */




/*** TYPE Pixel ***/

/* More Pixel functions, in case you need them */




/*** TYPE Image ***/

void initialization(void)
{
	// This function is automatically called when the interpreter starts.
	// If you need to perform some initialization, this is the place
	// to write the initialization code.
}

Int2 imageCopy(Image img, Int2 n, Image res)
{
	Int2 i;
	for(i.y = 0; i.y < n.y; i.y ++)
	{
		for(i.x = 0; i.x < n.x; i.x ++)
		{
			res[i.x][i.y] = img[i.x][i.y];
		}
	}
	return n;
}

Int2 imageGrayscale(Image img, Int2 n, Image res)
{
	Int2 i;
	for(i.y = 0; i.y < n.y; i.y ++)
	{
		for(i.x = 0; i.x < n.x; i.x ++)
		{
			res[i.x][i.y] = pixelGray(pixelGrayAverage(img[i.x][i.y]));
		}
	}
	return n;
}

Pixel pixelNegative(Pixel p)
{
	return pixel(MAX_COLOR - p.red, MAX_COLOR - p.green, MAX_COLOR - p.blue);
}

Int2 imageNegative(Image img, Int2 n, Image res)
{
	Int2 i;
	for(i.y = 0; i.y < n.y; i.y ++)
	{
		for(i.x = 0; i.x < n.x; i.x ++)
		{
			res[i.x][i.y] = pixelNegative(img[i.x][i.y]);
		}
	}
	return n;
}

Int2 imageHalf(Image img, Int2 n, Image res)
{
	Int2 i, j;
	j.y = 0;
	for(i.y = 0; i.y < n.y; i.y += 2)
	{
		j.x = 0;
		for(i.x = 0; i.x < n.x; i.x += 2)
		{
			res[j.x ++][j.y] = img[i.x][i.y];
		}
		j.y ++;
	}
	return j;
}

int stringSplit(String s, String parts[], int maxParts);

char* colorInFile(String cor)
{
	FILE* filePointer = fopen(colorsFileName, "r");
	String line;
	while(fgets(line, MAX_STRING, filePointer))
	{
		String parts[2];
		stringSplit(line, parts, 2);

		if(strcmp(cor, parts[1]) == 0)
		{
			cor = parts[0];
			fclose(filePointer);
			return cor;
		}
	}

	fclose(filePointer);
	return "000000";
}

Int2 imagePaint(String cor, Int2 n, Image res)
{
	cor = colorInFile(cor);

	int red, green, blue;
	sscanf(cor, "%02x%02x%02x", &red, &green, &blue);

	Int2 i;
	for(i.y = 0; i.y < n.y; i.y ++)
	{
		for(i.x = 0; i.x < n.x; i.x ++)
		{
			res[i.x][i.y] = pixel(red, green, blue);
		}
	}

	return n;
}

Int2 imageRotation90(Image img, Int2 n, Image res)
{
	Int2 i;
	for(i.y = 0; i.y < n.x; i.y ++)
	{
		for(i.x = 0; i.x < n.y; i.x ++)
		{
			res[i.x][i.y] = img[i.y][n.y - i.x];
			res[n.x - i.y][i.x] = img[i.x][i.y];
		}
	}
	return int2(n.y, n.x);
}

int getFValues (int value, int factor){

	while (value>0){
		if (value%factor != 0)
			value--;
		else return value;
	}
	return value;
}

Pixel pixelPoster(Pixel p, int factor)
{
	return pixel(getFValues(p.red,factor),
			getFValues(p.green,factor),
			getFValues(p.blue,factor));
}

Int2 imagePosterize(Image img, Int2 n, int factor, Image res)
{
	int rfactor = pow(2,8-factor);

	Int2 i;
	for(i.y = 0; i.y < n.y; i.y ++)
	{
		for(i.x = 0; i.x < n.x; i.x ++)
		{
			res[i.x][i.y] = pixelPoster((img[i.x][i.y]),rfactor);
		}
	}
	return n;
}
double grayLevel(Int2 i, Int2 center)
{
	return (0.7 * MAX_COLOR) + (0.3 * sin(int2Distance(center, i) / 20) * MAX_COLOR);
}

Int2 imageDroplet(Int2 n, Image res)
{
	Int2 center = int2Half(n);
	Int2 i;
	for(i.y = 0; i.y < n.y; i.y ++)
	{
		for(i.x = 0; i.x < n.x; i.x ++)
		{
			res[i.x][i.y] = pixelGray(grayLevel(int2(i.x, i.y), center));
		}
	}
	return n;
}

Pixel maskPixel(Pixel p1, Pixel p2)
{
	return pixel(p1.red * p2.red / MAX_COLOR,
				p1.green * p2.green / MAX_COLOR,
				p1.blue * p2.blue / MAX_COLOR);
}


// pre: int2Equals(n1, n2)
Int2 imageMask(Image img1, Int2 n1, Image img2, Int2 n2, Image res)
{
	Int2 i;
	for(i.y = 0; i.y < n1.y; i.y ++)
	{
		for(i.x = 0; i.x < n1.x; i.x ++)
		{
			res[i.x][i.y] = maskPixel(img1[i.x][i.y], img2[i.x][i.y]);
		}
	}
	return n1;
}

Pixel pixelBlur(Image img, Int2 i, int nivel)
{
	Int2 j;
	int sumOfRed, sumOfGreen, sumOfBlue, count;
	sumOfRed = 0;
	sumOfGreen = 0;
	sumOfBlue = 0;
	count = 0;
	for(j.y = i.y - nivel; j.y < i.y + nivel; j.y ++)
	{
		for(j.x = i.x - nivel; j.x < i.x + nivel; j.x ++)
		{
			if(j.y >= 0 && j.x >= 0 && j.y <= i.y && j.x <= i.x)
			{
				count ++;
				sumOfRed += img[j.x][j.y].red;
				sumOfGreen += img[j.x][j.y].green;
				sumOfBlue += img[j.x][j.y].blue;
			}
		}
	}

	return pixel(sumOfRed / count, sumOfGreen / count, sumOfBlue / count);
}



Int2 imageBlur(Image img, Int2 n, int nivel, Image res)
{
	Int2 i;
	for(i.y = 0; i.y < n.y; i.y ++)
	{
		for(i.x = 0; i.x < n.x; i.x ++)
		{
			res[i.x][i.y] = pixelBlur(img, i, nivel);
		}
	}

	return n;
}

Int2 imageFunctionPlotting(DoubleFun fun, int scale, Int2 n, Image res)
{
	n = imagePaint("white",n,res);
	Int2 origin = {n.x/2, n.y/2};

	int i,j,w;
	for(i = 0; i < n.y; i ++)
	{
		res[origin.x][i] = black;
	}
	for(j = 0; j < n.x; j ++)
	{
		res[j][origin.y] = black;
	}

	for(w = 0; w < n.x; w ++)
	{
		double realx = (double)(origin.x-w);
		double realy = -(fun(realx/scale)*scale);
		int wy = (double)(origin.y-realy);
		res[w][wy] = black;

	}

	return n;
}

Int2 imageOrderedDithering(Image img, Int2 n, Image res)
{
	#define INDEX_SIDE  8
	Byte indexMatrix[INDEX_SIDE][INDEX_SIDE] = {
		{ 0, 32,  8, 40,  2, 34, 10, 42},
		{48, 16, 56, 24, 50, 18, 58, 26},
		{12, 44,  4, 36, 14, 46,  6, 28},
		{60, 28, 52, 20, 62, 30, 54, 22},
		{ 3, 35, 11, 43,  1, 33,  9, 41},
		{51, 19, 59, 27, 49, 17, 57, 25},
		{15, 47,  7, 39, 13, 45,  5, 37},
		{63, 31, 55, 23, 61, 29, 53, 21}
	};

	Int2 i;
	for(i.y = 0; i.y < n.y; i.y ++)
	{
		for(i.x = 0; i.x < n.x; i.x ++)
		{

			if((pixelGrayAverage(img[i.x][i.y]) / 4.0) > (int) indexMatrix[i.x % 8][i.y % 8])
			{
				res[i.x][i.y] = white;
			}
			else
			{
				res[i.x][i.y] = black;
			}
		}
	}

	return n;
}

char getFirstChar6Bit(char c){
	switch(c){
	case '4':
		return '0';
	case '5':
		return '1';
	default:
		return c;
	}
}

int getDecimalFromHex(char* hex){

	int decimal = 0, base = 1;

	for(int i = 1; i >= 0; i--)
	{
	    if(hex[i] >= '0' && hex[i] <= '9')
	    {
	    	decimal += (hex[i] - 48) * base;
	    	base *= 16;
	    }
	    else if(hex[i] >= 'A' && hex[i] <= 'F')
	    {
	    	decimal += (hex[i] - 55) * base;
	    	base *= 16;
	    }
	}
	return decimal;
}
Pixel pixelSteganography(Pixel p, char c1, char c2){
	char b1 = getFirstChar6Bit(c1);

	char hex[2];
	hex[0]=b1; hex[1]=c2;
	int cvalue = getDecimalFromHex(hex);

	int red = (p.red & 252) | cvalue>>4;
	int green = (p.green & 252) | cvalue>>2;
	int blue = (p.blue & 252) | cvalue;

	return pixel(red,green,blue);
}
Int2 imageSteganography(Image img, Int2 n, String s, Image res)
{
	unsigned char strH[n.x * n.y];
	int i,j;
	bool end = false;

	memset(strH,0,sizeof(strH));

	for(i=0; s[i]!='\0'; i++)
	{
		if(s[i]>='a' && s[i]<='z')
	    	s[i] = s[i] - 32;
		if(s[i]=='@')
			s[i] = '?';
	}

	/*converte a string em Hex e adiciona a strH*/
	for(i=0,j=0;i<strlen(s);i++,j+=2){
		sprintf((char*)strH+j,"%02X",s[i]);
	}

	strH[j]='\0'; /*fim da frase*/

	Int2 z;
	int w = 0;
	for(z.y = 0; z.y < n.y; z.y ++)
	{
		for(z.x = 0; z.x < n.x; z.x ++)
		{
			if (!end){
				if (strH[w]!='\0'){
					res[z.x][z.y] = pixelSteganography((img[z.x][z.y]),strH[w],strH[w+1]);
					w=w+2;
				}
				else{
					res[z.x][z.y] = pixelSteganography((img[z.x][z.y]),'0','0');
					end = true;
				}
			} else{
				res[z.x][z.y] = img[z.x][z.y];
			}
		}
	}
	return n;
}



void imageTests(void)
{
	static Image img, img2, res;
	Int2 n;

	// Q
	n = imageLoad("img/frutos.png", img);
	n = imageGrayscale(img, n, res);
	imageStore("img/cinzento.png", res, n);

	// N
	n = imageLoad("img/frutos.png", img);
	n = imageNegative(img, n, res);
	imageStore("img/negativo.png", res, n);	

	// H
	n = imageLoad("img/frutos.png", img);
	n = imageHalf(img, n, res);
	imageStore("img/metade.png", res, n);

	// P
	n = int2(512, 512);
	n = imagePaint("green", n, res);
	imageStore("img/pintar.png", res, n);

	// R
	n = imageLoad("img/frutos.png", img);
	n = imageRotation90(img, n, res);
	imageStore("img/rotacao_90.png", res, n);

	// O
	n = imageLoad("img/frutos.png", img);
	n = imagePosterize(img, n, 3, res);
	imageStore("img/poster.png", res, n);

	// G
	n = int2(512, 512);
	n = imageDroplet(n, res);
	imageStore("img/goticula.png", res, n);

	// D
	n = imageLoad("img/frutos.png", img);
	n = imageBlur(img, n, 5, res);
	imageStore("img/desfocado.png", res, n);

	// M
	n = imageLoad("img/frutos.png", img);
	n = imageDroplet(n, img2);
	n = imageMask(img, n, img2, n, res);
	imageStore("img/mascarar.png", res, n);

	// F
	n = int2(512, 512);
	n = imageFunctionPlotting(sin, 50, n, res);
	imageStore("img/funcao.png", res, n);

	// T
	n = imageLoad("img/frutos.png", img);
	n = imageOrderedDithering(img, n, res);
	imageStore("img/matizacao.png", res, n);

	// E
	n = imageLoad("img/frutos.png", img);
	n = imageSteganography(img, n, "atacamos ao amanhecer", res);
	imageStore("img/esteganografia.png", res, n);
}

