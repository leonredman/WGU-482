package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


/**
 * This <b>"Inventory"</b> class which sets up the product and parts lists and manipulates the data. <br>
 */
public class Inventory {
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    // private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();  // filtered parts list

    public static int nextId = 0;

    /**
     * This <b>"createId"</b> method increments the part id's. <br>
     * @return
     */
    public static int createId(){
        nextId = ++nextId;
        return nextId;
    }


    /**
     * This <b>"addPart"</b> method adds a new part to the parts list. <br>
     * @param newPart
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }


    /**
     * This <b>"addProduct"</b> method adds a new product to the products list. <br>
     * @param newProduct
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }


    /**
     * This <b>"lookupPart"</b> method searches parts by id. <br>
     * @param partId
     * @return
     */
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


    /**
     * This <b>"lookupPart"</b> method searches parts by part name. <br>
     * @param partName
     * @return
     */
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


    /**
     * This <b>"lookupProduct"</b> method searches products by Id. <br>
     * @param productId
     * @return
     */
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


    /**
     * This <b>"lookupProduct"</b> method searches products by name. <br>
     * @param productName
     * @return
     */
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


    /**
     * This <b>"updatePart"</b> method updates part selected by user. <br>
     * @param index
     * @param selectedPart
     */
    // shared method to update part with an observable list
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }


    /**
     * This <b>"updateProduct"</b> method updates product selected by user. <br>
     * @param index
     * @param newProduct
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }


    /**
     * This <b>"deletePart"</b> method removes selected part by user. <br>
     * @param selectedPart
     * @return
     */
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


    /**
     * This <b>"deleteProduct"</b> method removes selected product by user. <br>
     * @param selectedProduct
     * @return
     */
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


    /**
     * This <b>"getAllParts"</b> method gets all parts from list. <br>
     * @return
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }


    /**
     * This <b>"getAllProducts"</b> method gets all products from list. <br>
     * @return
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}


