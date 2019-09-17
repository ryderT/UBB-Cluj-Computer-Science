/****************************************************************************
** Meta object code from reading C++ file 'GUIFinal.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.12.3)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../GUIFinal.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'GUIFinal.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.12.3. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
struct qt_meta_stringdata_GUIFinal_t {
    QByteArrayData data[13];
    char stringdata0[120];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_GUIFinal_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_GUIFinal_t qt_meta_stringdata_GUIFinal = {
    {
QT_MOC_LITERAL(0, 0, 8), // "GUIFinal"
QT_MOC_LITERAL(1, 9, 8), // "addMovie"
QT_MOC_LITERAL(2, 18, 0), // ""
QT_MOC_LITERAL(3, 19, 11), // "removeMovie"
QT_MOC_LITERAL(4, 31, 11), // "updateMovie"
QT_MOC_LITERAL(5, 43, 4), // "next"
QT_MOC_LITERAL(6, 48, 4), // "play"
QT_MOC_LITERAL(7, 53, 6), // "filter"
QT_MOC_LITERAL(8, 60, 12), // "addWatchlist"
QT_MOC_LITERAL(9, 73, 13), // "showWatchlist"
QT_MOC_LITERAL(10, 87, 15), // "deleteWatchlist"
QT_MOC_LITERAL(11, 103, 8), // "saveHTML"
QT_MOC_LITERAL(12, 112, 7) // "saveCSV"

    },
    "GUIFinal\0addMovie\0\0removeMovie\0"
    "updateMovie\0next\0play\0filter\0addWatchlist\0"
    "showWatchlist\0deleteWatchlist\0saveHTML\0"
    "saveCSV"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_GUIFinal[] = {

 // content:
       8,       // revision
       0,       // classname
       0,    0, // classinfo
      11,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // slots: name, argc, parameters, tag, flags
       1,    0,   69,    2, 0x08 /* Private */,
       3,    0,   70,    2, 0x08 /* Private */,
       4,    0,   71,    2, 0x08 /* Private */,
       5,    0,   72,    2, 0x08 /* Private */,
       6,    0,   73,    2, 0x08 /* Private */,
       7,    0,   74,    2, 0x08 /* Private */,
       8,    0,   75,    2, 0x08 /* Private */,
       9,    0,   76,    2, 0x08 /* Private */,
      10,    0,   77,    2, 0x08 /* Private */,
      11,    0,   78,    2, 0x08 /* Private */,
      12,    0,   79,    2, 0x08 /* Private */,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

void GUIFinal::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<GUIFinal *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->addMovie(); break;
        case 1: _t->removeMovie(); break;
        case 2: _t->updateMovie(); break;
        case 3: _t->next(); break;
        case 4: _t->play(); break;
        case 5: _t->filter(); break;
        case 6: _t->addWatchlist(); break;
        case 7: _t->showWatchlist(); break;
        case 8: _t->deleteWatchlist(); break;
        case 9: _t->saveHTML(); break;
        case 10: _t->saveCSV(); break;
        default: ;
        }
    }
    Q_UNUSED(_a);
}

QT_INIT_METAOBJECT const QMetaObject GUIFinal::staticMetaObject = { {
    &QMainWindow::staticMetaObject,
    qt_meta_stringdata_GUIFinal.data,
    qt_meta_data_GUIFinal,
    qt_static_metacall,
    nullptr,
    nullptr
} };


const QMetaObject *GUIFinal::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *GUIFinal::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_GUIFinal.stringdata0))
        return static_cast<void*>(this);
    return QMainWindow::qt_metacast(_clname);
}

int GUIFinal::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QMainWindow::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 11)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 11;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 11)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 11;
    }
    return _id;
}
QT_WARNING_POP
QT_END_MOC_NAMESPACE
