#pragma once
#include <vector>
#include "Movie.h"
#include <fstream>

class Export
{
protected:
	std::vector<Movie> movieList;
public:
	Export() {} 
	virtual ~Export() {}
	void add(Movie);
	virtual void printer() = 0;
};

class CSVfile : public Export {
public:
	CSVfile(){}
	~CSVfile() override {}
	void printer() override;
};

class HTMLfile : public Export {
public:
	HTMLfile() {}
	~HTMLfile() override {}
	void printer() override;
};

