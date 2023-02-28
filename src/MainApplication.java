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

//    public static int getRandom(int max) {
//        return (int)(Math.random()*(max))+1;
  //  }

//    public static int createRandom(){
//        Random genId = new Random();
//        int myId = 0;
//        for(int counter =1; counter<10; counter++) {
//            myId = genId.nextInt(1000);
//        }
//        return myId;
//
//    }
//        public static int createPartId() {
//           int newPartId = 1;
//           for (int i = 0; i < Inventory.getAllParts().size(); i++) {
//               newPartId++;
//           }
//           return newPartId;
//
//        }


    private void addTestData (){
        // parts ~ passing in method getRandom() instead of id #
        PowerSupply pwrSupply = new PowerSupply(Inventory.createId(), "Power Supply EVGA", 69.99, 20, 9, 6);
//        PowerSupply pwrSupply = new PowerSupply(1, "Power Supply EVGA", 69.99, 20, 9, 6);
        Inventory.addPart(pwrSupply);

        MotherBoard momBoard = new MotherBoard(Inventory.createId(), "Dell Motherboard Optiplex", 54.95, 25, 5, 1);
        Inventory.addPart(momBoard);

        CoolingFan coolFan = new CoolingFan(Inventory.createId(), "CORSAIR Cooling Fan", 149.99, 40, 5,1);
        Inventory.addPart(coolFan);

        // products
        SmallLaptop smLaptop = new SmallLaptop(Inventory.createId(), "Macbook Air", 1500,50.00, 9, 9);
        Inventory.addProduct(smLaptop);

        GamingPc gamePc = new GamingPc(Inventory.createId(), "Razor Gamer Extreme", 200, 1500.00, 50,1);
        Inventory.addProduct(gamePc);

        DesktopPc desktopPc = new DesktopPc(Inventory.createId(), "DELL Desktop", 25, 4000.00, 5,1);
        Inventory.addProduct(desktopPc);
    }



    public static void main(String[] args) {
        launch();
    }
}
