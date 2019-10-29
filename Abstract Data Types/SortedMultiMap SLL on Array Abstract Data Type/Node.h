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