#pragma once

#include "SortedMultiMap.h"

class SortedMultiMap;


class SMMIterator {

    friend class SortedMultiMap;

private:
    SMMIterator(const SortedMultiMap& m);
    const SortedMultiMap& m;
    int currentElement;

public:
    void first();
    void next();
	void previous();
    bool valid() const;
    TElem const getCurrent() const;

};