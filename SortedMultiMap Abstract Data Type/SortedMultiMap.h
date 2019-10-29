//
// Created by Teodora Dan on 2019-04-12.
//

#ifndef DSALAB4_SORTEDMULTIMAP_H
#define DSALAB4_SORTEDMULTIMAP_H

#endif //DSALAB4_SORTEDMULTIMAP_H

#pragma once

#include <vector>
#include <utility>
#include <stdexcept>
#include "DynamicVector.h"
#include "Node.h"

using namespace std;

typedef int TKey;

typedef int TValue;

typedef bool(*Relation)(TKey, TKey);


class SMMIterator;

class SortedMultiMap
{
    friend class SMMIterator;


private:
    // representation of the SortedMultiMap
    int capacity;
    Node * nodes;
    int head;
    int firstEmpty;
    int length;
    Relation rel;

public:

    //constructor

    SortedMultiMap(Relation r);



    void resize();
    int allocate();
    //adds a key value pair to the multimap

    void add(TKey c, TValue v);



    //removes a key value pair from the multimap

    //returns true if the pair was removed (if it was in the multimap) and false otherwise

    bool remove(TKey c, TValue v);



    //returns the vector of values associated to a key. If the key is not in the MultiMap, the vector is empty

    vector<TValue> search(TKey c) const;

    vector<TValue> removeKey(TKey c) ;

    //returns the number of pairs from the multimap

    int size() const;



    //checks whether the multimap is empty

    bool isEmpty() const;



    //returns an iterator for the multimap

    SMMIterator iterator() const;

    //descturctor

    ~SortedMultiMap();





};


