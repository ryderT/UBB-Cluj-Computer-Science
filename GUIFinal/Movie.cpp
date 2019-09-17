#include "Movie.h"
//#include <shellapi.h>
#include <Windows.h>
//include <iostream>
#include <vector>
#include <fstream>
#include <sstream>
Movie::Movie() :title(""), genre(""), year(0), likes(0), trailer("") {}


Movie::Movie(const std::string title, const std::string genre, const int year, const int likes, const std::string trailer)
{
	this->title = title;
	this->genre = genre;
	this->year = year;
	this->likes = likes;
	this->trailer = trailer;
}

	//getters

std::string Movie::getTitle() const
{
	return title;
}
std::string Movie::getGenre() const
{
	return genre;
}
std::string Movie::getTrailer() const
{
	return trailer;
}
int Movie::getYear() const
{
	return year;
}
int Movie::getLikes() const
{
	return likes;
}

   //setters

void Movie::setTitle(const std::string title)
{
	this->title = title;
}
void Movie::setGenre(const std::string genre)
{
	this->genre = genre;
}
void Movie::setTrailer(const std::string trailer)
{
	this->trailer = trailer;
}
void Movie::setYear(const int year)
{
	this->year = year;
}
void Movie::setLikes(const int likes)
{
	this->likes = likes;
}


// = overload

Movie& Movie::operator=(const Movie& movie)
{
	//if (this == &movie)
	//	return *this;
	this->title = movie.getTitle();
	this->genre = movie.getGenre();
	this->year = movie.getYear();
	this->trailer = movie.getTrailer();
	this->likes = movie.getLikes();

	return *this;
}

//== overload
bool operator==(const Movie movie1, const Movie movie2)
{
	if (movie1.getTitle() == movie2.getTitle())
		return true;
	return false;
}

//play
void Movie::play()
{
	ShellExecuteA(NULL, NULL, "chrome.exe", this->getTrailer().c_str(), NULL, SW_SHOWMAXIMIZED);
}
std::istream& operator>>(std::istream & fin, Movie& m)
{
	
	std::string s;
	getline(fin, s);

	std::vector<std::string> result;
	std::stringstream ss(s);

	std::string token;
	while (getline(ss, token, ';'))
		result.push_back(token);

	if (result.size() != 5)
		return fin;

	m.setTitle(result[0]);
	m.setGenre(result[1]);
	m.setYear(std::stoi(result[2]));
	m.setLikes(std::stoi(result[3]));
	m.setTrailer(result[4]);

	return fin;
}

std::ostream & operator<<(std::ostream & fout, Movie& m)
{
	fout << m.getTitle() << ";" << m.getGenre() << ";" << m.getYear() << ";" << m.getLikes() << ";" << m.getTrailer()<<"\n";
	return fout;
}