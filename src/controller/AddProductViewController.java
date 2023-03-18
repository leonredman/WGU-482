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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import wgu.inventoryApp.MainApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductViewController implements Initializable {

    private ObservableList<Part> associatedPartsLst = FXCollections.observableArrayList();

    @FXML
    private TextField modProductSearch;
    @FXML
    private TableView modifyProductPartsTable;
    @FXML
    private TableView modAssociatedPartsTable;
    @FXML
    private TableColumn modAssocProdPartIdColumn;
    @FXML
    private TableColumn modAssocProdPartNameColumn;
    @FXML
    private TableColumn modAssocProdPartStockColumn;
    @FXML
    private TableColumn modAssocProdPartPriceColumn;
    @FXML
    private TableColumn modifyProdPartIdColumn;
    @FXML
    private TableColumn modifyProdPartNameColumn;
    @FXML
    private TableColumn modifyProdStockColumn;
    @FXML
    private TableColumn modifyProdPartPriceColumn;
    @FXML
    private TextField prodModIdLbl;
    @FXML
    private TextField productModNameLbl;
    @FXML
    private TextField productModInventoryLbl;
    @FXML
    private TextField productModPriceLbl;
    @FXML
    private TextField productModMaxVal;
    @FXML
    private TextField prodModMinVal;

    private int currentIndex = 0;

    // Cancel Button
    public void toMainFromModifyProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        // Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1200, 450);
        stage.setTitle("Back To Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    // Sets text to Product Update fields
    public void sendProduct(Product product) {

            prodModIdLbl.setText(String.valueOf(product.getId()));
            productModNameLbl.setText(product.getName());
            productModInventoryLbl.setText(String.valueOf(product.getStock()));
            productModPriceLbl.setText(String.valueOf(product.getPrice()));
            productModMaxVal.setText(String.valueOf(product.getMax()));
            prodModMinVal.setText(String.valueOf(product.getMin()));

            for (Part part: product.getAllAssociatedParts()) {
                associatedPartsLst.add(part);
            }
        }


 // Init and set products and parts tables
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // on init loads all parts to table - same as main screen
        modifyProductPartsTable.setItems(Inventory.getAllParts());

        // set columns to list
        modifyProdPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProdPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProdStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProdPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // set associated parts on the table
        modAssociatedPartsTable.setItems(associatedPartsLst);
        modAssocProdPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modAssocProdPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modAssocProdPartStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modAssocProdPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    // Add part to associated parts list
    public void addModifiedAssocPart(ActionEvent actionEvent) {

        Part selectedPart = (Part) modifyProductPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("User Error");
            alert.setContentText("You must select a part to add from the list");
            alert.showAndWait();
       //   return;
        }
        else if (!associatedPartsLst.contains(selectedPart)) {
            associatedPartsLst.add(selectedPart);
            modAssociatedPartsTable.setItems(associatedPartsLst);
        }
    }

    public void saveModifiedAssocPart(ActionEvent actionEvent) throws IOException {

        int id = Integer.parseInt(prodModIdLbl.getText());
        String name = productModNameLbl.getText();
        int stock = Integer.parseInt(productModInventoryLbl.getText());
        double price = Double.parseDouble(productModPriceLbl.getText());
        int max = Integer.parseInt(productModMaxVal.getText());
        int min = Integer.parseInt(prodModMinVal.getText());

        if (stock > max || stock < min) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory requirements: Inventory Amount must be within min and max range.");
            alert.showAndWait();
            return;
        } else if (min >= max) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory requirements: Max amount must be greater than Min amount");
            alert.showAndWait();
            return;
        }

        Product modifiedProduct = new Product(id, name,stock, price, min, max);
        if (modifiedProduct != associatedPartsLst) {
            Inventory.updateProduct(currentIndex, modifiedProduct);
        }

        for (Part part: associatedPartsLst) {
            if (part != associatedPartsLst)
                modifiedProduct.addAssociatedPart(part);
        }

        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 450);
        stage.setTitle("Back To Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    public void toRemoveAssociatedPart(ActionEvent actionEvent) {
//        Part selectedPart = (Part) modAssociatedPartsTable.getSelectionModel().getSelectedItem();
//
//        if (selectedPart == null) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Input Error");
//            alert.setContentText("Select part from list");
//            alert.showAndWait();
//            return;
//        } else if (associatedPartsLst.contains(selectedPart)) {
//            Product.deleteAssociatedPart(selectedPart);
//            associatedPartsLst.remove(selectedPart);
//            modAssociatedPartsTable.setItems(associatedPartsLst);
//        }
    }

    public void onModProductPartSearch(ActionEvent actionEvent) {

        String q = modProductSearch.getText();

        ObservableList<Part> parts = filter(q);

        if (parts.size() == 0){
            try {
                int id = Integer.parseInt(q);
                Part part = modProductsSearchPartsWithID(id);
                if (part != null)
                    parts.add(part);
            }
            catch(NumberFormatException e) {
                // catch and ignore
            }
        }
        modifyProductPartsTable.setItems(parts);
    }



    // Mod Products Parts Search Filter by id
    private Part modProductsSearchPartsWithID (int id){
        ObservableList<Part> allParts = Inventory.getAllParts();
        // Enhanced loop option
        for(Part part: allParts){
            if (part.getId() == id) {
                return part;
            }
        }
        return null;
    }

    // ModProducts Parts Search Filter Using  Partial Name  with this filter instead
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

}



