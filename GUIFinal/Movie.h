#pragma once
#include <string>
#include<fstream>
class Movie
{
private:
	std::string title;
	std::string genre;
	int year;
	int likes;
	std::string trailer;

public:
	Movie();//default
	Movie(const std::string title, const std::string genre,const int year, const int likes, const std::string trailer);//params
	//~Movie();

	//getters

	std::string getTitle() const;
	std::string getGenre() const;
	std::string getTrailer() const;
	int getYear() const;
	int getLikes() const;

	//setters

	void setTitle(const std::string title);
	void setGenre(const std::string genre);
	void setTrailer(const std::string trailer);
	void setYear(const int year);
	void setLikes(const int likes);

	//play
	void play();

	// = overload

	Movie& operator=(const Movie& movie);

	//== overload


	// file read/write overload
	
	friend std::istream& operator>>(std::istream& fin, Movie& m);
	friend std::ostream& operator<<(std::ostream& fout, Movie& m);
	
};

bool operator==(const Movie movie1, const Movie movie2); //compare 2 movies(classes) by title

