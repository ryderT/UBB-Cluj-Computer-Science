#pragma once

#include "Exceptions.h"
#include "Movie.h"
#include <vector>

class Repository
{


private:

	std::vector<Movie> v;

public:


	bool add(const Movie& m);
	bool remove(const int pos);
	bool update(const Movie& m);

	int search(const std::string title);

	Movie getMovie(const int pos) const;
	std::string getMovieToString(const int pos)const;
	std::string getEverythingToString();
	std::vector<Movie> getAll();
	int getSize() const;
	void loadFile();
	void writeFile();
	//Repository getAll();

};

class RepoException : public Exceptions {
public:
	RepoException(std::string text):Exceptions(text){}
};