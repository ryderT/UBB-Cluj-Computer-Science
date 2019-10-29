#pragma once
#include "Set.h"
#include "pch.h"


//unidirectional iterator for a container
typedef int TElem;

class SetIterator {
	friend class Set;
private:
	
	//Constructor receives a reference of the container.
	const Set& set;
	//after creation the iterator will refer to the first element of the container, or it will be invalid if the container is empty

	SetIterator(const Set& set);

	

	
	//contains a reference of the container it iterates over

	



	/* representation specific for the iterator*/
	


public:
	TElem current;
	TElem head;



	//sets the iterator to the first element of the container

	void first();



	//moves the iterator to the next element

	//throws exception if the iterator is not valid

	void next();



	//checks if the iterator is valid

	bool valid();



	//returns the value of the current element from the iterator

	// throws exception if the iterator is not valid

	TElem getCurrent();



};



