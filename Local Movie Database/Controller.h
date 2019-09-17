#pragma once
#include "Repository.h"
class Controller
{
	Repository &repo;
	Repository watchlist = Repository();

public:
	//Controller();
	
	Controller(Repository& r) : repo{ r } {}

	//ADMIN MENU

	bool add(std::string title, std::string genre, int year, int likes, std::string trailer);
	bool remove(std::string title);
	bool updateGenre(std::string title,std::string genre);
	bool updateYear(std::string title,const int year);
	bool updateLikes(std::string title,const int likes);
	bool updateTrailer(std::string title, std::string trailer);
	std::vector<Movie> getVector();

	//USER MENU

	Repository getMoviesGenre(std::string genre);
	bool addWatchList(const Movie m);
	bool removeWatchList(const std::string title);
	void like(std::string title);
	std::string getWatchListToString();
	std::string getAll();
	std::vector<Movie> getWatchList();
	void setWatchList(Repository v);

};

