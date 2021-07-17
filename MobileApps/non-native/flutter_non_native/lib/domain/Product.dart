class Product {
  int id;
  String name;
  int price;
  int quantity;

  Product(
      {this.id,
        this.name,
        this.price,
        this.quantity
        });

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'name':name,
      'price':price,
      'quantity':quantity,
    };
  }

  @override
  String toString() {
    return this.name;
  }
}
