#include "pch.h"
#include "Set.h"
#include "Iterator.h"


Set::Set(int capacity)
{
	this->sizes = 0;
	this->capacity = capacity;
	this->elems = new TElem[capacity];
}
Set::Set(const Set& set)
{
	this->sizes = set.sizes;
	this->capacity = set.capacity;
	this->elems = new TElem[this->capacity];
	for (int i = 0; i < this->sizes; i++)
		this->elems[i] = set.elems[i];
}
Set::~Set()
{
	delete[] this->elems;
}

void Set::resize(double factor)
{
	this->capacity *= static_cast<int>(factor);
	TElem* els = new TElem[this->capacity];
	for (int i = 0; i < this->sizes; i++)
		els[i] = this->elems[i];
	delete[] this->elems;
	this->elems = els;
}

bool Set::add(TElem e)
{
	if (this->search(e) == false)
	{
		if (this->sizes == this->capacity)
			this->resize();
		this->elems[set.size()] = e;
		this->sizes++;
		return true;
	}
	return false;

}
bool Set::remove(TElem e)
{	
	if (this->search(e) == true)
	{
		for (int i = 0; i < this->sizes; i++)
			if (this->elems[i] == e)
			{
				for (int j = i; j < this->sizes - 1; j++)
					this->elems[i] = this->elems[i + 1];
				this->sizes--;
				return true;
			}
	}
	return false;
}
int Set::size()
{
	return this->sizes;
}
bool Set::isEmpty()
{
	return this->sizes == 0;
}
bool Set::search(TElem elem)
{
	int i = 0;
	for (i = 0; i < this->sizes; i++)
		if (this->elems[i] == elem)
			return true;
	return false;
}
SetIterator Set::iterator()
{
	return SetIterator(*this);
}

TElem Set::getElem(TElem pos)
{
	return this->elems[pos];
}