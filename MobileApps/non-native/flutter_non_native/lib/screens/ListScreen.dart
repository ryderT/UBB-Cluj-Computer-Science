import 'package:flutter/material.dart';
import 'package:flutter_non_native/domain/Product.dart';
import 'package:provider/provider.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_non_native/notifier/ProductNotifier.dart';

import 'AddScreen.dart';
import 'UpdateScreen.dart';

class ListScreen extends StatefulWidget{
  final String title;
  ListScreen({Key key, this.title}):super(key:key);
  @override
  ListState createState() => ListState();
}

class ListState extends State<ListScreen>{
  @override
  Widget build(BuildContext context) {
    ProductNotifier productNotifier = Provider.of<ProductNotifier>(context);

    return Scaffold(
      backgroundColor: Colors.grey.shade200,
      appBar: AppBar(
        title: Text('Products:'),
        backgroundColor: Colors.transparent,
        elevation: 0.0,
      ),
      body: Container(
        margin: EdgeInsets.fromLTRB(17, 0, 17, 0),
        child: ListView.builder(
            itemCount: productNotifier.products.length,
            itemBuilder:(BuildContext context, int index){
              return Container(
                margin: EdgeInsets.fromLTRB(0, 17, 0, 0),
                child: Card(
                  child: Row(
                    children: [
                      Column(
                        children:[
                          Text(productNotifier.products[index].name),
                          Text("Price: "+productNotifier.products[index].price.toString() + " Quantity: " + productNotifier.products[index].quantity.toString()),
                        ],

                      ),
                      ElevatedButton(
                                onPressed: () {
                                  //productNotifier.remove(productNotifier.products[index].id);
                                  showDeleteDialog(context, productNotifier,productNotifier.products[index].id);
                                },
                                child: Text('Delete')
                            ),
                      ElevatedButton(
                                onPressed: () {
                                  navigateToUpdate(context, productNotifier.products[index]);
                                },
                                child: Text('Update')
                            )
                    ],
                  )
                  // child: ListTile(
                  //   title: Text(productNotifier.products[index].name),
                  //   subtitle: Text("Price: "+productNotifier.products[index].price.toString() + " Quantity: " + productNotifier.products[index].quantity.toString()),
                  //   trailing: Container(
                  //     child: Row(
                  //       children: [
                  //         ElevatedButton(
                  //             onPressed: () {
                  //               //productNotifier.remove(productNotifier.products[index].id);
                  //               showDeleteDialog(context, productNotifier,productNotifier.products[index].id);
                  //             },
                  //             child: Text('Delete')
                  //         ),
                  //         ElevatedButton(
                  //             onPressed: () {
                  //               navigateToUpdate(context, productNotifier.products[index]);
                  //             },
                  //             child: Text('Update')
                  //         )
                  //       ],
                  //     ),
                  //   )
                  //
                  // )

                )

              );

            }

        )



        ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: () {
          navigateToAddScreen(context);
        },

    ),
      );



  }

  void showDeleteDialog(BuildContext context, ProductNotifier productNotifier ,int id) async{
    return showDialog<void>(
      context:context,
      barrierDismissible: false,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text(
            'Are you sure you want to delete this Product?',
            textAlign: TextAlign.center,
          ),
          actions: <Widget>[
            TextButton(
              child: Text('DELETE'),
              onPressed: () {
                productNotifier.remove(id);
                Navigator.of(context).pop();
              },
            ),
            TextButton(
              child: Text('CANCEL'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  void navigateToUpdate(BuildContext context, Product product) async{
    await Navigator.push(
      context,
      MaterialPageRoute(builder:(context) =>UpdateScreen(product: product)),
    );

  }

  void navigateToAddScreen(BuildContext context) async{
    await Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => AddScreen())
    );
  }


}

