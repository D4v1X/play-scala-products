package models

/**
  * Created by d4v1x on 09/10/2016.
  */
case class Product(ean: String, name: String, description: String)

object Product {
  var products = Set(
    Product("5010255079763", "Paperclips Large",
      "Large Plain Pack of 1000"),
    Product("5018206244666", "Giant Paperclips",
      "Giant Plain 51mm 100 pack"),
    Product("5018306332812", "Paperclip Giant Plain",
      "Giant Plain Pack of 10000"),
    Product("5018306312913", "No Tear Paper Clip",
      "No Tear Extra Large Pack of 1000"),
    Product("5018206244611", "Zebra Paperclips",
      "Zebra Length 28mm Assorted 150 Pack")
  )

  def findAll = products.toList.sortBy(_.ean)

  def findByEan(ean: String) = products.find(_.ean == ean)

  def add(product: Product): Unit = {
    products = products + product
  }
}
