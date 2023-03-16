package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private int id;
    private String name;
    private int stock;
    private double price;
    private int max;
    private int min;

    public static ObservableList<Part> associatedParts = FXCollections.observableArrayList();


    public Product(int id, String name, int stock, double price, int min, int max) {

     // this.id = Inventory.nextId;
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.max = max;
        this.min = min;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    // unfinished feature methods ???
    //  public void setAssociated(boolean associated) {
    //     this.associated = associated;
    //  }

    // add a part to the list
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);

        return true;
    }

    public ObservableList<Part> getAllAssociatedParts() {

        return associatedParts;
    }
}
