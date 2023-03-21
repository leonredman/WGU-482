package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Product;
import wgu.inventoryApp.MainApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductViewController implements Initializable {

    // Our list of assoc parts
    private ObservableList<Part> associatedPartsLst = FXCollections.observableArrayList();

    @FXML
    private TextField addProductSearch;
    @FXML
    private TableColumn associatedProductPartId;
    @FXML
    private TableColumn associatedProductPartName;
    @FXML
    private TableColumn associatedProductPartInventory;
    @FXML
    private TableColumn associatedProductPartPrice;
    @FXML
    private TableView associatedProductTable;
    @FXML
    private TableView addProductTable;
    @FXML
    private TableColumn addProductIdTxt;
    @FXML
    private TableColumn addProductNameText;
    @FXML
    private TableColumn addProductStockTxt;
    @FXML
    private TableColumn addProductPriceTxt;
    @FXML
    private Button thirdButton;
    @FXML
    private TableView addProductNameTxt;
    @FXML
    private TextField addProductInvField;
    @FXML
    private TextField addProductPriceField;
    @FXML
    private TextField addProductMaxField;
    @FXML
    private TextField addProductMinField;
    @FXML
    private TextField addProductNameField;
    @FXML
    private TextField addProductIdField;


    // Cancel Button sends back to main
    public void addProdBackToMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        //Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1200, 450);
        stage.setTitle("Back To Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    // add product screen search feature event handler
    public void onAddProductSearch(ActionEvent actionEvent) {
        String q = addProductSearch.getText();

        ObservableList<Part> parts = filter(q);

        if (parts.size() == 0){
            try {
                int id = Integer.parseInt(q);
                Part part = addPartsSearchWithID(id);
                if (part != null)
                    parts.add(part);
            }
            catch(NumberFormatException e) {
                // catch and ignore
            }
        }
        addProductTable.setItems(parts);
    }


    // AddParts Search Filter by id
    private Part addPartsSearchWithID (int id){
        ObservableList<Part> allParts = Inventory.getAllParts();
        // Enhanced loop option
        for(Part part: allParts){
            if (part.getId() == id) {
                return part;
            }
        }
        return null;
    }


    // AddParts Search Filter Using  Partial Name  with this filter instead
    private ObservableList<Part> filter (String partialName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part part: allParts) {
            if(part.getName().contains(partialName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }


    // btn add Associated part to product list
    public void onAddProductBtn(ActionEvent actionEvent) {
        Part selectedPart = (Part) addProductTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("User Error");
            alert.setContentText("You must select a part to add from the list");
            alert.showAndWait();
            return;
        } else if (!associatedPartsLst.contains(selectedPart)){
            associatedPartsLst.add(selectedPart);
            associatedProductTable.setItems(associatedPartsLst);
        }
    }


    // Save Button - Adds Product and goes back to main
    public void onSaveProductBtn(ActionEvent actionEvent) throws IOException{

         int id = Inventory.createId();
         String name = addProductNameField.getText();
         int stock = Integer.parseInt(addProductInvField.getText());
         Double price = Double.parseDouble(addProductPriceField.getText());
         int max = Integer.parseInt(addProductMaxField.getText());
         int min = Integer.parseInt(addProductMinField.getText());

        if (min > stock || stock < max) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory requirements: Inventory must be within min and max.");
            alert.showAndWait();
        } else if (min >= max) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory requirements: maximum must be greater than minimum");
            alert.showAndWait();
        }

        Product addProduct = new Product(id, name, stock, price, max, min);
        for (Part part: associatedPartsLst) {
            if (part != associatedPartsLst)
                addProduct.addAssociatedPart(part);
        }
        Inventory.addProduct(addProduct);

        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        //Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1200, 450);
        stage.setTitle("Back To Main Screen");
        stage.setScene(scene);
        stage.show();
    }


    // Remove Associated part from product list
    public void onRemoveAssociatedPartBtn(ActionEvent actionEvent) {

        Part selectedPart = (Part)associatedProductTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("User Error");
            alert.setContentText("You must select part to remove from the list");
            alert.showAndWait();
            return;
        }
        else if (associatedPartsLst.contains(selectedPart)); {
            associatedPartsLst.remove(selectedPart);
            associatedProductTable.setItems(associatedPartsLst);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // on init loads all parts to table - same as main screen
        addProductTable.setItems(Inventory.getAllParts());

        // set columns to list
        addProductIdTxt.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductNameText.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductStockTxt.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductPriceTxt.setCellValueFactory(new PropertyValueFactory<>("price"));

        // set associated parts on the table
        associatedProductTable.setItems(associatedPartsLst);
        associatedProductPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedProductPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedProductPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedProductPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
