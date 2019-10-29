#pragma once
typedef int TComp;

class BSTNode
{
private:
	TComp info;
	BSTNode *left;
	BSTNode *right;

public:	
	BSTNode(TComp info=0, BSTNode *left=nullptr,BSTNode *right=nullptr);

	//Getters
	TComp getInfo() const;
	BSTNode* getLeft() const;
	BSTNode* getRight() const;

	//Setters
	void setInfo(TComp newInfo);
	void setLeft(BSTNode *newLeft);
	void setRight(BSTNode *newRight);

	bool isLeaf();
	
};

