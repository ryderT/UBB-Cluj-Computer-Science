#include "MultiMap.h"

int MultiMap::hash(TKey c) const {
	return c%this->cap;
}

MultiMap::MultiMap()
{
	this->cap = 10;
	this->nrEl = 0;
	this->firstEmpty = 0;
	this->table = new TElem[10];
	this->next = new int[10];
	for (int i = 0; i < 10; i++) {
		this->next[i] = -2;
		this->table[i].first = 1e0;
	}	
}
void MultiMap::readjust() {
	int i=0,k=0;
	if (((1.0*this->nrEl) / (1.0*this->cap)) >= 0.65) {
		TElem *old = new TElem[this->nrEl];
		for (i = 0; i < this->cap; i++) {
			if (this->table[i].first != 1e9) {
				old[k++] = this->table[i];
			}
		}
		this->cap *= 2;
		TElem *newTable = new TElem[this->cap];
		int *newNext = new int[this->cap];
		for (i = 0; i < this->cap; i++) {
			newTable[i].first = 1e9;
			newNext[i] = -2;
		}
		delete[] this->table;
		delete[] this->next;
		this->firstEmpty = 0;
		int oldNrEl = this->nrEl;
		this->nrEl = 0;
		for (i = 0; i < oldNrEl; i++) {
			this->add(old[i].first,old[i].second);
		}
		delete[] old;
	}
}
void MultiMap::add(TKey c, TValue v) {
	int pos = this->hash(c);
	this->readjust();
	if (this->next[pos] == -2) {
		this->next[pos] = -1;
		this->table[pos].first = c;
		this->table[pos].second = v;
		if (this->firstEmpty == pos) {
			while (this->next[firstEmpty] != -2)
				this->firstEmpty++;
		}
	}
	else {
		while (this->next[pos] != -1) {
			pos = this->next[pos];
		}
		this->next[pos] = this->firstEmpty;
		this->table[this->firstEmpty].first = c;
		this->table[this->firstEmpty].second = v;
		while (this->next[firstEmpty] != -2)
			this->firstEmpty++;
	}
	this->nrEl++;
}
bool MultiMap::remove(TKey c, TValue v) {
	int pos = this->hash(c);
	int i = 0,sw=0;
	while (pos != -2 && this->table[pos].first != c)
	{
		if (this->table[pos].second == v) {
			sw = 1;
			this->table[pos].first = 1e9;

		}
		pos = this->next[pos];
	}
	if (sw == 1) {
		return true;
	}
	else {
		return false;
	}

}
vector<TValue> MultiMap::search(TKey c) const {
	int i;
	std::vector<TValue> v;
	int pos = this->hash(c);
	while (this->table[pos].first != -1) {
		if (this->table[pos].first == c)
			v.emplace_back(this->table[pos].second);
		pos = this->next[pos];
	}
	//if (pos == -1)
	return v;
	
}
bool MultiMap::isEmpty() const
{
	return this->nrEl == 0;
}

int MultiMap::size() const
{
	return this->nrEl;
}

MultiMap::~MultiMap()
{
	delete[] this->table;
	delete[] this->next;
}
MultiMapIterator MultiMap::iterator() const
{
	return MultiMapIterator{ *this };
}