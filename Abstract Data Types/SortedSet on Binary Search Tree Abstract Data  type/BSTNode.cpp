#include "BSTNode.h"

BSTNode::BSTNode(TComp info, BSTNode *left, BSTNode *right) : info{ info }, left{ left }, right{ right } {}

TComp BSTNode::getInfo() const
{
	return this->info;
}
BSTNode *BSTNode::getLeft() const
{
	return this->left;
}
BSTNode *BSTNode::getRight() const
{
	return this->right;
}

void BSTNode::setInfo(TComp newInfo)
{
	this->info = newInfo;
}
void BSTNode::setRight(BSTNode *newRight)
{
	this->right = newRight;
}
void BSTNode::setLeft(BSTNode *newLeft)
{
	this->left = newLeft;
}

bool BSTNode::isLeaf()
{
	return this->right == nullptr && this->left == nullptr;
}