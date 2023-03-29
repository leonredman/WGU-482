package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * This <b>"MainController"</b> class implements all the features of the main screen.
 */
public class MainController implements Initializable {

    private int partCount = 1;
    private int productCount = 1;

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


    /**
     * This <b>"getResultsHandler"</b> method on enter searches parts by id
     * or string based on user input. <br>
     * @param actionEvent
     */
    public void getResultsHandler(ActionEvent actionEvent) {
        String q = partsSearchFieldMain.getText();

        ObservableList<Part> parts = Inventory.lookupPart(q);

        if (parts.size() == 0){
             try {
            int id = Integer.parseInt(q);
            Part part = Inventory.lookupPart(id);
            if (part != null)
                parts.add(part);
        }
        catch(NumberFormatException e) {
            // catch and ignore
           }
        }
        partsTable.setItems(parts);
    }


    /**
     * This <b>"getProductResultsHandler"</b> method on enter searches products by id
     * or string based on user input. <br>
     * @param actionEvent
     */
    public void getProductResultsHandler(ActionEvent actionEvent) {
        String prq = productsSearchFieldMain.getText();

        ObservableList<Product> products = Inventory.lookupProduct(prq);

        if (products.size() == 0) {
            try {
                int id = Integer.parseInt(prq);
                Product product = Inventory.lookupProduct(id);
                if (product != null)
                    products.add(product);
            }
            catch (NumberFormatException e){
                // catch and ignore
            }
        }
        productsTable.setItems(products);
    }


    /**
     * This <b>"initialize"</b> method initializes and sets all inventory
     * to tables and binds columns. <br>
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");

        // Associate lists with table
        productsTable.setItems(Inventory.getAllProducts());


        partsTable.setItems(Inventory.getAllParts());
            System.out.println(partsTable.getItems());


        // Associate / bind columns with table
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    /**
     * This <b>"deletePartFired"</b> method on click removes selected part from inventory. <br>
     * @param actionEvent
     */
    public void deletePartFired(ActionEvent actionEvent){

            Part selectedPart = (Part) partsTable.getSelectionModel().getSelectedItem();
            if(selectedPart == null) {
                Alert noDeletePartSelectedMessage = new Alert(Alert.AlertType.WARNING);
                noDeletePartSelectedMessage.setContentText("No Part Deleted - You must select a part first");
                noDeletePartSelectedMessage.show();
            } if (selectedPart != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("This Part will be permanently Deleted?");
                alert.setContentText("Do you want to Delete this Now?");
                Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }


    /**
     * This <b>"deleteProductFired"</b> method on click removes selected product from inventory. <br>
     * @param actionEvent
     */
    public void deleteProductFired(ActionEvent actionEvent) {

        Product selectedProduct = (Product) productsTable.getSelectionModel().getSelectedItem();

        if(selectedProduct == null) {
            Alert noDeletePartSelectedMessage = new Alert(Alert.AlertType.WARNING);
            noDeletePartSelectedMessage.setContentText("No Product Deleted - You must select a product first");
            noDeletePartSelectedMessage.show();
        } if (selectedProduct != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("This Product will be permanently Deleted?");
            alert.setContentText("Do you want to Delete this Now?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Product selectedProductToDelete = (Product) productsTable.getSelectionModel().getSelectedItem();
                if (selectedProductToDelete.getAllAssociatedParts().size() > 0) {
                    Alert productCannotBeDeleted = new Alert(Alert.AlertType.ERROR);
                    productCannotBeDeleted.setTitle("Error Message");
                    productCannotBeDeleted.setContentText("Remove associated parts to all products to be deleted");
                    productCannotBeDeleted.showAndWait();
                    return;
                }
                Inventory.deleteProduct(selectedProduct);
            }
        }
   }


    /**
     * This <b>"addPart"</b> method on click takes user to add part screen and loads controller. <br>
     * @param actionEvent
     * @throws IOException
     */
    public void addPart(ActionEvent actionEvent) throws IOException {
        // Parent root = FXMLLoader.load(HelloApplication.class.getResource("/main/resources/wgu/firstscreen/addPart.fxml "));
        Parent root = FXMLLoader.load(getClass().getResource("/view/addPart.fxml"));

        //Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/addPart.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Part Screen");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This <b>"addProduct"</b> method on click takes user to add product screen and loads controller. <br>
     * @param actionEvent
     * @throws IOException
     */
    public void addProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/addProduct.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Product Screen");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This <b>"toModifyPart"</b> method on click takes user to modify part screen and
     * loads data for selected part. <br>
     * @param actionEvent
     * @throws IOException
     */
    public void toModifyPart(ActionEvent actionEvent) throws IOException {
        // try/catch for exception when no part selected
        try {
            // create an fxml loader object and create a constructor. new fxml loader
            FXMLLoader loader = new FXMLLoader();

            // specify which screen we are going to be loading
            //  loader.setLocation(MainApplication.class.getResource("/view/modifyPart.fxml"));
            loader.setLocation(getClass().getResource("/view/modifyPart.fxml"));

            // call load method  overloaded method without any params
            loader.load();

            ModifyPartViewController MPVController = loader.getController();


            MPVController.sendPart(partsTable.getSelectionModel().getSelectedIndex(), (Part) partsTable.getSelectionModel().getSelectedItem()); // had to reference the index in the get
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setTitle("Modify Part Screen");
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (NullPointerException e){
            Alert noPartSelectedMessage = new Alert(Alert.AlertType.WARNING);
            noPartSelectedMessage.setContentText("You must select a part first");
            noPartSelectedMessage.show();
        }
    }


    /**
     * This <b>"toModifyProduct"</b> method on click takes user to modify product screen and
     * loads data for selected product. <br>
     * @param actionEvent
     * @throws IOException
     */
    public void toModifyProduct(ActionEvent actionEvent) throws IOException{
        try {
        FXMLLoader loader = new FXMLLoader() ;

    loader.setLocation((getClass().getResource("/view/modifyProduct.fxml")));
    loader.load();

    ModifyProductViewController MPRVController = loader.getController();

    MPRVController.sendProduct(productsTable.getSelectionModel().getSelectedIndex(),(Product)productsTable.getSelectionModel().getSelectedItem());  // we needed to pass in selectedIndex

    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    Parent scene = loader.getRoot();
    stage.setTitle("Modify Product Screen");
    stage.setScene(new Scene(scene));
    stage.show();
    } catch (NullPointerException e){
        Alert noPartSelectedMessage = new Alert(Alert.AlertType.WARNING);
        noPartSelectedMessage.setContentText("You must select a product first");
        noPartSelectedMessage.show();
    }
}


    /**
     * This <b>"exitBtn"</b> method terminates the program. <br>
     * @param actionEvent
     */
    public void exitBtn(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("This will end the program now");
        alert.setContentText("Do you want to exit the program now?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
    }
}}
