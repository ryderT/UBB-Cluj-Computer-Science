#include "pch.h"
#include "Iterator.h"
#include "Set.h"
#include <stdexcept>

SetIterator::SetIterator(const Set& set): set(set)
{
	this->head = 0;
	this->current = 0;
	//int ar = 0;
}

//sets the iterator to the first element of the container

void SetIterator::first()
{
	this->current = 0;
}



//moves the iterator to the next element

//throws exception if the iterator is not valid

void SetIterator::next()
{
	if (this->valid() == true)
		this->current++;
	else
		throw std::invalid_argument("Invalid iterator");
}



//checks if the iterator is valid

bool SetIterator::valid()
{
	if (this->current > set.sizes || this->current < 0)
		return false;
}	



//returns the value of the current element from the iterator

// throws exception if the iterator is not valid

TElem SetIterator::getCurrent() 
{
	if (this->valid() == true)
		return set.getElem(this->current);
	else throw std::invalid_argument("Invalid iterator");
}