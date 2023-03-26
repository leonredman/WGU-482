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
import model.Part;
import wgu.inventoryApp.MainApplication;

import java.io.IOException;


public class ModifyPartViewController {

    @FXML
    private Label modChangeMe;
    @FXML
    private RadioButton modPartInHouseYes;
    @FXML
    private RadioButton modPartOutSourcedYes;
    @FXML
    private TextField partModIdLbl;
    @FXML
    private TextField partModNameLbl;
    @FXML
    private TextField partModInventoryLbl;
    @FXML
    private TextField partModPriceLbl;
    @FXML
    private TextField partModMaxLbl;
    @FXML
    private TextField partModMinLbl;
    @FXML
    private TextField partModToggleLbl;
    
    private int currentIndex = 0;

    // Cancel Button
    public void toMainFromModify(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        Scene scene = new Scene(root, 1200, 450);
        stage.setTitle("Back To Main Screen");
        stage.setScene(scene);
        stage.show();
    }


    //public void sendPart(Part part) {
    public void sendPart(int selectedIndex, Part part) {
    // if inHouse radio is selected (true) get data id, name, inv, price, max, min, machine id part instance of InHouse

        currentIndex = selectedIndex;
        
        if (part instanceof InHouse) {
            modPartInHouseYes.setSelected(true);
            partModToggleLbl.setText(String.valueOf(((InHouse) part).getMachineId()));  // cast InHouse Part object so we can access getMachineId
        } else {
           modPartOutSourcedYes.setSelected(true);
           modChangeMe.setText("Company Name");
           partModToggleLbl.setText(((Outsourced) part).getCompanyName());   // cast
        }

        // retrieved id and converted from string to assign to label
        partModIdLbl.setText(String.valueOf(part.getId()));
        partModNameLbl.setText(part.getName());
        partModInventoryLbl.setText(String.valueOf(part.getStock()));
        partModPriceLbl.setText(String.valueOf(part.getPrice()));
        partModMaxLbl.setText(String.valueOf(part.getMax()));
        partModMinLbl.setText(String.valueOf(part.getMin()));
        }


    public void onFirst(ActionEvent actionEvent) {
        modChangeMe.setText("Machine Id");
    }


    public void onSecond(ActionEvent actionEvent) {
        modChangeMe.setText("Company Name");
    }


    public void modPartSaveBtn(ActionEvent actionEvent) throws IOException {
    try{
        int modPartId = Integer.parseInt(partModIdLbl.getText());
        String modPartName = partModNameLbl.getText();
        int modPartInventory = Integer.parseInt(partModInventoryLbl.getText());
        double modPartPrice = Double.parseDouble(partModPriceLbl.getText());
        int modPartMax = Integer.parseInt(partModMaxLbl.getText());
        int modPartMin = Integer.parseInt(partModMinLbl.getText());
        int machineId;
        
        String companyName;

        //Min should be less than max.
       if (modPartMax < modPartMin) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Maximum must be greater than minimum.");
            alert.showAndWait();
            return;
        }
       //Inventory should be between the min and max values.
            else if (modPartInventory < modPartMin || modPartMax < modPartInventory) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory must be within min and max.");
                alert.showAndWait();
                return;
            }

        if(modPartInHouseYes.isSelected()) {
            machineId = Integer.parseInt(partModToggleLbl.getText());
            InHouse modifiedPart = new InHouse(modPartId, modPartName, modPartPrice, modPartInventory, modPartMax, modPartMin, machineId );
            System.out.println("modifiedPart id = " + modifiedPart.getId());
            Inventory.updatePart(currentIndex,modifiedPart);
        }

        if (modPartOutSourcedYes.isSelected()) {
            companyName = partModToggleLbl.getText();
            Outsourced modifiedPart = new Outsourced(modPartId, modPartName,modPartPrice,modPartInventory,modPartMax,modPartMin,companyName);
            Inventory.updatePart(currentIndex,modifiedPart);
        }

        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
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


