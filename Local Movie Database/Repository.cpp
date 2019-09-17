
#include "Repository.h"
#include <fstream>
//#include "DynamicVector.h"

std::vector<Movie> Repository::getAll()
{
	return this->v;
}

bool Repository::add(const Movie& m)
{
	for (auto i : v)
		if (i == m)
			throw RepoException("Movie already exists!");
	v.emplace_back(m);
	this->writeFile();
	return true;
}

bool Repository::remove(const int pos)
{
	if (pos == -1)
		throw RepoException("Movie does not exist!");
	if (pos != -1)
	{
		//v.erase(pos);
		v.erase(v.begin()+ pos);
		this->writeFile();
		return true;
	}
	return false;
}

bool Repository::update(const Movie& m)
{
	int pos = search(m.getTitle());
	if (pos==-1)
		throw RepoException("Movie does not exist!");
	if (pos != -1)
	{
		v.erase(v.begin() + pos);
		v.insert(v.begin() + pos, m);
		this->writeFile();
		return true;
	}
	return false;
}

int Repository::search(const std::string title)
{
	int k = 0;
	for (auto i : this->v)
	{
		if (i.getTitle() == title)
			return k;
		k++;
	}
	//for (int i = 0; i < this->v.size(); i++)
	//{
	//	if (v[i].getTitle() == title)
	//		return i;
	//}
	return -1;

}

Movie Repository::getMovie(const int pos)const
{
	return v[pos];
}

std::string Repository::getMovieToString(const int pos)const
{
	std::string s = "";
	auto m = v[pos];
	s.append("Movie title:" + m.getTitle() + " Genre:" + m.getGenre() + " Year:" + std::to_string(m.getYear()) + " Likes:" + std::to_string(m.getLikes()) + " Trailer:" + m.getTrailer());
	return s;
}

std::string Repository::getEverythingToString()
{
	std::string evr = "";
	for (auto i : v)
		evr += getMovieToString(this->search(i.getTitle()))+'\n';
	return evr;
}

int Repository::getSize()const
{
	return v.size();
}

void Repository::loadFile()
{
	std::ifstream f("file.txt");
	Movie m;
	while (f >> m)
		this->add(m);
	f.close();
}

void Repository::writeFile()
{
	std::ofstream f("file.txt");
	//for (int i = 0; i < v.size(); i++)
	for(auto i:v)
		f << i;
	f.close();
}