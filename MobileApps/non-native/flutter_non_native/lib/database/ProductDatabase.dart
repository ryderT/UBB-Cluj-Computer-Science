import 'dart:async';
import 'package:flutter/widgets.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_non_native/domain/Product.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';


class ProductDatabase{
  Future<Database> database;

  ProductDatabase(){
    WidgetsFlutterBinding.ensureInitialized();
    database= connect();
  }
  Future<Database> connect() async {
    return openDatabase(
      join(await getDatabasesPath(),'products.db'),
      onCreate:(db,version){
        return db.execute(
          "create table products(id integer primary key autoincrement, name text, price integer, quantity integer)",
        );
      },
      version: 1,
    );
  }

  Future<void> insertProduct(Product product) async{
    final Database db =  await database;
    await db.insert('products', product.toMap(),conflictAlgorithm: ConflictAlgorithm.replace);
  }

  Future<List<Product>> products() async{
    final Database db = await database;
    final List<Map<String, dynamic>> maps = await db.query('products');
    return List.generate(maps.length, (index) {
      return Product(
        id: maps[index]['id'],
        name: maps[index]['name'],
        price: maps[index]['price'],
        quantity: maps[index]['quantity'],
      );
    });
  }
  Future<void> updateProduct(Product product) async{
    final db = await database;
    await db.update(
      'products',
      product.toMap(),
      where: "id = ?",
      whereArgs: [product.id],
    );
  }
  Future<void> deleteProduct(int id) async {
    final db = await database;

    await db.delete(
      'products',
      where: "id = ?",
      whereArgs: [id],
    );
  }

}