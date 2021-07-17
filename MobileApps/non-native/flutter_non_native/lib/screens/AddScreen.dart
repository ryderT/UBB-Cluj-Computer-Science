import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_non_native/domain/Product.dart';
import 'package:flutter_non_native/notifier/ProductNotifier.dart';
import 'package:provider/provider.dart';

class AddScreen extends StatefulWidget{
  @override
  AddProductState createState() {
    return AddProductState();
  }
}

class AddProductState extends State<AddScreen>{
  final key = GlobalKey<FormState>();
  final nameController=TextEditingController();
  final priceController=TextEditingController();
  final quantityController=TextEditingController();


  @override
  Widget build(BuildContext context) {
    ProductNotifier productNotifier = Provider.of<ProductNotifier>(context);
    return Scaffold(
      appBar: AppBar(
        automaticallyImplyLeading: false,
        title: Text('Add Product'),
        backgroundColor: Colors.transparent,
        elevation: 0.0,
        centerTitle: false,
      ),
      body: Container(
        margin: EdgeInsets.fromLTRB(17, 0, 17, 0),
        child: SingleChildScrollView(
          child: Form(
            key: key,
            child: Column(
              children: <Widget>[
                TextFormField(
                  controller: nameController,
                  decoration: InputDecoration(labelText: 'Product Name:'),
                  validator: (value){
                    if (value.isEmpty){
                      return "Title is empty!";
                    }
                    return null;
                  },
                ),
                TextFormField(
                  controller: priceController,
                  decoration: InputDecoration(labelText: 'Product Price:'),
                  validator: (value){
                    if (value.isEmpty){
                      return "Price is empty!";
                    }
                    return null;
                  },
                ),
                TextFormField(
                  controller: quantityController,
                  decoration: InputDecoration(labelText: 'Product Quantity:'),
                  validator: (value){
                    if (value.isEmpty){
                      return "Quantity is empty!";
                    }
                    return null;
                  },
                ),
                ElevatedButton(
                    onPressed: () {
                      if(key.currentState.validate()){
                        setState(() {
                          productNotifier.add(nameController.text, int.parse(priceController.text), int.parse(quantityController.text));
                        });
                        Navigator.of(context).popUntil((route) => route.isFirst);
                      }
                    },
                    child: Text("Add Product"),
                    style: ElevatedButton.styleFrom(
                      primary: Colors.green,
                    ),
                ),
              ],
              crossAxisAlignment: CrossAxisAlignment.stretch,
              mainAxisAlignment: MainAxisAlignment.start,
            ),
          ),
          padding: EdgeInsets.fromLTRB(14, 0, 14, 0),
        )
      ),
    );
  }

}