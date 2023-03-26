package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;



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
    public static Part lookupPart(int partId) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        // Enhanced loop option
        for(Part part: allParts){
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }


    // Look Up PartName String - shared method to lookup part by string-part name
    public static ObservableList<Part> lookupPart (String partName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part part: allParts) {
            if(part.getName().contains(partName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }


    // Look Up Product id - shared method to lookup product by id
    public static Product lookupProduct (int productId){
    ObservableList<Product> allProducts = Inventory.getAllProducts();
    // Enhanced loop option
        for(Product product: allProducts){
        if (product.getId() == productId) {
            return product;
        }
    }
        return null;
}


    // Look Up Product Name String - shared method to lookup product by string-product name
    public static ObservableList<Product> lookupProduct (String productName){
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for (Product product: allProducts) {
            if (product.getName().contains(productName)) {
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
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }


    // method to delete selected part from list - returns true if part is deleted
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            Alert partDeleteSuccessful = new Alert(Alert.AlertType.INFORMATION);
            partDeleteSuccessful.setTitle("Confirmation Message");
            partDeleteSuccessful.setContentText("The part was deleted");
            partDeleteSuccessful.showAndWait();

            return true;
        } else {
            return false;
        }
    }


    // returns true if selected product is deleted
    public static boolean deleteProduct(Product selectedProduct) {
        System.out.println("Inventory Product Delete Fired");
        if(allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            Alert productDeleteSuccessful = new Alert(Alert.AlertType.INFORMATION);
            productDeleteSuccessful.setTitle("Confirmation Message");
            productDeleteSuccessful.setContentText("The product was deleted");
            productDeleteSuccessful.showAndWait();
            return true;
        } else {
            return false;
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


}


