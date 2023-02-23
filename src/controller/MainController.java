package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import wgu.inventoryApp.MainApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private int partCount = 1;
    private int productCount = 1;

    public Label TheLabel;
    public TextField partsSearchFieldMain;
    public Button removePart;
    public Button modifyPart;
    public Button addPart;
    public TextField productsSearchFieldMain;
    public Label productsTitle;
    public Button deleteProduct;
    public Button modifyProduct;
    public Button addProduct;
    public Label partsTitleText;

    public TableView partsTable;
    public TableColumn partIdCol;
    public TableColumn partNameCol;
    public TableColumn partInventoryLevelCol;
    public TableColumn partPricePerUnitCol;


    public TableView productsTable;
    public TableColumn productIdCol;
    public TableColumn productNameCol;
    public TableColumn productInventoryLevelCol;
    public TableColumn productPricePerUnitCol;

    public void getResultsHandler(ActionEvent actionEvent) {
        String q = partsSearchFieldMain.getText();

        ObservableList<Part> parts = filter(q);

        if (parts.size() == 0){
        try {
            int id = Integer.parseInt(q);
            Part part = getPartsWithID(id);
            if (part != null)
                parts.add(part);
        }
        catch(NumberFormatException e) {
            // catch and ignore
        }
      }

        partsTable.setItems(parts);
    }


    private Part getPartsWithID (int id){
        ObservableList<Part> allParts = Inventory.getAllParts();
    //basic index loop option
     //   for (int i = 0; i < allParts.size(); i++) {
      //      Part part = allParts.get(i);

        // Enhanced loop option
        for(Part part: allParts){
            if (part.getId() == id) {
                return part;
            }
        }
        return null;
    }

// selectPart is the method for index
//            int id = Integer.parseInt(q);
//            Part part = selectPart(id);
//            if (part != null)
//                parts.add(part);


    // Linear search method
//    public boolean search(int id) {
//        for (Part part : Inventory.getAllParts()) {
//            if (part.getId() == id)
//                return true;
//        } // else
//        return false;
//    }

/*
    // Searching for an existing object
    public boolean update(int id, Part part1) {
        int index = -1;                     // index starts at -1 so first time it runs we get index of zero
        for(Part part: Inventory.getAllParts())
        {
            index ++;

            if(part.getId() == id) {
                Inventory.getAllParts().set(index, part1);
                return true;
            }
        }
        return false;
    }


 */

    // Remove method
//    public boolean delete(int id) {
//        for(Part part: Inventory.getAllParts()) {
//            if(part.getId() == id)              // check if id's match
//                return Inventory.getAllParts().remove(part);
//        }
//        return false;
//    }


    // Define select method this searches by id number only
//    public Part selectPart(int id) {
//        for(Part part : Inventory.getAllParts()){
//            if(part.getId() == id)
//                return part;
//        }
//        return null;
//    }

