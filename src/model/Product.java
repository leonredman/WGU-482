package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * The product class is used to describe products tracked in inventory. <br>
 */

public class Product {
    private int id;
    private String name;
    private int stock;
    private double price;
    private int max;
    private int min;

    //List of Parts "Associated" with Product
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    public Product(int id, String name, int stock, double price, int max, int min) {
        // Basic Values of Product
     // this.id = Inventory.nextId;
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.max = max;
        this.min = min;
    }

    /**
     * This <b>"getId"</b> method is the getter for the class. <br>
     * @return
     */
    // Getters and Setters
    public int getId() {
        return id;
    }


    /**
     * This <b>"setId"</b> method is the setter for the class. <br>
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * This <b>"getName"</b> method is the getter for the class. <br>
     * @return
     */
    public String getName() {
        return name;
    }


    /**
     * This <b>"setName"</b> method is the setter for the class. <br>
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * This <b>"getPrice"</b> method is the getter for the class. <br>
     * @return
     */
    public double getPrice() {
        return price;
    }


    /**
     * This <b>"setPrice"</b> method is the setter for the class. <br>
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }


    /**
     * This <b>"getStock"</b> method is the getter for the class. <br>
     * @return
     */
    public int getStock() {
        return stock;
    }


    /**
     * This <b>"setStock"</b> method is the setter for the class. <br>
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }


    /**
     * This <b>"getMin"</b> method is the getter for the class. <br>
     * @return
     */
    public int getMin() {
        return min;
    }


    /**
     * This <b>"setMin"</b> method is the setter for the class. <br>
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }


    /**
     * This <b>"getMax"</b> method is the getter for the class. <br>
     * @return
     */
    public int getMax() {
        return max;
    }


    /**
     * This <b>"setMax"</b> method is the setter for the class. <br>
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }


    /**
     * This <b>"addAssociatedPart"</b> method adds associated part to list. <br>
     * @param part
     */
    // adding  a part to the Product list
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }


    /**
     * This <b>"deleteAssociatedPart"</b> method removes associated part to list. <br>
     * @param selectedAssociatedPart
     * @return
     */
        public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);

        Alert partDeleteSuccessful = new Alert(Alert.AlertType.INFORMATION);
        partDeleteSuccessful.setTitle("Confirmation Message");
        partDeleteSuccessful.setContentText("The Associated part was deleted");
        partDeleteSuccessful.showAndWait();

    return true;
   }



    /**
     * This <b>"getAllAssociatedParts"</b> method gets all associated part from list. <br>
     * @return
     */
    // getting "The Associated Parts List" for the Product
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}

