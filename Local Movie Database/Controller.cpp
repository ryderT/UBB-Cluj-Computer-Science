#include "Controller.h"
#include<fstream>

//ADMIN MENU

bool Controller::add(std::string title, std::string genre, int year, int likes, std::string trailer)
{
	auto m = Movie(title, genre, year, likes, trailer);
	//repo.writeFile();
	return repo.add(m);

}
bool Controller::remove(std::string title)
{	
	int pos = repo.search(title);
	//repo.writeFile();
	return repo.remove(pos);
}
bool Controller::updateGenre(std::string title, std::string genre)
{	

	auto pos = repo.search(title);
	if (pos != -1) {
		auto m = repo.getMovie(pos);
		m.setGenre(genre);
		return repo.update(m);
	}
	return false;
}
bool Controller::updateYear(std::string title, const int year)
{
	auto pos = repo.search(title);
	if (pos != -1) {
		auto m = repo.getMovie(pos);
		m.setYear(year);
		return repo.update(m);
	}
	return false;
}
bool Controller::updateLikes(std::string title, const int likes)
{
	auto pos = repo.search(title);
	if (pos != -1) {
		auto m = repo.getMovie(pos);
		m.setLikes(likes);
		return repo.update(m);
	}
	return false;
}
bool Controller::updateTrailer(std::string title, std::string trailer)
{
	auto pos = repo.search(title);
	if (pos != -1) {
		auto m = repo.getMovie(pos);
		m.setTrailer(trailer);
		return repo.update(m);
	}
	return false;
}

//USER MENU

Repository Controller::getMoviesGenre(std::string genre)
{
	Repository watchRepo;

	if (genre == "")
	{
		watchRepo = this->repo;
		return watchRepo;
	}
	
	for (int i = 0; i < repo.getSize(); i++)
		if (this->repo.getMovie(i).getGenre() == genre)
			watchRepo.add(this->repo.getMovie(i));
	return watchRepo;

}
bool Controller::addWatchList(const Movie m)
{
	return watchlist.add(m);
}
bool Controller::removeWatchList(const std::string title)
{	
	int pos = repo.search(title);
	return watchlist.remove(pos);
}
void Controller::like(std::string title)
{	
	int pos = repo.search(title);
	auto m = repo.getMovie(pos);
	m.setLikes(m.getLikes() + 1);
	repo.update(m);
}
std::string Controller::getWatchListToString()
{
	return watchlist.getEverythingToString();
}
std::string Controller::getAll()
{
	return repo.getEverythingToString();
}
std::vector<Movie> Controller::getVector()
{
	return this->repo.getAll();
}
std::vector<Movie> Controller::getWatchList()
{
	return this->watchlist.getAll();
}
void Controller::setWatchList(Repository v)
{
	this->watchlist = Repository();
	auto vect = v.getAll();
	for (auto i : vect)
		this->watchlist.add(i);
}
