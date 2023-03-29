package model;

/**
 * This <b>"InHouse"</b> class is the FXML controller for the inHouse parts. <br>
 *
 * <b>MADE RUNTIME ERROR HERE</b> - made mistake here and made static and machine id would not change
 */
public class InHouse extends Part {
    // must use extends Part class for inheritance
     private  int machineId;

    // must call super in constructor
    public InHouse(int id, String name, double price, int stock, int max, int min,  int machineId) {
        super(id, name, price, stock, max, min);
        this.machineId = machineId;
    }


    /**
     * This <b>"setMachineId"</b> method is the setter for the class.
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }


    /**
     * This <b>"getMachineId"</b> method is the getter for the class.
     * @return
     */
    public  int getMachineId() {
        return machineId;
    }
}
