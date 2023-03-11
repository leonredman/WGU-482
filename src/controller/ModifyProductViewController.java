package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Product;
import wgu.inventoryApp.MainApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductViewController implements Initializable {
    @FXML
    private TextField prodModIdLbl;
    @FXML
    private TextField productModNameLbl;
    @FXML
    private TextField productModInventoryLbl;
    @FXML
    private TextField productModPriceLbl;
    @FXML
    private TextField productModMaxLbl;
    @FXML
    private TextField prodModMinLbl;

    public void toMainFromModifyProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        // Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1200, 450);
        stage.setTitle("Back To Main Screen");
        stage.setScene(scene);
        stage.show();
    }

        public void sendProduct(Product product) {

            prodModIdLbl.setText(String.valueOf(product.getId()));
            productModNameLbl.setText(product.getName());
            productModInventoryLbl.setText(String.valueOf(product.getStock()));
            productModPriceLbl.setText(String.valueOf(product.getPrice()));
            productModMaxLbl.setText(String.valueOf(product.getMax()));
            prodModMinLbl.setText(String.valueOf(product.getMin()));
        }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

// todo
    }
}



/*
Beginning file set up
    public void toMainFromModifyProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        // Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1200, 450);
        stage.setTitle("Back To Main Screen");
        stage.setScene(scene);
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


 */