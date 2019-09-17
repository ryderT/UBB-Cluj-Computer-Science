#include "Test.h"
#include <assert.h>
#include "Movie.h"
#include "Repository.h"
#include "Controller.h"
#include <iostream>

#include <vector>


void testAll()
{
	//  DYNAMIC VECTOR TESTS


	//////////////////////////

	///////// MOVIE TESTS

	Movie m("a", "a", 0, 0, "https://www.youtube.com/watch?v=ytdjYjM-cLg");
	Movie mv("b", "a", 0, 0, "https://www.youtube.com/watch?v=5C8ESp7jko0");
	assert(m.getGenre() == "a");
	assert(m.getLikes() == 0);
	assert(m.getTitle() == "a");
	assert(m.getYear() == 0);
	assert(m.getTrailer() == "https://www.youtube.com/watch?v=ytdjYjM-cLg");

	assert(!(mv == m));
	Movie mtes;
	mtes.setGenre("a");
	mtes.setLikes(0);
	mtes.setTitle("a");
	mtes.setTrailer("https://www.youtube.com/watch?v=5C8ESp7jko0");
	mtes.setYear(0);
	assert(mtes == m);
	assert(m == m);

	//m.play();

	///////////////////////

	////////REPOSITORY TESTS

	Repository repo;
	assert(repo.getEverythingToString() == "");
	assert(repo.add(mv) == true);
	assert(repo.add(mv) == false);
	assert(repo.add(m) == true);
	assert(repo.getSize() == 2);
	assert(repo.getMovie(0) == mv);
	//printf("%s", repo.getEverythingToString().c_str());
	//printf("%s", repo.getMovieToString(0).c_str());
	//std::string s = "Movie Title:a Genre:a Year:0 Likes:0 Trailer:https://www.youtube.com/watch?v=5C8ESp7jko0";
	//assert(repo.getMovieToString(0).c_str() == "Movie title:a Genre:a Year:0 Likes:0 Trailer:https://www.youtube.com/watch?v=5C8ESp7jko0");
	assert(repo.remove(repo.search(m.getTitle())) == true);
	assert(repo.getSize() == 1);
	Movie mv1("c", "a", 0, 0, "https://www.youtube.com/watch?v=5C8ESp7jko0");
	Movie mv2("d", "a", 0, 0, "https://www.youtube.com/watch?v=5C8ESp7jko0");
	repo.add(mv1);
	repo.add(mv2);
	std::string s1= repo.getMovieToString(2);
	//std::cout << s1;
	//assert(repo.getMovieToString(2) == "Movie title : Genre: Year:0 Likes : 0 Trailer :");
	//assert(repo.getSize() == 3);
	std::string s2= repo.getEverythingToString();
	//std::cout << s2;
	///////////////////////////////////



	////////////////CONTROLER TESTS

	Controller ctr(repo);
	assert(ctr.add("", "tud", 0, 0, "") == true);
	assert(repo.getSize() == 4);
	assert(ctr.remove("d") == true);
	assert(repo.getSize() == 3);
	Repository test = ctr.getMoviesGenre("tud");
	assert(test.getSize() == 1);
	test = ctr.getMoviesGenre("");
	assert(test.getSize() == repo.getSize());
	assert(ctr.updateGenre("c", "d") == true);
	assert(ctr.updateLikes("c",1)==true);
	assert(ctr.updateYear("c", 1) == true);
	assert(ctr.updateTrailer("c", "d") == true);
	assert(ctr.updateGenre("sd", "a") == false);
	assert(ctr.updateLikes("sd", 0) == false);
	assert(ctr.updateYear("sd", 0) == false);
	assert(ctr.updateTrailer("sd", "a") == false);
	assert(ctr.addWatchList(m)==true);
	assert(ctr.addWatchList(m) == false);
	//std::string s3 = ctr.getWatchListToString();
	//std::string s4 = ctr.getAll();
	assert(repo.getMovie(1).getGenre() == "d");
	ctr.add("like", "like", 0, 0, "like");
	
	//repo.add()
	//std::cout << ml.getLikes();
	//test = ctr.getMoviesGenre("");
	//assert(test.getMovie(2).getLikes() == 1);
	////////////////////////////////////////	
}