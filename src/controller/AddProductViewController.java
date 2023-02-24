package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import wgu.inventoryApp.MainApplication;

import java.io.IOException;

public class AddProductViewController {
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
}
