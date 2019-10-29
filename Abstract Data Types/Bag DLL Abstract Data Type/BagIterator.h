#pragma once
#include "Bag.h"



//unidirectional iterator for a container
class Bag;

class BagIterator {
	friend class Bag;
private:

	const Bag& b;
	Node* current;
	int currentF;
	BagIterator(const Bag& b);


	



public:



	//sets the iterator to the first element of the container

	void first();



	//moves the iterator to the next element

	//throws exception if the iterator is not valid

	void next();



	//checks if the iterator is valid

	bool valid() const;



	//returns the value of the current element from the iterator

	// throws exception if the iterator is not valid

	TElem getCurrent() const;



};



