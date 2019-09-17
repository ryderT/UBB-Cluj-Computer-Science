#include "Export.h"
#include <fstream>
#include <Windows.h>

void Export::add(Movie m)
{
	movieList.emplace_back(m);
}

void CSVfile::printer()
{
	std::ofstream g("watchlist.csv");
	for (auto i : this->movieList)
	{
		g << i.getTitle() << "," << i.getGenre() << "," << i.getLikes() << "," << i.getYear() << ","<< i.getTrailer() << "\n";
	}
	g.close();

}

void HTMLfile::printer()
{
	std::ofstream g("watchlist.html");
	g << "<!DOCTYPE html> \n <html> \n <head>\n <title> Watch list </title> </head>\n>";
	g << "<body>\n <table border=\"2\">\n";
	g << "<tr>\n<td>Movie title</td>\n<td>Genre</td>\n<td>Likes</td>\n<td>Year</td>\n</td>\n<td>Trailer</td>\n";
	for (auto i : this->movieList) {
		g << "<tr><td>" << i.getTitle() << "</td>\n<td>" << i.getGenre() << "</td>\n<td>" << i.getLikes() << "</td>\n<td>" << i.getYear() << "</td>\n<td><a href=" << '"' << i.getTrailer() << '"' << ">Link</a></td>\n</tr>";
	}
	g << "</table>\n </body>\n </html> \n";
	g.close();
}