//
// Created by Teodora Dan on 2019-04-12.
//

#ifndef DSALAB4_SMMITERATOR_H
#define DSALAB4_SMMITERATOR_H

#endif //DSALAB4_SMMITERATOR_H

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
    bool valid() const;
    TElem const getCurrent() const;

};