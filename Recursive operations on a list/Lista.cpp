#include "lista.h"
#include <iostream>

using namespace std;


PNod creare_rec() {
	TElem x;
	cout << "x=";
	cin >> x;
	if (x == 0)
		return NULL;
	else {
		PNod p = new Nod();
		p->e = x;
		p->urm = creare_rec();
		return p;
	}
}

Lista creare() {
	Lista l;
	l._prim = creare_rec();
	return l;
}

void tipar_rec(PNod p) {
	if (p != NULL) {
		cout << p->e << " ";
		tipar_rec(p->urm);
	}
}

void tipar(Lista l) {
	tipar_rec(l._prim);
}

void distrug_rec(PNod p) {
	if (p != NULL) {
		distrug_rec(p->urm);
		delete p;
	}
}

void distruge(Lista l) {
	//se elibereaza memoria alocata nodurilor listei
	distrug_rec(l._prim);
}

void addElemEnd(Lista l, PNod root,PNod newNode)
{
	if (root->urm == NULL) {
		
		root->urm = newNode;
	}
	else {
		addElemEnd(l, root->urm, newNode);
	}
}

void concatenate(PNod root1, PNod root2) {
	if (root1->urm == NULL) {
		root1->urm = root2;
	}
	else {
		concatenate(root1->urm, root2);
	}
}