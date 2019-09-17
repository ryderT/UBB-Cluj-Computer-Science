#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_GUIFinal.h"
#include "Controller.h"
#include "Export.h"

class GUIFinal : public QMainWindow
{
	Q_OBJECT

public:
	GUIFinal(QWidget *parent = Q_NULLPTR);
	Repository repo = Repository{};
	Controller cont{ repo };
	void populate(std::vector<Movie> movies);
	int index=0;
	std::vector<Movie> userWatchList;
	Export *lista;
private:
	Ui::GUIFinalClass ui;
private slots:
	void addMovie();
	void removeMovie();
	void updateMovie();
	void next();
	void play();
	void filter();
	void addWatchlist();
	void showWatchlist();
	void deleteWatchlist();
	void saveHTML();
	void saveCSV();


};
