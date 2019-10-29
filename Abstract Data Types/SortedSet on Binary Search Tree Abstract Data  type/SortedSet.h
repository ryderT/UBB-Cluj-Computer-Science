#pragma once
#include "SortedSetIterator.h"
#include "BSTNode.h"


typedef int TElem;

typedef TElem TComp;



typedef bool(*Relation)(TComp, TComp);

class SortedSetIterator;

class SortedSet {
	friend class SortedSetIterator;

private:

	/*Representation of the SortedSet*/
	BSTNode *root;
	int nrElem;
	Relation r;

public:
	

	//constructor

	SortedSet(Relation r);



	//adds an element to the sorted set

	//if the element was added, the operation returns true, otherwise (if the element was already in the set) 

	//it returns false

	bool add(TComp e); //check





	//removes an element from the sorted set

	//if the element was removed, it returns true, otherwise false
	BSTNode *getMinimum(BSTNode *startingRoot);
	BSTNode *getMaximum(BSTNode *startingRoot);
	BSTNode *removeRecursive(BSTNode *rootNode, TComp e, bool &removed);
	bool remove(TComp e); //check



	//checks if an element is in the sorted set
	BSTNode *searchRecursive(BSTNode *rootNode,TElem e,bool &found);
	bool search(TElem elem) const; //check





	//returns the number of elements from the sorted set

	int size() const; //check



	//checks if the sorted set is empty

	bool isEmpty() const; //check



	//returns an iterator for the sorted set

	SortedSetIterator iterator();



	// destructor
	void postOrderDel(BSTNode *rootNode);
	~SortedSet();





};
