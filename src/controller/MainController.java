package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import wgu.inventoryApp.MainApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public int partCount = 1;

    public int productCount = 1;
    public Label TheLabel;
    public TextField partsSearchFieldMain;
    public Button removePart;
    public Button modifyPart;
    public Button addPart;
    public TextField productsSearchFieldMain;
    public Label productsTitle;
    public Button deleteProduct;
    public Button modifyProduct;
    public Button addProduct;
    public Label partsTitleText;

    public TableView partsTable;
    public TableColumn partIdCol;
    public TableColumn partNameCol;
    public TableColumn partInventoryLevelCol;
    public TableColumn partPricePerUnitCol;


    public TableView productsTable;
    public TableColumn productIdCol;
    public TableColumn productNameCol;
    public TableColumn productInventoryLevelCol;
    public TableColumn productPricePerUnitCol;

// linear search method
    public boolean search(int id)
    {
        for (Part part : Inventory.getAllParts()) {
            if (part.getId() == id)

                return true;
        } // else
        return false;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");

        // associate lists with table
        productsTable.setItems(Inventory.getAllProducts());
        partsTable.setItems(Inventory.getAllParts());

        // associate/ bind columns with table
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // call linear search in initialize method
        if(search(3))
            System.out.println("match found");
        else System.out.println("No match found");

    }



    @FXML
    protected void modifyPartFired() {
        // welcomeText.setText("Modify Part Fired Leon!");
        System.out.println("Modify Part button is clicked");
        TheLabel.setText("You clicked the Modify Part button, Total Number of clicks is: " + partCount++);
    }

    @FXML
    protected void removePartFired() {
        // welcomeText.setText("Delete Part Fired Leon!");
        System.out.println("Delete Part button is clicked");
        TheLabel.setText("You clicked the Delete Part button, Total Number of clicks is: " + partCount++);
    }

    public void addProductFired(ActionEvent actionEvent) {
        System.out.println("Add Product Fired");
        TheLabel.setText("You clicked the Add Product button, Total Number of clicks is: " + productCount++);
    }

    public void modifyProductFired(ActionEvent actionEvent) {
        System.out.println("Product Modify Fired");
        TheLabel.setText("You clicked the Modify Product button, Total Number of clicks is: " + productCount++);
    }

    public void deleteProductFired(ActionEvent actionEvent) {
        System.out.println("Product Delete Fired");
        TheLabel.setText("You clicked the Delete Product button, Total Number of clicks is: " + productCount++);

    }

    // From main to 'add Part'
    public void toSecond(ActionEvent actionEvent) throws IOException {
        // Parent root = FXMLLoader.load(HelloApplication.class.getResource("/main/resources/wgu/firstscreen/addPart.fxml "));
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/addPart.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Part Screen");
        stage.setScene(scene);
        stage.show();

    }

    // From main to 'add Product'
    public void toAddProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/addProduct.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Product Screen");
        stage.setScene(scene);
        stage.show();

    }

    // From main to 'modifyPart'
    public void toModifyPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/modifyPart.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Part Screen");
        stage.setScene(scene);
        stage.show();
    }

    // From main to modifyProduct
    public void toModifyProduct(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/modifyProduct.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Product Screen");
        stage.setScene(scene);
        stage.show();
    }
}
