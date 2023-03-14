package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//  this class is like the Fruit bowl
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

   // private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();   // filtered parts list


    public static int nextId = 0;

    public static int createId(){
        nextId = ++nextId;
        return nextId;
    }


    // global function to create id for parts
//    public static int createPartId(int baseId) {
//        int newPartId = 0;
//        for (int i = 0; i < Inventory.getAllParts().size(); i++) {
//            newPartId = baseId * 2;
//        }
//        return newPartId;
//
    // int n = i * 2;   n is the increment amount
    // nextId = nextId + n
//   }

    // global function to create id for parts
//    public static int createProductId(int baseId) {
//        int newProductId = 0;
//
//        return newProductId;
//    }



// method adds part to allParts observable list
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        // todo
        return null;
    }

    public static Product lookupProduct (int productId){
        // todo
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName){
        // todo
        //return PartName;

        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName){
        // todo
        //return ProductName;

        return null;
    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }


    public static void updateProduct(int index, Product newProduct) {
        return ;
    }

    public static boolean deletePart(Part selectedPart){
        return true;
    }

    public static boolean deleteProduct(Product selectedProduct){
        return true;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

//    public static ObservableList<Part> getAllFilteredParts() {
//        return filteredParts;
//    }


}

