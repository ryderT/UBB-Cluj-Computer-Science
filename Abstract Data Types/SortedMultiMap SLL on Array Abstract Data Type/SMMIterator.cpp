#include "SortedMultiMap.h"
#include "SMMIterator.h"
#include <stdexcept>

SMMIterator::SMMIterator(const SortedMultiMap& m) : m(m)
{
    currentElement = m.head;
}

void SMMIterator::first()
{
    this->currentElement = this->m.head;
}

void SMMIterator::next()
{
    if (!this->valid())
    {
        throw exception();
    }
    else {
        this->currentElement = this->m.nodes[currentElement].next;
    }
}
void SMMIterator::previous()
{
	if (!this->valid())
		throw exception();
	if (this->currentElement == this->m.head)
	{
		this->currentElement = -1;
		return;
	}
	auto aux = this->m.head;
	while (this->m.nodes[aux].next != currentElement)
		aux= this->m.nodes[aux].next;
	this->currentElement = aux;
}

bool SMMIterator::valid() const
{

    if (this->currentElement != -1)
        return true;
    return false;
}

TElem const SMMIterator::getCurrent() const
{
    if (!this->valid())
    {
        throw exception();
    }
    return this->m.nodes[this->currentElement].info;
}
