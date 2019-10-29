#include "BagIterator.h"
#include <stdexcept>


BagIterator::BagIterator(const Bag& b): b(b)
{
	if (b.isEmpty() == true)
		this->current = nullptr;
	else
		first();
}

//sets the iterator to the first element of the container

void BagIterator::first()
{
	this->current = b.head;
	this->currentF = b.head->getFrequency();
}



//moves the iterator to the next element

//throws exception if the iterator is not valid

void BagIterator::next()
{
	if (this->valid() == false)
		throw std::exception();

	--this->currentF;
	if (this->currentF == 0) 
	{
		this->current = this->current->getNext();
		if (this->current != nullptr) 
		{
			this->currentF = this->current->getFrequency();
		}
	}
}



//checks if the iterator is valid

bool BagIterator::valid() const
{
	return this->current != nullptr;
}



//returns the value of the current element from the iterator

// throws exception if the iterator is not valid

TElem BagIterator::getCurrent() const
{
	if (this->valid() == false)
		throw std::exception();
	return this->current->getElement();
}


