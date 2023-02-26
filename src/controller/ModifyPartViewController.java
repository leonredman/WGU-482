package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Part;
import wgu.inventoryApp.MainApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartViewController implements Initializable {
   // Stage stage;
   // Parent scene;
    public Label partIDLbl;
    @FXML
    private ToggleGroup tGroup;
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




    public void toMainFromModify(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
 //     Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
      Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
//        scene = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));


        Scene scene = new Scene(root, 1200, 450);
        stage.setTitle("Back To Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    public void sendPart(Part part) {
        // retrieved id and converted from string to assign to label
        partModIdLbl.setText(String.valueOf(part.getId()));
        partModNameLbl.setText(part.getName());
        partModInventoryLbl.setText(String.valueOf(part.getStock()));
        partModPriceLbl.setText(String.valueOf(part.getPrice()));
        partModMaxLbl.setText(String.valueOf(part.getMax()));
        partModMinLbl.setText(String.valueOf(part.getMin()));
        //partModToggleLbl.setText(String.valueOf(part.getMax()));

//        if(part instanceof InHouse)
//            partModToggleLbl.setText(((InHouse) part).getMachineId());


    }


    @Override
    public void initialize (URL location, ResourceBundle resources){
    // to do
    }


    public void onFirst(ActionEvent actionEvent) {
        modChangeMe.setText("Machine Id");
    }

    public void onSecond(ActionEvent actionEvent) {
        modChangeMe.setText("Company Name");
    }

}


