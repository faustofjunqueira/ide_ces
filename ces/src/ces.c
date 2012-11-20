#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ces.h"
#include "rom.h"

void printRegistradores(Processador p, char *st){
	char *buffer = (char*) malloc (sizeof(char) * 40*3.5);
	sprintf(buffer,"Registrador P: %hX\nRegistrador T: %hX\nResistrador C: %d\n----------------------\n",p.regP,p.regT,p.regC);
	strcat(st,buffer);
}

Processador *init_ces(){
	Processador *p = (Processador*) malloc (sizeof(Processador));
	p->regP = 0;
	p->regT = 0;
	p->regC = 1;
	return p;
}

short int *init_memory(int l){
	if(l > 0x3FF){
	short int *memory = (short int*) malloc (sizeof(short int)*l);
		memset(memory, 0, sizeof(short int)*l);
		init_ROM(memory);
		return memory;
	}else{
		printf("Aloque mais memoria para suportar a ROM\n");
		return NULL;
	}

}

void SUB(Processador* p,short int* memoria, unsigned short int posMemoria){
	p->regP++;
	if((p->regT = memoria[posMemoria] - p->regT) < 0)
		p->regC = 0;
	else
		p->regC = 1;
}

void LE(Processador* p, short int* memoria, unsigned short int posMemoria){
	p->regP++;
	p->regT = memoria[posMemoria];
}

void ESC(Processador* p, short int* memoria, unsigned short int posMemoria){
	p->regP++;
	memoria[posMemoria] = p->regT;
}

void DNP(Processador* p, unsigned short int posMemoria){
	if(p->regC == 1)
		p->regP = posMemoria;
	else
		p->regP++;
}

char *exec(Processador* p, short int* memoria, unsigned short int org, unsigned short int EOP){
	unsigned short int i = 0;
	p->regP = org;
	char* out = (char*) malloc (sizeof(char)*(40*3.5*(EOP+1)));
	while(i<EOP){
		switch(((memoria[p->regP]>>14)-0xFFFFFFFC)%4){
			case 0:
				LE(p,memoria,memoria[p->regP]);
				break;
			case 1:
				ESC(p,memoria,memoria[p->regP]-0x4000);
				break;
			case 2:
				SUB(p,memoria,memoria[p->regP]-0x8000);
				break;
			case 3:
				DNP(p,memoria[p->regP]-0xc000);
				break;
		}
		printRegistradores(*p, out);
		i++;		
	}
	char *buf = (char*) malloc (sizeof(char)*16);
	sprintf(buf,"%0.4hX%0.4hX%d\n",p->regP,p->regT,p->regC);
	strcat(out,buf);
	return out;
}