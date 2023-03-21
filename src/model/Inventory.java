package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//  this class is like the Fruit bowl

public class Inventory {
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    // private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();   // filtered parts list

    public static int nextId = 0;

    public static int createId(){
        nextId = ++nextId;
        return nextId;
    }


    // shared method adds part to allParts observable list
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }


    // shared method adds part to allParts observable list
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }


    // Look Up Part id -shared method looks up part by id
    public static Part lookupPart(int id) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        // Enhanced loop option
        for(Part part: allParts){
            if (part.getId() == id) {
                return part;
            }
        }
        return null;
    }


    // Look Up Part String - shared method to lookup part by string-part name
    public static ObservableList<Part> lookupPart (String partialName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part part: allParts) {
            if(part.getName().contains(partialName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }


    // Look Up Product id - shared method to lookup product by id
    public static Product lookupProduct (int id){
    ObservableList<Product> allProducts = Inventory.getAllProducts();
    // Enhanced loop option
        for(Product product: allProducts){
        if (product.getId() == id) {
            return product;
        }
    }
        return null;
}


    // Look Up Product String - shared method to lookup product by string-product name
    public static ObservableList<Product> lookupProduct (String partialProdName){
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for (Product product: allProducts) {
            if (product.getName().contains(partialProdName)) {
                namedProducts.add(product);
            }
        }
        return namedProducts;
    }


    // shared method to update part with an observable list
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }


    // method to update product with an observable list
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }



    // method to delete selected part from list
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);

            return true;
        } else {
            return false;
        }
    }
    

    // method to delete product from selected list
//    public static boolean deleteProduct(Product selectedProduct){
//        return true;
//    }

//    public static boolean deleteProduct(Product selectedProduct){
//        return true;
//    }

    public static boolean deleteProduct(Product selectedProduct) {
        System.out.println("Inventory Product Delete Fired");
        if(allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;

//        ObservableList<Product> selectedRows,allProducts;
//        allProducts = productsTable.getItems();
//        selectedRows = productsTable.getSelectionModel().getSelectedItems();
//        for (Product product: selectedRows) {
//            allProducts.remove(product);
        }
    }

    
    // method to get all parts from list
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }


    // method to get all products from list
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }


    // method to get all parts from a filtered list
//    public static ObservableList<Part> getAllFilteredParts() {
//        return filteredParts;
//    }

}


//-------------------------------------------------------
// global function to create id for parts

//    public static int createPartId(int baseId) {
//        int newPartId = 0;
//        for (int i = 0; i < Inventory.getAllParts().size(); i++) {
//            newPartId = baseId * 2;
//        }
//        return newPartId;
//
//     int n = i * 2;   n is the increment amount
//     nextId = nextId + n
//   }

// global function to create id for parts

//    public static int createProductId(int baseId) {
//        int newProductId = 0;
//
//        return newProductId;
//    }
