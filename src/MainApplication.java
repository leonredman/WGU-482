package wgu.inventoryApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

/**
 * This class creates an app for inventory control with test data.<br>
 *<br>
 * FUTURE FEATURES - Some future feature I would add to this application is to have it store data in a database <br>
 * and to have the search feature auto filter as you type characters in the search field without having to press enter.<br>
 *<br>
 * RUNTIME ERROR - (Bug) I had a runtime error that kept displaying the same machine id for all parts<br>
 * and would not change due to the fact that I had the InHouse class set as static.<br>
 * I fixed this by changing the class to public. <br>
 */
public class MainApplication extends Application {


/**
 * This method starts the main screen fxml and adds the test data.<br>
 * <br>
 * @param primaryStage This is the stage that FX passes in. <br>
 */
    @Override
    public void start(Stage primaryStage) throws IOException {

        addTestData();

        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/mainScreen.fxml"));
        primaryStage.setTitle("Main Screen");
        primaryStage.setScene(new Scene (root, 1200, 600));
        primaryStage.show();
    }


    /**
     * This method adds the test data for parts and products.
     */
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


    /**
     * This is the main method. This is the first method that gets called when you run your java program.
     *
     * @param args These are the command line args that will not be used in this program.
     */
    public static void main(String[] args) {
        launch();
    }
}
