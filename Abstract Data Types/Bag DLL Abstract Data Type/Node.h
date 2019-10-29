#pragma once
typedef int TElem;

class Node {
private:
	TElem element;
	int freq;
	Node* prev;
	Node* next;

public:
	Node(TElem element = 0,int freq=1, Node *prev = nullptr, Node *next = nullptr);
	TElem getElement() const;
	void setElement(TElem element);
	int getFrequency()const;
	void setFrequency(int freq);
	Node *getPrev() const;
	void setPrev(Node *prev);
	Node *getNext() const;
	void setNext(Node *next);
	bool operator==(const Node &rhs) const;
	bool operator!=(const Node &rhs) const;
};