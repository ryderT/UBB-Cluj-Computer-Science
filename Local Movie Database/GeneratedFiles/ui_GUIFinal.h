/********************************************************************************
** Form generated from reading UI file 'GUIFinal.ui'
**
** Created by: Qt User Interface Compiler version 5.12.3
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_GUIFINAL_H
#define UI_GUIFINAL_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QRadioButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_GUIFinalClass
{
public:
    QWidget *centralWidget;
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QLabel *label;
    QListWidget *movieWidget;
    QWidget *formLayoutWidget;
    QFormLayout *formLayout;
    QLabel *titleLabel;
    QLabel *genreLabel;
    QLineEdit *genreLineEdit;
    QLabel *yearLabel;
    QLineEdit *yearLineEdit;
    QLabel *likesLabel;
    QLineEdit *likesLineEdit;
    QLabel *trailerLabel;
    QLineEdit *trailerLineEdit;
    QLineEdit *titleLineEdit;
    QWidget *gridLayoutWidget;
    QGridLayout *gridLayout;
    QPushButton *addButton;
    QPushButton *removeButton;
    QPushButton *updateButton;
    QWidget *verticalLayoutWidget_2;
    QVBoxLayout *verticalLayout_2;
    QLabel *label_2;
    QListWidget *watchlistWidget;
    QWidget *gridLayoutWidget_2;
    QGridLayout *gridLayout_2;
    QPushButton *playlistShowButton;
    QLineEdit *deleteWLineEdit;
    QPushButton *nextButton;
    QPushButton *playlistDeleteButton;
    QPushButton *filterButton;
    QLineEdit *filterLineEdit;
    QPushButton *playlistAddButton;
    QPushButton *playButton;
    QRadioButton *radioButton;
    QWidget *horizontalLayoutWidget;
    QHBoxLayout *horizontalLayout;
    QPushButton *htmlButton;
    QPushButton *csvButton;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *GUIFinalClass)
    {
        if (GUIFinalClass->objectName().isEmpty())
            GUIFinalClass->setObjectName(QString::fromUtf8("GUIFinalClass"));
        GUIFinalClass->resize(710, 799);
        centralWidget = new QWidget(GUIFinalClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        verticalLayoutWidget = new QWidget(centralWidget);
        verticalLayoutWidget->setObjectName(QString::fromUtf8("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(0, 0, 351, 541));
        verticalLayout = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        label = new QLabel(verticalLayoutWidget);
        label->setObjectName(QString::fromUtf8("label"));

        verticalLayout->addWidget(label);

        movieWidget = new QListWidget(verticalLayoutWidget);
        movieWidget->setObjectName(QString::fromUtf8("movieWidget"));

        verticalLayout->addWidget(movieWidget);

        formLayoutWidget = new QWidget(centralWidget);
        formLayoutWidget->setObjectName(QString::fromUtf8("formLayoutWidget"));
        formLayoutWidget->setGeometry(QRect(40, 550, 271, 141));
        formLayout = new QFormLayout(formLayoutWidget);
        formLayout->setSpacing(6);
        formLayout->setContentsMargins(11, 11, 11, 11);
        formLayout->setObjectName(QString::fromUtf8("formLayout"));
        formLayout->setContentsMargins(0, 0, 0, 0);
        titleLabel = new QLabel(formLayoutWidget);
        titleLabel->setObjectName(QString::fromUtf8("titleLabel"));

        formLayout->setWidget(0, QFormLayout::LabelRole, titleLabel);

        genreLabel = new QLabel(formLayoutWidget);
        genreLabel->setObjectName(QString::fromUtf8("genreLabel"));

        formLayout->setWidget(1, QFormLayout::LabelRole, genreLabel);

        genreLineEdit = new QLineEdit(formLayoutWidget);
        genreLineEdit->setObjectName(QString::fromUtf8("genreLineEdit"));

        formLayout->setWidget(1, QFormLayout::FieldRole, genreLineEdit);

        yearLabel = new QLabel(formLayoutWidget);
        yearLabel->setObjectName(QString::fromUtf8("yearLabel"));

        formLayout->setWidget(2, QFormLayout::LabelRole, yearLabel);

        yearLineEdit = new QLineEdit(formLayoutWidget);
        yearLineEdit->setObjectName(QString::fromUtf8("yearLineEdit"));

        formLayout->setWidget(2, QFormLayout::FieldRole, yearLineEdit);

        likesLabel = new QLabel(formLayoutWidget);
        likesLabel->setObjectName(QString::fromUtf8("likesLabel"));

        formLayout->setWidget(3, QFormLayout::LabelRole, likesLabel);

        likesLineEdit = new QLineEdit(formLayoutWidget);
        likesLineEdit->setObjectName(QString::fromUtf8("likesLineEdit"));

        formLayout->setWidget(3, QFormLayout::FieldRole, likesLineEdit);

        trailerLabel = new QLabel(formLayoutWidget);
        trailerLabel->setObjectName(QString::fromUtf8("trailerLabel"));

        formLayout->setWidget(4, QFormLayout::LabelRole, trailerLabel);

        trailerLineEdit = new QLineEdit(formLayoutWidget);
        trailerLineEdit->setObjectName(QString::fromUtf8("trailerLineEdit"));

        formLayout->setWidget(4, QFormLayout::FieldRole, trailerLineEdit);

        titleLineEdit = new QLineEdit(formLayoutWidget);
        titleLineEdit->setObjectName(QString::fromUtf8("titleLineEdit"));

        formLayout->setWidget(0, QFormLayout::FieldRole, titleLineEdit);

        gridLayoutWidget = new QWidget(centralWidget);
        gridLayoutWidget->setObjectName(QString::fromUtf8("gridLayoutWidget"));
        gridLayoutWidget->setGeometry(QRect(60, 693, 239, 51));
        gridLayout = new QGridLayout(gridLayoutWidget);
        gridLayout->setSpacing(6);
        gridLayout->setContentsMargins(11, 11, 11, 11);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        gridLayout->setContentsMargins(0, 0, 0, 0);
        addButton = new QPushButton(gridLayoutWidget);
        addButton->setObjectName(QString::fromUtf8("addButton"));

        gridLayout->addWidget(addButton, 0, 0, 1, 1);

        removeButton = new QPushButton(gridLayoutWidget);
        removeButton->setObjectName(QString::fromUtf8("removeButton"));

        gridLayout->addWidget(removeButton, 0, 1, 1, 1);

        updateButton = new QPushButton(gridLayoutWidget);
        updateButton->setObjectName(QString::fromUtf8("updateButton"));

        gridLayout->addWidget(updateButton, 0, 2, 1, 1);

        verticalLayoutWidget_2 = new QWidget(centralWidget);
        verticalLayoutWidget_2->setObjectName(QString::fromUtf8("verticalLayoutWidget_2"));
        verticalLayoutWidget_2->setGeometry(QRect(379, -1, 331, 541));
        verticalLayout_2 = new QVBoxLayout(verticalLayoutWidget_2);
        verticalLayout_2->setSpacing(6);
        verticalLayout_2->setContentsMargins(11, 11, 11, 11);
        verticalLayout_2->setObjectName(QString::fromUtf8("verticalLayout_2"));
        verticalLayout_2->setContentsMargins(0, 0, 0, 0);
        label_2 = new QLabel(verticalLayoutWidget_2);
        label_2->setObjectName(QString::fromUtf8("label_2"));

        verticalLayout_2->addWidget(label_2);

        watchlistWidget = new QListWidget(verticalLayoutWidget_2);
        watchlistWidget->setObjectName(QString::fromUtf8("watchlistWidget"));

        verticalLayout_2->addWidget(watchlistWidget);

        gridLayoutWidget_2 = new QWidget(centralWidget);
        gridLayoutWidget_2->setObjectName(QString::fromUtf8("gridLayoutWidget_2"));
        gridLayoutWidget_2->setGeometry(QRect(370, 560, 329, 141));
        gridLayout_2 = new QGridLayout(gridLayoutWidget_2);
        gridLayout_2->setSpacing(6);
        gridLayout_2->setContentsMargins(11, 11, 11, 11);
        gridLayout_2->setObjectName(QString::fromUtf8("gridLayout_2"));
        gridLayout_2->setContentsMargins(0, 0, 0, 0);
        playlistShowButton = new QPushButton(gridLayoutWidget_2);
        playlistShowButton->setObjectName(QString::fromUtf8("playlistShowButton"));

        gridLayout_2->addWidget(playlistShowButton, 4, 1, 1, 1);

        deleteWLineEdit = new QLineEdit(gridLayoutWidget_2);
        deleteWLineEdit->setObjectName(QString::fromUtf8("deleteWLineEdit"));

        gridLayout_2->addWidget(deleteWLineEdit, 5, 0, 1, 1);

        nextButton = new QPushButton(gridLayoutWidget_2);
        nextButton->setObjectName(QString::fromUtf8("nextButton"));

        gridLayout_2->addWidget(nextButton, 0, 3, 1, 1);

        playlistDeleteButton = new QPushButton(gridLayoutWidget_2);
        playlistDeleteButton->setObjectName(QString::fromUtf8("playlistDeleteButton"));

        gridLayout_2->addWidget(playlistDeleteButton, 4, 0, 1, 1);

        filterButton = new QPushButton(gridLayoutWidget_2);
        filterButton->setObjectName(QString::fromUtf8("filterButton"));

        gridLayout_2->addWidget(filterButton, 0, 0, 1, 1);

        filterLineEdit = new QLineEdit(gridLayoutWidget_2);
        filterLineEdit->setObjectName(QString::fromUtf8("filterLineEdit"));

        gridLayout_2->addWidget(filterLineEdit, 0, 1, 1, 1);

        playlistAddButton = new QPushButton(gridLayoutWidget_2);
        playlistAddButton->setObjectName(QString::fromUtf8("playlistAddButton"));

        gridLayout_2->addWidget(playlistAddButton, 4, 3, 1, 1);

        playButton = new QPushButton(gridLayoutWidget_2);
        playButton->setObjectName(QString::fromUtf8("playButton"));

        gridLayout_2->addWidget(playButton, 1, 1, 1, 1);

        radioButton = new QRadioButton(gridLayoutWidget_2);
        radioButton->setObjectName(QString::fromUtf8("radioButton"));

        gridLayout_2->addWidget(radioButton, 5, 1, 1, 1);

        horizontalLayoutWidget = new QWidget(centralWidget);
        horizontalLayoutWidget->setObjectName(QString::fromUtf8("horizontalLayoutWidget"));
        horizontalLayoutWidget->setGeometry(QRect(450, 699, 171, 61));
        horizontalLayout = new QHBoxLayout(horizontalLayoutWidget);
        horizontalLayout->setSpacing(6);
        horizontalLayout->setContentsMargins(11, 11, 11, 11);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        horizontalLayout->setContentsMargins(0, 0, 0, 0);
        htmlButton = new QPushButton(horizontalLayoutWidget);
        htmlButton->setObjectName(QString::fromUtf8("htmlButton"));

        horizontalLayout->addWidget(htmlButton);

        csvButton = new QPushButton(horizontalLayoutWidget);
        csvButton->setObjectName(QString::fromUtf8("csvButton"));

        horizontalLayout->addWidget(csvButton);

        GUIFinalClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(GUIFinalClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 710, 21));
        GUIFinalClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(GUIFinalClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        GUIFinalClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(GUIFinalClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        GUIFinalClass->setStatusBar(statusBar);

        retranslateUi(GUIFinalClass);

        QMetaObject::connectSlotsByName(GUIFinalClass);
    } // setupUi

    void retranslateUi(QMainWindow *GUIFinalClass)
    {
        GUIFinalClass->setWindowTitle(QApplication::translate("GUIFinalClass", "GUIFinal", nullptr));
        label->setText(QApplication::translate("GUIFinalClass", "Movie list", nullptr));
        titleLabel->setText(QApplication::translate("GUIFinalClass", "Title:", nullptr));
        genreLabel->setText(QApplication::translate("GUIFinalClass", "Genre:", nullptr));
        yearLabel->setText(QApplication::translate("GUIFinalClass", "Year:", nullptr));
        likesLabel->setText(QApplication::translate("GUIFinalClass", "Likes:", nullptr));
        trailerLabel->setText(QApplication::translate("GUIFinalClass", "Trailer:", nullptr));
        addButton->setText(QApplication::translate("GUIFinalClass", "Add", nullptr));
        removeButton->setText(QApplication::translate("GUIFinalClass", "Remove", nullptr));
        updateButton->setText(QApplication::translate("GUIFinalClass", "Update", nullptr));
        label_2->setText(QApplication::translate("GUIFinalClass", "Watch list:", nullptr));
        playlistShowButton->setText(QApplication::translate("GUIFinalClass", "Show Watch list", nullptr));
        nextButton->setText(QApplication::translate("GUIFinalClass", "Next", nullptr));
        playlistDeleteButton->setText(QApplication::translate("GUIFinalClass", "Delete", nullptr));
        filterButton->setText(QApplication::translate("GUIFinalClass", "Filter", nullptr));
        playlistAddButton->setText(QApplication::translate("GUIFinalClass", "Add", nullptr));
        playButton->setText(QApplication::translate("GUIFinalClass", "Play", nullptr));
        radioButton->setText(QApplication::translate("GUIFinalClass", "Like", nullptr));
        htmlButton->setText(QApplication::translate("GUIFinalClass", "HTML", nullptr));
        csvButton->setText(QApplication::translate("GUIFinalClass", "CSV", nullptr));
    } // retranslateUi

};

namespace Ui {
    class GUIFinalClass: public Ui_GUIFinalClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_GUIFINAL_H