/*

    // Our filter Method to search all parts
    public ObservableList<Part> filter (String name) {            // scans through list and checks if contains any text passed. If true add it to filtered list
        // clear filter
        if(!(Inventory.getAllFilteredParts().isEmpty()))        // checks if list is not empty then we have filtered already
            Inventory.getAllFilteredParts().clear();            // takes out everything in list

        for(Part part: Inventory.getAllParts()) {
            if(part.getName().contains(name))
                Inventory.getAllFilteredParts().add(part);      // returns filtered list
        }

        if(Inventory.getAllFilteredParts().isEmpty())           // if empty return original list or filtered parts list
            return Inventory.getAllParts();
        else
        return Inventory.getAllFilteredParts();
    }

 */


    // try this filter instead
    private ObservableList<Part> filter (String partialName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part part: allParts) {
            if(part.getName().contains(partialName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }


        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");

        // Associate lists with table
        productsTable.setItems(Inventory.getAllProducts());


        // To test filter - comment out line 135 ~ setItems(Inventory.allParts) temporarily and uncomment line 136

        partsTable.setItems(Inventory.getAllParts());
       // partsTable.setItems(filter("Z"));



        // Associate / bind columns with table
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));




        // TEST CALL of linear search in initialize method
    /*
        if(search(3))
            System.out.println("match found");
        else
            System.out.println("No match found");
    */


        // Test data for update method
    /*
        if (update(55, new InHouse(3, "Razor Cooling Fan GX99", 199.99, 20, 1, 1)))
            System.out.println("Update Successful");
        else
            System.out.println("Update Failed");


        // Test delete

        if (delete(3))
        System.out.println("delete successful");
        else System.out.println("No Match");
    */


        // This call select method with test id passed in of 3 this is how to select based on id
        // partsTable.getSelectionModel().select(selectPart(6));


    }



    @FXML
    protected void modifyPartFired() {
        // welcomeText.setText("Modify Part Fired Leon!");
        System.out.println("Modify Part button is clicked");
        TheLabel.setText("You clicked the Modify Part button, Total Number of clicks is: " + partCount++);
    }

    @FXML
    protected void removePartFired() {
        System.out.println("Part Delete Fired");

        ObservableList<Part> selectedRows, allParts;
        allParts = partsTable.getItems();
        selectedRows = partsTable.getSelectionModel().getSelectedItems();
        for (Part part : selectedRows) {
            allParts.remove(part);
        }
    /*  welcomeText.setText("Delete Part Fired Leon!");
        TheLabel.setText("You clicked the Delete Part button, Total Number of clicks is: " + partCount++);

        Sample code for delete
        if (partsTable.getSelectionModel().isEmpty()) {
            int selectedPart = partsTable.getSelectionModel().getSelectedIndex();
            partsTable.getItems().remove(selectedPart);
        }
   */
    }


    public void addProductFired(ActionEvent actionEvent) {
        System.out.println("Add Product Fired");
        TheLabel.setText("You clicked the Add Product button, Total Number of clicks is: " + productCount++);
    }

    public void modifyProductFired(ActionEvent actionEvent) {
        System.out.println("Product Modify Fired");
        TheLabel.setText("You clicked the Modify Product button, Total Number of clicks is: " + productCount++);
    }

    public void deleteProductFired(ActionEvent actionEvent) {
        System.out.println("Product Delete Fired");

        ObservableList<Product> selectedRows,allProducts;
        allProducts = productsTable.getItems();
        selectedRows = productsTable.getSelectionModel().getSelectedItems();
        for (Product product: selectedRows) {
            allProducts.remove(product);
        }
    }

    // From main to 'add Part'
    public void toSecond(ActionEvent actionEvent) throws IOException {
        // Parent root = FXMLLoader.load(HelloApplication.class.getResource("/main/resources/wgu/firstscreen/addPart.fxml "));
        Parent root = FXMLLoader.load(getClass().getResource("/view/addPart.fxml"));

        //Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/addPart.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Part Screen");
        stage.setScene(scene);
        stage.show();

    }

    // From main to 'add Product'
    public void toAddProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/addProduct.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Product Screen");
        stage.setScene(scene);
        stage.show();

    }

    // From main to 'modifyPart HANDLER'
    public void toModifyPart(ActionEvent actionEvent) throws IOException {
        // create an fxml loader object
        // create a constructor. new fxml loader
        FXMLLoader loader = new FXMLLoader();

        // specify which screen we are going to be loading
        //  loader.setLocation(MainApplication.class.getResource("/view/modifyPart.fxml"));
        loader.setLocation(getClass().getResource("/view/modifyPart.fxml"));

        // call load method  overloaded method without any params
        loader.load();

        ModifyPartViewController MPVController = loader.getController();
   //   MPVController.sendPart(partsTable.getSelectionModel().getSelectedItem());        // shows error must be cast?
        MPVController.sendPart((Part) partsTable.getSelectionModel().getSelectedItem());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setTitle("Modify Part Screen");
        stage.setScene(new Scene(scene));
        stage.show();

        // ORIGINAL FUNCTION TO GO FROM MAIN TO MODIFY PART SCREEN
    /*
        public void toModifyPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/modifyPart.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.setTitle("Modify Part Screen");
        stage.setScene(scene);
        stage.showAndWait();
    */


    /*
        ORIGINAL FUNCTION TO GO FROM MAIN TO MODIFY PART SCREEN
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/modifyPart.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Part Screen");
        stage.setScene(scene);
        stage.show();

    */
    }

    // From main to modifyProduct
    public void toModifyProduct(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(MainApplication.class.getResource("/view/modifyProduct.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Product Screen");
        stage.setScene(scene);
        stage.show();
    }

    public void exitBtn(ActionEvent actionEvent) {
        System.exit(0);
    }


}
