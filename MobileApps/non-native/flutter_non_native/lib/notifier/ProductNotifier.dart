import 'dart:collection';
import 'package:flutter/cupertino.dart';
import 'package:flutter_non_native/database/ProductDatabase.dart';
import 'package:flutter_non_native/domain/Product.dart';

class ProductNotifier with ChangeNotifier{
  ProductDatabase  productDB;
  List<Product> productsList;

  ProductNotifier(){
  productDB=ProductDatabase();
  productsList=List<Product>();
  loadData();
  }

  void loadData() async{
    productsList = await productDB.products();
    notifyListeners();
  }

  UnmodifiableListView<Product> get products => UnmodifiableListView(productsList);

  void add(String name, int price, int quantity){
    productDB.insertProduct(Product(
      id:null,
      name:name,
      price:price,
      quantity: quantity
    ));
    loadData();
  }

  void update(int id, String name, int price, int quantity){
    productDB.updateProduct(Product(
      id:id,
      name:name,
      price:price,
      quantity: quantity
    ));
    loadData();
  }

  void remove(int id){
    productDB.deleteProduct(id);
    loadData();
  }
}