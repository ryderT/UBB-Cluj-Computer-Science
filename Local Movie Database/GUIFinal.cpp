#include "GUIFinal.h"
#include <QObject>
#include <sstream>
#include <Windows.h>
GUIFinal::GUIFinal(QWidget *parent)
	: QMainWindow(parent)
{
	
	ui.setupUi(this);
	std::string title = "title";
	std::string genre = "genre";
	std::string url = "url";
	/*for (int i = 0; i <= 9; i++)
		this->cont.add(title + std::to_string(i), genre + std::to_string(i), 2000 + i, 100 + i, url + std::to_string(i));*/
	repo.loadFile();
	this->populate(cont.getVector());
	QObject::connect(this->ui.addButton, &QPushButton::clicked, this, &GUIFinal::addMovie);
	QObject::connect(this->ui.removeButton, &QPushButton::clicked, this, &GUIFinal::removeMovie);
	QObject::connect(this->ui.updateButton, &QPushButton::clicked, this, &GUIFinal::updateMovie);
	QObject::connect(this->ui.filterButton, &QPushButton::clicked, this, &GUIFinal::filter);
	QObject::connect(this->ui.nextButton, &QPushButton::clicked, this, &GUIFinal::next);
	QObject::connect(this->ui.playButton, &QPushButton::clicked, this, &GUIFinal::play);
	QObject::connect(this->ui.playlistAddButton, &QPushButton::clicked, this, &GUIFinal::addWatchlist);
	QObject::connect(this->ui.playlistShowButton, &QPushButton::clicked, this, &GUIFinal::showWatchlist);
	QObject::connect(this->ui.playlistDeleteButton, &QPushButton::clicked, this, &GUIFinal::deleteWatchlist);
	QObject::connect(this->ui.htmlButton, &QPushButton::clicked, this, &GUIFinal::saveHTML);
	QObject::connect(this->ui.csvButton, &QPushButton::clicked, this, &GUIFinal::saveCSV);
	this->ui.addButton->setText(QString::fromStdString("User"));	this->setStyleSheet("* {color: qlineargradient(spread:pad, x1:0 y1:0, x2:1 y2:0, stop:0 rgba(0, 0, 0, 255), stop:1 rgba(255, 255, 255, 255));""background: qlineargradient( x1:0 y1:0, x2:1 y2:0, stop:0 yellow, stop:1 red);}");
}
void GUIFinal::saveHTML() {

	this->lista = new HTMLfile();
	for (auto i : this->userWatchList)
		lista->add(i);
	lista->printer();
	ShellExecuteA(0, 0, "chrome.exe", "watchlist.html", 0, SW_SHOWMAXIMIZED);

}
void GUIFinal::saveCSV() {
	this->lista = new CSVfile();
	for (auto i : this->userWatchList)
		lista->add(i);
	lista->printer();
	ShellExecuteA(0, 0, "notepad.exe", "watchlist.csv", 0, SW_SHOWMAXIMIZED);

}
void GUIFinal::deleteWatchlist() {
	if (this->ui.deleteWLineEdit->text().toStdString() != "") {
		auto pos = this->repo.search(this->ui.deleteWLineEdit->text().toStdString());
		if (pos != -1) {
			if (this->ui.radioButton->isChecked() == true) {
				this->cont.updateLikes(ui.deleteWLineEdit->text().toStdString(), this->cont.getVector()[pos].getLikes() + 1);
				this->ui.radioButton->setChecked(false);
			}
			for (int i=0;i< this->userWatchList.size();i++)
			{
				if (this->userWatchList[i].getTitle() == this->ui.deleteWLineEdit->text().toStdString()) {
					this->userWatchList.erase(this->userWatchList.begin()+i);
					break;
				}

			}
			this->ui.movieWidget->clear();
			this->populate(cont.getVector());
			this->ui.watchlistWidget->clear();

			for (auto m : this->userWatchList)
			{
				std::string s = m.getTitle() + "," + m.getGenre() + "," + std::to_string(m.getYear()) + "," + std::to_string(m.getLikes()) + "," + m.getTrailer();
				QString str = QString::fromStdString(s);
				this->ui.watchlistWidget->addItem(str + '\n');
			}
		}
	}
}
void GUIFinal::addWatchlist() {
	if (this->index != 0)
		this->userWatchList.emplace_back(cont.getWatchList()[index - 1]);
	else this->userWatchList.emplace_back(cont.getWatchList()[index]);
}
void GUIFinal::showWatchlist() {
	
	this->ui.watchlistWidget->clear();
	
	for (auto m : this->userWatchList)
	{
		std::string s = m.getTitle() + "," + m.getGenre() + "," + std::to_string(m.getYear()) + "," + std::to_string(m.getLikes()) + "," + m.getTrailer();
		QString str = QString::fromStdString(s);
		this->ui.watchlistWidget->addItem(str + '\n');
	}
}

