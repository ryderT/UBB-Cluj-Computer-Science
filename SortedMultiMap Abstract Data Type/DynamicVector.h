//
// Created by Teodora Dan on 2019-04-16.
//

#ifndef DSALAB4_DYNAMICVECTOR_H
#define DSALAB4_DYNAMICVECTOR_H

#endif //DSALAB4_DYNAMICVECTOR_H

#pragma once
#include "SortedMultiMap.h"

template <typename TElem>
class DynamicArray;

template <typename Ceva>
DynamicArray<Ceva> operator+(const Ceva &e, DynamicArray<Ceva> da) {
    da.add(e);
    return da;
}

template <typename TElem>
class DynamicArray
{
public:
    DynamicArray(int cap = 2);
    DynamicArray(const DynamicArray<TElem> &v);
    void add(const TElem &e);
    void remove(const int pos);
    void add_to_position(const TElem &e, const int pos);
    void update(const TElem &e, const int pos);
    bool isIn(const TElem &e);
    int size() const;
    int getCap();
    void resize();
    TElem* getList() const;
    TElem getFromPosition(const int pos) const;
    DynamicArray operator=(const DynamicArray<TElem> &v);
    DynamicArray operator+(const TElem &e);

    template <typename Ceva>
    friend DynamicArray<Ceva> operator+(const Ceva &e, DynamicArray<Ceva> da);

    ~DynamicArray();
private:
    int cap;
    int sizee;
    TElem* elems;
};

template <typename TElem>
DynamicArray<TElem>::DynamicArray(const DynamicArray &v) {
    //copy constructor
    this->cap = v.cap;
    this->sizee = v.sizee;
    this->elems = new TElem[this->cap];
    for (int i = 0; i < this->sizee; i++) {
        this->elems[i] = v.elems[i];
    }
}

template <typename TElem>
DynamicArray<TElem>::DynamicArray(int cap)
{
    //constructor
    if (cap <= 0) {
        throw std::invalid_argument("Capacity must be a positive number!");
    }
    this->cap = cap;
    this->sizee = 0;
    this->elems = new TElem[this->cap];
}

template <typename TElem>
TElem* DynamicArray<TElem>::getList() const {
    //gets the list of elements
    //input:
    //output: TElem*
    return this->elems;
}

template <typename TElem>
TElem DynamicArray<TElem>::getFromPosition(const int pos) const
{
    //returns the element from position pos or raises an exception if pos is invalid
    //input: pos - int
    //output: TElem
    //exception: pos is invalid
    if (pos < 0 || pos >= this->sizee) {
        throw std::invalid_argument("Invalid position");
    }
    return this->elems[pos];
}

template <typename TElem>
DynamicArray<TElem> DynamicArray<TElem>::operator=(const DynamicArray<TElem>& v) {
    //overloads the =(assignment) operator
    this->cap = v.cap;
    this->sizee = v.sizee;
    delete[] this->elems;
    this->elems = new TElem[this->cap];
    for (int i = 0; i < v.sizee; i++) {
        this->elems[i] = v.elems[i];
    }
    return *this;
}

template <typename TElem>
DynamicArray<TElem> DynamicArray<TElem>::operator+(const TElem & e)
{
    //overloads the +(addition) operator for v = v + e
    this->add(e);
    return *this;
}

template <typename TElem>
int DynamicArray<TElem>::size() const {
    //returns the size of the array
    return this->sizee;
}

template <typename TElem>
int DynamicArray<TElem>::getCap() {
    //returns the capacity of the array
    return this->cap;
}

template <typename TElem>
void DynamicArray<TElem>::resize()
{
    //if the size is equal to the capacity double the capacity and reallocate the elements
    if (this->sizee == this->cap) {
        TElem* copy = new TElem[this->cap * 2];
        for (int i = 0; i <this->sizee; i++) {
            copy[i] = this->elems[i];
        }
        delete[] this->elems;
        this->elems = copy;
        this->cap *= 2;
    }
}

template <typename TElem>
void DynamicArray<TElem>::add(const TElem &e) {
    //adds a new element in the array or raises an exception
    //pre: e is not in the array
    //post: e is added
    //exceptions: e is already in the array
    //if (!(this->isIn(e))) {
    resize();
    this->elems[this->sizee++] = e;
    //}
    //else {
    //	throw std::invalid_argument("Already existing element");
    //}
}

template <typename TElem>
void DynamicArray<TElem>::remove(const int pos) {
    //removes the element from position pos or raises an exception
    //pre: pos is valid (between 0 and size-1)
    //pos: element from position pos is removed
    //exceptions: pos is invalid
    if (pos < 0 || pos >= this->sizee) {
        throw std::invalid_argument("Invalid position");
    }
    for (int j = pos; j < this->sizee - 1; j++) {
        this->elems[j] = this->elems[j + 1];
    }
    this->sizee--;
}

template <typename TElem>
void DynamicArray<TElem>::add_to_position(const TElem &e, const int pos) {
    //adds an element to a certain position
    //pre: pos is valid (between 0 and size-1) and e does not exist in the array
    //pos: element from position pos is removed
    //exceptions: pos is invalid or e is already in the array
    //if (!(this->isIn(e))) {
    if (pos >= 0 && pos < this->sizee) {
        resize();
        this->sizee++;
        for (int i = this->sizee - 1; i > pos; i--) {
            this->elems[i] = this->elems[i - 1];
        }
        this->elems[pos] = e;
    }
    else {
        if (pos == this->sizee) {
            add(e);
        }
        else {
            throw std::invalid_argument("Invalid position");
        }
    }
}


template <typename TElem>
void DynamicArray<TElem>::update(const TElem & e, const int pos)
{
    //update the element from a position with a new element
    //pre: pos is valid
    //post: element from position pos is e
    //exceptions: pos is invalid
    if (pos < 0 || pos >= this->sizee) {
        throw std::invalid_argument("Invalid position");
    }
    this->elems[pos] = e;
}

template <typename TElem>
bool DynamicArray<TElem>::isIn(const TElem & e)
{
    //returns true if e is in the array or false otherwise
    for (int i = 0; i < this->sizee; i++) {
        if (this->elems[i] == e) {
            return true;
        }
    }
    return false;
}

template <typename TElem>
DynamicArray<TElem>::~DynamicArray<TElem>()
{
    delete[] this->elems;
}

