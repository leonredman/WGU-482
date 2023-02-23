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

    private void addTestData (){
        // parts
        PowerSupply pwrSupply = new PowerSupply(1, "Power Supply EVGA", 69.99, 20, 9, 6);
        Inventory.addPart(pwrSupply);

        MotherBoard momBoard = new MotherBoard(2, "Dell Motherboard Optiplex", 54.95, 25, 5, 1);
        Inventory.addPart(momBoard);

        CoolingFan coolFan = new CoolingFan(3, "CORSAIR Cooling Fan", 149.99, 40, 5,1);
        Inventory.addPart(coolFan);

        // products
        SmallLaptop smLaptop = new SmallLaptop(4, "Macbook Air", 1500.00,50, 9, 9, false);
        Inventory.addProduct(smLaptop);

        GamingPc gamePc = new GamingPc(5, "Razor Gamer Extreme", 3500.00, 67, 5,1, true);
        Inventory.addProduct(gamePc);

        DesktopPc desktopPc = new DesktopPc(6, "DELL Desktop", 2500.00, 400, 5,1, true);
        Inventory.addProduct(desktopPc);
    }

    public static void main(String[] args) {
        launch();
    }
}