void GUIFinal::play()
{
	if (this->index != 0)
		this->cont.getWatchList()[index-1].play();
	else this->cont.getWatchList()[index].play();
}
void GUIFinal::addMovie() {
	
	this->ui.movieWidget->clear();
	std::string title = this->ui.titleLineEdit->text().toStdString();
	std::string genre = this->ui.genreLineEdit->text().toStdString();
	int year = this->ui.yearLineEdit->text().toInt();
	int likes = this->ui.likesLineEdit->text().toInt();
	std::string trailer = this->ui.trailerLineEdit->text().toStdString();
	cont.add(title, genre, year, likes, trailer);
	this->ui.titleLineEdit->clear();
	this->ui.yearLineEdit->clear();
	this->ui.likesLineEdit->clear();
	this->ui.genreLineEdit->clear();
	this->ui.trailerLineEdit->clear();
	this->populate(cont.getVector());
}
void GUIFinal::removeMovie() {
	this->ui.movieWidget->clear();

	//auto movies = cont.getVector();
	//std::string title = movies[this->ui.movieWidget->currentItem()].getTitle();
	//this->ui.movieWidget->currentItem() = this->ui.movieWidget->selectedItems();
	/*std::string txt = ui.movieWidget->currentItem()->text().toStdString();
	std::vector<std::string> result;
	std::stringstream ss(txt);
	std::string token;
	while (getline(ss, token, ','))
		result.push_back(token);
	std::string title = result[0];*/
	std::string title = this->ui.titleLineEdit->text().toStdString();
	this->cont.remove(title);
	this->ui.titleLineEdit->clear();
	this->populate(cont.getVector());

}
void GUIFinal::updateMovie() {
	this->ui.movieWidget->clear();
	std::string title = this->ui.titleLineEdit->text().toStdString();
	std::string genre = this->ui.genreLineEdit->text().toStdString();
	auto year = this->ui.yearLineEdit->text();
	auto likes = this->ui.likesLineEdit->text();
	std::string trailer = this->ui.trailerLineEdit->text().toStdString();
	if (title != "") {
		if (genre != "") {
			this->cont.updateGenre(title, genre);
		}
		if (likes != "") {
			this->cont.updateLikes(title, likes.toInt());
		}
		if (year != "") {
			this->cont.updateYear(title, year.toInt());
		}
		if (trailer != "") {
			this->cont.updateTrailer(title, trailer);
		}
		this->populate(this->cont.getVector());
	}
}
void GUIFinal::populate(std::vector<Movie> movies)
{	
	this->ui.movieWidget->addItem("Title Genre Year Likes Trailer\n");
	for (auto m : movies)
	{
		std::string s =m.getTitle() + "," + m.getGenre() + "," + std::to_string(m.getYear()) + "," + std::to_string(m.getLikes()) + "," + m.getTrailer();
		QString str = QString::fromStdString(s);
		this->ui.movieWidget->addItem(str + '\n');
	}
}
void GUIFinal::filter()
{
	this->userWatchList.clear();
	this->cont.setWatchList(this->cont.getMoviesGenre(this->ui.filterLineEdit->text().toStdString()));
	this->index = 0;
}
void GUIFinal::next()
{
	if (this->index >= this->cont.getWatchList().size()) {
		this->index = 0;
	}
	this->ui.watchlistWidget->clear();
	auto m = this->cont.getWatchList()[index];
	std::string s = m.getTitle() + "," + m.getGenre() + "," + std::to_string(m.getYear()) + "," + std::to_string(m.getLikes()) + "," + m.getTrailer();
	QString str = QString::fromStdString(s);
	this->ui.watchlistWidget->addItem(str + '\n');
	this->index += 1;
}