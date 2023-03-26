package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import wgu.inventoryApp.MainApplication;

import java.io.IOException;

public class AddPartViewController {
    public TextField partPriceTxt;
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
        try {
            int machineId = 0;

            int id = Inventory.createId();
            String name = partNameTxt.getText();
            int stock = Integer.parseInt(partStockTxt.getText());  // using wrapper class to convert input field string to int
            double price = Double.parseDouble(partPriceTxt.getText());
            int max = Integer.parseInt(partInventoryMaxTxt.getText());
            int min = Integer.parseInt(partInventoryMinTxt.getText());

            if (max < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Maximum must be greater than minimum.");
                alert.showAndWait();
                return;
            }
            //Inventory should be between the min and max values.
            else if (stock < min || max < stock) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory must be within min and max.");
                alert.showAndWait();
                return;
            }

            if (addPartInHouseYes.isSelected()) {
                machineId = Integer.parseInt(partToggleIdTxt.getText());
                InHouse addPart = new InHouse(id, name, price, stock, max, min, machineId);
                Inventory.addPart(addPart);
            } else {
                String companyName = partToggleIdTxt.getText();
                Outsourced addPart = new Outsourced(id, name, price, stock, max, min, companyName);
                System.out.println(companyName);
                Inventory.addPart(addPart);

            }
            // had error here, was trying to instantiate an abstract class Part instead of inHouse which extends part
            Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1200, 450);
            stage.setTitle("Back To Main Screen");
            stage.setScene(scene);
            stage.show();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("There is a Field Input Error");
            alert.setContentText("Please complete and check all your input values and try again");
            alert.showAndWait();
        }
    }

}
