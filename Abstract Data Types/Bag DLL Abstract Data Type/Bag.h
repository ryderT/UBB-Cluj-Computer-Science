#pragma once
#include "Node.h"
#include "BagIterator.h"
//class BagIterator;

class Bag {
	friend class BagIterator;
private:
	Node *head;
	Node *tail;
	int len;
	int cap;
public:
	//constructor

	Bag();



	//adds an element to the bag

	void add(TElem e);



	//removes one occurrence of an element from a bag

	//returns true if an element was removed, false otherwise (if e was not part of the bag)

	bool remove(TElem e);



	//checks if an element appearch is the bag

	bool search(TElem e) const;



	//returns the number of occurrences for an element in the bag

	int nrOccurrences(TElem e) const;



	//returns the number of elements from the bag

	int size() const;



	//returns an iterator for this bag

	BagIterator iterator() const;



	//checks if the bag is empty

	bool isEmpty() const;

	//counts and returns the number of unique elemets
	int uniqueCount()const;

	//destructor

	~Bag();
};