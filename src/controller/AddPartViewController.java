package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import wgu.inventoryApp.MainApplication;

import java.io.IOException;

public class AddPartViewController {
    @FXML
    private RadioButton addPartInHouseYes;
    @FXML
    private RadioButton addPartOutsourcedYes;
    @FXML
    private Label addChangeMe;
    @FXML
    private TextField partIdTxt;
    @FXML
    private TextField partNameTxt;
    @FXML
    private TextField partStockTxt;
    @FXML
    private TextField partInventoryMaxTxt;
    @FXML
    private TextField partInventoryMinTxt;
    @FXML
    private TextField partToggleIdTxt;


    public void toMain(ActionEvent actionEvent) throws IOException {

       Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
       Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1200, 450);
        stage.setTitle("Back To Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    public void onFirst(ActionEvent actionEvent) {
        addChangeMe.setText("Machine Id");
    }

    public void onSecond(ActionEvent actionEvent) {
        addChangeMe.setText("Company Name");
    }

    public void onActionSavePart(ActionEvent actionEvent) throws IOException {
        int id = Inventory.createId();
        String name = partNameTxt.getText();
        double price = Double.parseDouble(partStockTxt.getText());  // using wrapper class to convert input field string to int
        int stock = Integer.parseInt(partStockTxt.getText());
        int min = Integer.parseInt(partInventoryMinTxt.getText());;
        int max= Integer.parseInt(partInventoryMaxTxt.getText());;
        int toggle = Integer.parseInt(partToggleIdTxt.getText());
        boolean isInHouse;

        if(addPartInHouseYes.isSelected())
            isInHouse = true;
        else
            isInHouse = false;

        // had error here, was trying to instantiate an abstract class Part instead of inHouse which extends part


        InHouse addPart = new InHouse(id, name, price, stock, min, max);
        Inventory.addPart(addPart);

        // alternate method to use Node instead of Button
        // Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 450);
        stage.setTitle("Back To Main Screen");
        stage.setScene(scene);
        stage.show();

    }

}
