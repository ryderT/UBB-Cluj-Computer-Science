#pragma once
#include <exception>
#include <string>
class Exceptions: public std::exception{

private:
	std::string text;
public:
	Exceptions(std::string text):text(text){}
	const char* what() { return text.c_str(); }
};

