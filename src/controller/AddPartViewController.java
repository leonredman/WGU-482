package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import wgu.inventoryApp.MainApplication;

import java.io.IOException;

public class AddPartViewController {
    public TextArea textArea;
    public Button secondButton;
    public RadioButton addPartInHouseYes;
    public RadioButton addPartOutsourcedYes;
    public Label addChangeMe;
    public Button savePartBtn;
    public TextField partIdTxt;
    public TextField partNameTxt;
    public TextField partStockTxt;
    public TextField partPriceTxt;
    public TextField partInventoryMaxTxt;
    public TextField partInventoryMinTxt;
    public TextField partToggleIdTxt;



    public void toMain(ActionEvent actionEvent) throws IOException {
//        Parent root = FXMLLoader.load(HelloApplication.class.getResource("addPart.fxml "));
//
       Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
       Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
//     Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1000, 450);
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
        int id = Integer.parseInt(partIdTxt.getText());   // using wrapper class to convert input field string to int
        String name = partNameTxt.getText();
        double price = Double.parseDouble(partStockTxt.getText());
        int stock = Integer.parseInt(partStockTxt.getText());
        int min = Integer.parseInt(partInventoryMinTxt.getText());;
        int max= Integer.parseInt(partInventoryMaxTxt.getText());;
        int toggle = Integer.parseInt(partToggleIdTxt.getText());
        boolean isInHouse;

        if(addPartInHouseYes.isSelected())
            isInHouse = true;
        else
            isInHouse = false;
// ERROR HERE >>>>>
     Inventory.addPart(new Part(id, name, price, stock, min, max));// we get an error when Part is set to Abstract must refactor here?
//        Inventory addPart = new Part(id, name, price, stock, min, max);// we get an error when Part is set to Abstract must refactor here?
//        Part.addPart(addPart);










        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
//     Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1000, 450);
        stage.setTitle("Back To Main Screen");
        stage.setScene(scene);
        stage.show();

    }

}
