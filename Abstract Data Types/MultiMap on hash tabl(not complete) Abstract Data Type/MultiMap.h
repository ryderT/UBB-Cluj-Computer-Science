#pragma once

#include<vector>
#include "MultiMapIterator.h"
#include<utility>
#include<vector>


using namespace std;


typedef int TKey;

typedef std::vector<int> TValue;



typedef std::pair<TKey, TValue> TElem;

class MultiMapIterator;

class MultiMap

{

	friend class MultiMapIterator;

private:

	/* representation of the MultiMap */
	TElem *table;
	int *next;
	int nrEl;
	int cap;
	int firstEmpty;

	
	void readjust();
	int hash(TKey c) const;



public:

	//constructor

	MultiMap();



	//adds a key value pair to the multimap

	void add(TKey c, TValue v);



	//removes a key value pair from the multimap

	//returns true if the pair was removed (if it was in the multimap) and false otherwise

	bool remove(TKey c, TValue v);



	//returns the vector of values associated to a key. If the key is not in the MultiMap, the vector is empty

	vector<TValue> search(TKey c) const;



	//returns the number of pairs from the multimap

	int size() const;



	//checks whether the multimap is empty

	bool isEmpty() const;



	//returns an iterator for the multimap

	MultiMapIterator iterator() const;



	//descturctor

	~MultiMap();





};


