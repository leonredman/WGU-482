package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//  this class is like the Fruit bowl

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


//    public static void addPart(Part newPart) {
//        allParts.add(newPart);
//    }
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        return null;                                        // need code to return
    }


    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}


/*

Alternate solution but unsure if all is required in this code
 public class Inventory {   // correct  this is like the Fruit bowl

     private static Inventory inventory;    // not sure required

     private Inventory () {  // not sure required

}
    public static Inventory getInstance() {
        if (null == inventory){
            inventory = new Inventory();
        }
        return inventory;
    }

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    public static void addPart(Part newPart) {
    }
    public static void addProduct(Product newProduct) {

    }
}
 */