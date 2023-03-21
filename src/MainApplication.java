package wgu.inventoryApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

public class MainApplication extends Application {

    // new code for multi scenes replaced original auto generated for one scene here

    @Override
    public void start(Stage primaryStage) throws IOException {

        addTestData();

        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        primaryStage.setTitle("Main Screen");
        primaryStage.setScene(new Scene (root, 1200, 600));
        primaryStage.show();
    }


    private void addTestData () {
        //Parts Test Data
        // use createId method instead of hard coded id num
        Part ssdDrive = new InHouse(Inventory.createId(), "SSD Drive", 49.99, 60, 60, 20, 101);
        Inventory.addPart(ssdDrive);

        Part pwrSupply = new InHouse(Inventory.createId(), "Power Supply EVGA", 69.99, 20, 20, 5,202);
        Inventory.addPart(pwrSupply);

        Part coolFan = new Outsourced(Inventory.createId(), "CORSAIR Cooling Fan", 149.99, 40, 40,10,"Acme");
        Inventory.addPart(coolFan);


        // Products Test Data
        Product gamingChair = new Product(Inventory.createId(), "Gaming Chair", 10, 149.99, 15, 1);
        Inventory.addProduct(gamingChair);

        Product smLaptop = new Product(Inventory.createId(), "Macbook Air", 15,50.00, 20, 1);
        Inventory.addProduct(smLaptop);

        Product gamePc = new Product(Inventory.createId(), "Razor Gamer Extreme", 20, 1500.00, 30,1);
        Inventory.addProduct(gamePc);
    }


    public static void main(String[] args) {
        launch();
    }
}
