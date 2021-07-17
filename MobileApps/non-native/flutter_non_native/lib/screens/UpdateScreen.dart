import 'package:flutter/cupertino.dart';
import 'package:flutter_non_native/domain/Product.dart';
import 'package:flutter_non_native/notifier/ProductNotifier.dart';
import 'package:provider/provider.dart';
import 'package:flutter/material.dart';

class UpdateScreen extends StatefulWidget{
  final Product product;
  const UpdateScreen({Key key, @required this.product}) : super(key: key);

  @override
  UpdateProductState createState() {
    return UpdateProductState(product);
  }
}

class UpdateProductState extends State<UpdateScreen> {
  final Product product;
  final key = GlobalKey<FormState>();
  final nameController=TextEditingController();
  final priceController=TextEditingController();
  final quantityController=TextEditingController();

  UpdateProductState(this.product);

  @override
  Widget build(BuildContext context) {
    ProductNotifier productNotifier = Provider.of<ProductNotifier>(context);
    nameController.text=product.name;
    priceController.text=product.price.toString();
    quantityController.text=product.quantity.toString();

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
                          productNotifier.update(product.id,nameController.text, int.parse(priceController.text), int.parse(quantityController.text));
                        });
                        Navigator.of(context).popUntil((route) => route.isFirst);
                      }
                    },
                    child: Text("Update Product"),
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