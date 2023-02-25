package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Inventory;
import model.Product;
import wgu.inventoryApp.MainApplication;

import java.io.IOException;

public class AddProductViewController {
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



    public void addProdBackToMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        //Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1000, 450);
        stage.setTitle("Back To Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    public void onProductSearch(ActionEvent actionEvent) {
    }

    public void onAddProductBtn(ActionEvent actionEvent) {
    }

    public void onSaveProductBtn(ActionEvent actionEvent) throws IOException{
         int id = Integer.parseInt(addProductIdField.getText());
         String name = addProductNameField.getText();
         int stock = Integer.parseInt(addProductInvField.getText());
         Double price = Double.parseDouble(addProductPriceField.getText());
         int max = Integer.parseInt(addProductMaxField.getText());
         int min = Integer.parseInt(addProductMinField.getText());


        Product addProduct = new Product(id, name, stock, price, max, min);
        Inventory.addProduct(addProduct);

        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        //Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1000, 450);
        stage.setTitle("Back To Main Screen");
        stage.setScene(scene);
        stage.show();

    }

    public void onRemoveAssociatedPartBtn(ActionEvent actionEvent) {
    }
}
