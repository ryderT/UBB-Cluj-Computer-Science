#include "GUIFinal.h"
#include <QtWidgets/QApplication>

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	GUIFinal w;
	w.show();
	return a.exec();
}
