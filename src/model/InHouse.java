package model;

// must use extends Part class for inheritance
public class InHouse extends Part {
// made mistake here and made static and machine id would not change
     private  int machineId;

    // must call super in constructor
    public InHouse(int id, String name, double price, int stock, int max, int min,  int machineId) {
        super(id, name, price, stock, max, min);
        this.machineId = machineId;
    }

    // setter
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    // getter
    public  int getMachineId() {
        return machineId;
    }
}
