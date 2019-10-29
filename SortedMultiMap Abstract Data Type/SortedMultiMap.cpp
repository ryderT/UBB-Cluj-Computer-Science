//
// Created by Teodora Dan on 2019-04-12.
//

#include "SortedMultiMap.h"
#include "SMMIterator.h"
#include <iostream>

SortedMultiMap::SortedMultiMap(Relation r) {
    //Complexity: Theta(n)
    this->capacity = 10;
    this->head = -1;
    this->nodes = new Node[10];
    for (int i = 0; i < this->capacity; i++) {
        this->nodes[i].next = i + 1;
    }

    this->nodes[this->capacity-1].next = -1;

    this->rel = r;
    this->firstEmpty = 0;
    this->length = 0;
}

void SortedMultiMap::resize() {
    //Complexity: Theta(n*n)
    Node* newElems = new Node[this->capacity * 2];
    //make copy of initial elements
    for (int i = 0; i < this->capacity; i++) {
    newElems[i] = this->nodes[i];
    }
    for (int i = this->capacity; i < this->capacity * 2 - 1; i++) {
    newElems[i].next = i + 1;
    }
    newElems[2 * this->capacity-1].next = -1;

    this->firstEmpty = this->capacity;
    this->capacity *= 2;
    delete[] this->nodes;
    this->nodes = newElems;
}

int SortedMultiMap::allocate() {
    //Complexity: Theta(1)
    int newElem = this->firstEmpty;
    if (newElem != -1) {
        this->firstEmpty = this->nodes[this->firstEmpty].next;
        this->nodes[newElem].next = -1;
    }
    return newElem;
}

vector<TValue> SortedMultiMap::search(TKey c) const {
    //Complexity: O(n)
    vector<TValue> v;
    int current = this->head;
    while (current != -1) {
        if (this->nodes[current].info.first == c)
            v.push_back(this->nodes[current].info.second);
        current = this->nodes[current].next;
    }
    return v;
}

vector<TValue> SortedMultiMap::removeKey(TKey c) {
    //Complexity: BC=WC=AC: Theta(n)
    int current = this->head;
    int prevNode = -1;

    vector<TValue> m;
    while (current != -1) {
        if (this->nodes[current].info.first == c)
            m.push_back(this->nodes[current].info.second);
        current = this->nodes[current].next;
    }

    current = this->head;
    while (current != -1) {
        if (this->nodes[current].info.first == c) {
            if (current == this->head) {//we need to remove the head
                this->head = this->nodes[current].next;

            } else {
                this->nodes[prevNode].next = this->nodes[current].next;
            }

            this->nodes[current].next = this->firstEmpty;
            this->firstEmpty = current;
            this->length--;
        }

        prevNode = current;
        current = this->nodes[current].next;
    }

    return m;
}

void SortedMultiMap::add(TKey c, TValue v) {
    //Complexity: O(n)
    int new_elem = this->allocate();
    if (new_elem == -1) {
        this->resize();
        new_elem = this->allocate();
    }

    this->nodes[new_elem].info.first = c;
    this->nodes[new_elem].info.second = v;

    TValue old_value;
    vector<TValue> m;
    m = this->search(c);
    int current = this->head;

    if (this->length == 0) { //the list is empty
        if (this->head == -1) {
            this->head = new_elem;
            this->length++;
        }
    }

    else {
        while (current != -1 && this->rel(this->nodes[this->nodes[current].next].info.first, c) != false) {
            current = this->nodes[current].next;
        }

        if (current == 0) {
            this->nodes[new_elem].next = this->head;
            this->head = new_elem;
            this->length++;
        } else {
            int currentNode = this->head;
            int currentPos = 0;
            while (currentNode != -1 and currentPos < current - 1) {
                currentNode = this->nodes[currentNode].next;
                currentPos += 1;
            }

            if (currentNode != -1) {
                int nextNode = this->nodes[currentNode].next;
                this->nodes[new_elem].next = nextNode;
                this->nodes[currentNode].next = new_elem;
                this->length++;
            }
        }
    }
}

bool SortedMultiMap::remove(TKey c, TValue v)
{
    //Complexity: O(n)
    int current = this->head;
    int prevNode = -1;
    while (current != -1) {
            if (this->nodes[current].info.first == c && this->nodes[current].info.second == v) {
                if (current == this->head) {//we need to remove the head
                    this->head = this->nodes[current].next;

                } else {
                    this->nodes[prevNode].next = this->nodes[current].next;
                }

                this->nodes[current].next = this->firstEmpty;
                this->firstEmpty = current;
                this->length--;
                return true;
            }

        prevNode = current;
        current = this->nodes[current].next;
    }
    return false;
}


int SortedMultiMap::size() const
{
    //Complexity: Theta(1)
    return this->length;
}


bool SortedMultiMap::isEmpty() const
{
    //Complexity: Theta(1)
    if (this->length == 0) {
        return true;
    }
    else {
        return false;
    }
}

SMMIterator SortedMultiMap::iterator() const
{
    //Complexity: Theta(1)
    return SMMIterator(*this);
}

SortedMultiMap::~SortedMultiMap()
{
}



