#include <iostream>
#include "Lista.h"



int main()
{
	Lista l1 = creare();
	Lista l2 = creare();
	tipar(l1);
	std::cout << "\n";
	tipar(l2);
	std::cout << "\n";

	PNod aux=new Nod;
	aux->urm = NULL;
	aux->e = 5;

	addElemEnd(l1, l1._prim, aux);
	tipar(l1);
	std::cout << "\n";
	concatenate(l1._prim, l2._prim);
	tipar(l1);
	std::cout << "\n";
	delete aux;
}
