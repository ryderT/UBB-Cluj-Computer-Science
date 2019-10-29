#include "SortedSet.h"
//#include "SortedSetIterator.h"


SortedSet::SortedSet(Relation r):root{nullptr},nrElem{0},r{r}{}

bool SortedSet::add(TComp e)
{
	BSTNode *current = this->root;
	BSTNode *parent = nullptr;

	while (current != nullptr) {
		parent = current;
		if (e == current->getInfo()) {
			return false;
		}
		else if (this->r(e, current->getInfo())) {
			current = current->getLeft();
		}
		else {
			current = current->getRight();
		}

	}

	BSTNode *newNode = new BSTNode{ e };
	if (root == nullptr) {
		root = newNode;
	}
	else {
		if (this->r(e, parent->getInfo())) {
			parent->setLeft(newNode);
		}
		else {
			parent->setRight(newNode);
		}
	}
	this->nrElem++;
	return true;
}
BSTNode *SortedSet::getMinimum(BSTNode *startingRoot) {
	BSTNode *currentNode = root;
	BSTNode *minNode = root;

	while (currentNode != nullptr) {
		minNode = currentNode;
		currentNode = currentNode->getLeft();
	}
	return minNode;
}

BSTNode *SortedSet::getMaximum(BSTNode *startingRoot) {
	BSTNode *currentNode = root;
	BSTNode *maxNode = root;

	while (currentNode != nullptr) {
		maxNode = currentNode;
		currentNode = currentNode->getRight();
	}
	return maxNode;
}

BSTNode *SortedSet::removeRecursive(BSTNode *rootNode, TComp e, bool &removed)
{
	if (rootNode == nullptr) { 
		return nullptr;
	}
	if (e == rootNode->getInfo()) {
		removed = true;
		if (rootNode->getLeft() == nullptr) {
			BSTNode *right = rootNode->getRight();
			delete rootNode;
			return right;
		}
		else if (rootNode->getRight() == nullptr) {
			BSTNode *left = rootNode->getLeft();
			delete rootNode;
			return left;
		}
		else {
			BSTNode *maxNode = getMaximum(rootNode->getLeft());
			rootNode->setInfo(maxNode->getInfo());
			rootNode->setLeft(maxNode->getLeft());
			
		}
	}
	else if (this->r(e, rootNode->getInfo())) {
		rootNode->setLeft(removeRecursive(rootNode->getLeft(), e, removed));
	}
	else {
		rootNode->setRight(removeRecursive(rootNode->getRight(), e, removed));
	}
	return rootNode;
}
bool SortedSet::remove(TComp e) {
	bool removed = false;
	root = removeRecursive(root,e,removed);	
	if (removed == true) {
		this->nrElem--;
	}
	return removed;
}
BSTNode *SortedSet::searchRecursive(BSTNode *rootNode, TElem e, bool &found)
{
	if (rootNode == nullptr) {
		return nullptr;
	}
	if (rootNode->getInfo() == e) {
		found = true;
		return rootNode;
	}
	else if (this->r(rootNode->getInfo(), e)) {
		return searchRecursive(rootNode->getLeft(), e, found);
	}
	else {
		return searchRecursive(rootNode->getRight(), e, found);
	}
		
}
bool SortedSet::search(TElem elem) const {
	BSTNode *current = this->root;
	while (current != nullptr) {
		if (current->getInfo() == elem) {
			return true;
		}
		else if (this->r(current->getInfo(), elem)) {
			current = current->getLeft();
		}
		else {
			current = current->getRight();
		}
	}
	return false;
}
int SortedSet::size() const {
	return this->nrElem;
}
bool SortedSet::isEmpty()const {
	return this->nrElem == 0;
}
SortedSetIterator SortedSet::iterator() {
	return SortedSetIterator(*this);
}
void SortedSet::postOrderDel(BSTNode *rootNode) 
{
	if (rootNode == nullptr) {
		return;
	}
	if (rootNode->isLeaf()) {
		delete rootNode;
		return;
	}
	if (rootNode->getLeft() != nullptr) {
		postOrderDel(rootNode->getLeft());
	}
	if (rootNode->getRight() != nullptr) {
		postOrderDel(rootNode->getRight());
	}
	delete rootNode;
}
SortedSet::~SortedSet()
{
	postOrderDel(this->root);
}
