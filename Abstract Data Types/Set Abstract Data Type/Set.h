#pragma once
#include "Iterator.h"


typedef int TElem;





class Set {

	friend class SetIterator;


private:
	
	/* representation of Set*/

	TElem *elems;
	int sizes;
	int capacity;




public:

	//implicit constructor

	Set(int capacity = 10);

	Set(const Set& v);
	


	//adds an element to the  set

	//if the element was added, the operation returns true, otherwise (if the element was already in the set) 

	//it returns false

	bool add(TElem e);



	//removes an element from the set

	//if the element was removed, it returns true, otherwise false

	bool remove(TElem e);



	//checks if an element is in the  set

	bool search(TElem elem);

	


	//returns the number of elements;

	int size();



	//checks if the set is empty

	bool isEmpty();



	//returns an iterator for the set

	SetIterator iterator();
	
	TElem getElem(TElem pos);

	//destructor

	~Set();
private:
	//Resizes the Set, multiplyin the capacity by it's given factor
	void resize(double factor = 2);




};










