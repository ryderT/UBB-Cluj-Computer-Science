import 'package:flutter/material.dart';
import 'package:flutter_non_native/notifier/ProductNotifier.dart';
import 'package:provider/provider.dart';
import 'package:flutter_non_native/screens/ListScreen.dart';

void main() => runApp(
  ChangeNotifierProvider(
      create: (context) => ProductNotifier(),
    child: Grogu(),
  )
);

class Grogu extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Grogu',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: ListScreen(title:'Grogu'),
    );
  }
}

