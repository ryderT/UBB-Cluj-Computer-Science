#include "Node.h"


Node::Node(TElem element,int freq, Node *prev, Node *next) : element(element),freq(freq), prev(prev), next(next) {}

TElem Node::getElement() const {
	return element;
}

void Node::setElement(TElem element) {
	this->element = element;
}
int Node::getFrequency()const{
	return this->freq;
}
void Node::setFrequency(int freq) {
	this->freq = freq;
}

Node *Node::getPrev() const {
	return prev;
}

void Node::setPrev(Node *prev) {
	this->prev = prev;
}

Node *Node::getNext() const {
	

	return next;
}

void Node::setNext(Node *next) {
	this->next = next;
}

bool Node::operator==(const Node &rhs) const {
	return element == rhs.element &&
		prev == rhs.prev &&
		next == rhs.next;
}

bool Node::operator!=(const Node &rhs) const {
	return !(rhs == *this);
}