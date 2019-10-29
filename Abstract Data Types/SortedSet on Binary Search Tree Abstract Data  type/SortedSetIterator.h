#include <stack>
#include "SortedSet.h"
#include "BSTNode.h"

class SortedSet;
typedef int TComp;
typedef int TElem;

class SortedSetIterator {
	friend class SortedSet;

private:
	const SortedSet& set;
	BSTNode* currentNode;
	std::stack <BSTNode*> stack;
	SortedSetIterator(const SortedSet& set);

public:
	void first();
	void next();
	bool valid() const;
	TComp getCurrent() const;
};
