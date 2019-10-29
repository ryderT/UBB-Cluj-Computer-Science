//
// Created by Teodora Dan on 2019-04-17.
//

#ifndef DSALAB4_NODE_H
#define DSALAB4_NODE_H

#endif //DSALAB4_NODE_H

#pragma once
#include <utility>
#include <vector>

typedef int TKey;
typedef int TValue;
typedef std::pair<TKey, TValue> TElem;


class Node
{
public:
    TElem info;
    int next;
    Node();
    Node(TElem inf, int nxt);
    ~Node();
};