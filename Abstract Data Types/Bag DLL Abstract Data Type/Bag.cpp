#include "Bag.h"

Bag::Bag() : head{ nullptr }, tail{ nullptr }, len{ 0 }, cap{ 10 }{}

int Bag::size()const
{
	return this->len;
}

bool Bag::isEmpty()const
{
	return this->len == 0;
}
bool Bag::search(TElem e)const
{
	auto n = this->head;
	while (n != nullptr)
	{
		if (n->getElement() == e)
			return true;
		n = n->getNext();
	}
	return false;
}
void Bag::add(TElem e)
{
	Node* n = new Node(e);
	if (this->len == 0)
	{
		this->head = n;
		this->tail = n;
	}
	else
		if (this->search(e) == true)
		{
			auto aux = this->head;
			while (aux != nullptr)
			{
				if (aux->getElement() == e)
				{
					aux->setFrequency(aux->getFrequency() + 1);
					
				}
				aux = aux->getNext();
			}
		}
		else
		{
			n->setPrev(this->tail);
			n->setNext(nullptr);
			this->tail->setNext(n);
			this->tail = n;
		}
	this->len++;
}
bool Bag::remove(TElem e)
{
	if (this->search(e) == false)
		return false;
	
	if (this->head->getElement() == e)
	{
		this->head->setFrequency(this->head->getFrequency() - 1);
		if (this->head->getFrequency() == 0)
		{
			auto secondElement = this->head->getNext();
			this->head->setNext(nullptr);
			this->head->setPrev(nullptr);
			delete this->head;
			this->head = secondElement;
			if (this->head != nullptr)
				this->head->setPrev(nullptr);
			else
				this->tail = nullptr;
		}
		this->len--;
		return true;
	}

	if (this->tail->getElement() == e)
	{
		this->tail->setFrequency(this->tail->getFrequency() - 1);
		if (this->tail->getFrequency() == 0)
		{
			auto prevLast = this->tail->getPrev();
			this->tail->setNext(nullptr);
			this->tail->setPrev(nullptr);
			delete this->tail;
			this->tail = prevLast;
			if (this->tail != nullptr)
				this->tail->setNext(nullptr);
		}
		this->len--;
		return true;
	}

	auto aux = this->head;
	while (aux != nullptr)
	{
		if (aux->getElement() == e)
		{
			/*auto next = aux->getNext();
			aux->setElement(next->getElement());
			aux->setNext(next->getNext());
			delete next;
			next = nullptr;*/
			aux->setFrequency(aux->getFrequency() - 1);
			if (aux->getFrequency() == 0)
			{
				auto next = aux->getNext();
				auto prev = aux->getPrev();
				next->setPrev(prev);
				prev->setNext(next);
				delete aux;
			}
			this->len--;
			return true;
		}
		aux = aux->getNext();
	}
	return false;
}

int Bag::nrOccurrences(TElem e)const
{
	if (this->search(e) == false)
		return 0;
	auto aux = this->head;
	while (aux != nullptr)
	{
		if (aux->getElement() == e)
			return aux->getFrequency();
		aux = aux->getNext();
	}
}

Bag::~Bag()
{
	Node *currentNode;
	while (head != nullptr) {
		currentNode = head;
		head = head->getNext();
		delete currentNode;
	}
}

BagIterator Bag::iterator() const {
	return BagIterator(*this);
}

int Bag::uniqueCount() const{
	int nrUniq = 0;
	auto aux = this->head;
	while (aux != nullptr)
	{
		nrUniq++;
		aux = aux->getNext();
	}
	return nrUniq;
}
//int Bag::uniqueCount()const
//{
//	int nrUniq = 0;
//	auto aux = this->head;
//	while (aux != nullptr)
//	{
//		if (aux->getFrequency() == 1)
//			nrUniq++;
//		aux = aux->getNext();
//	}
//	return nrUniq;
//}