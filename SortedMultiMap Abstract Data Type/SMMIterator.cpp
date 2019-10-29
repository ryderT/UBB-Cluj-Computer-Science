//
// Created by Teodora Dan on 2019-04-12.
//

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
