package model;

/**
 * This abstract part class is used to describe parts tracked in inventory. <br>
 *
 * RUNTIME ERROR had issue with trying to instantiate and add a part. Resolved by making abstract.<br>
 * Also had an issue with creating individual unique id. Was calling method twice.
 */
   public abstract class Part {
    // Notes: part is an abstract class like Fruit class in webinar
    // instance attributes
    private int id;
    private String name;
    private double price;
    private int stock;
    private int max;
    private int min;

    // constructor
    public Part(int id, String name, double price, int stock, int max, int min ) {

     // this.id = Inventory.nextId;   // fixed  bug here was making the update use the next id instead of the selected index id
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.max = max;
        this.min = min;

    }


    /**
     * This <b>"getId"</b> method is the getter for the class. <br>
     * @return
     */
    public int getId() {
        return id;
    }


    /**
     * This <b>"setId"</b> method is the setter for the class. <br>
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * This <b>"getName"</b> method is the getter for the class. <br>
     * @return
     */
    public String getName() {
        return name;
    }


    /**
     * This <b>"setName"</b> method is the setter for the class. <br>
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * This <b>"getPrice"</b> method is the getter for the class. <br>
     * @return
     */
    public double getPrice() {
        return price;
    }


    /**
     * This <b>"setPrice"</b> method is the setter for the class. <br>
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }


    /**
     * This <b>"getStock"</b> method is the getter for the class. <br>
     * @return
     */
    public int getStock() {
        return stock;
    }


    /**
     * This <b>"setStock"</b> method is the setter for the class. <br>
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }


    /**
     * This <b>"getMin"</b> method is the getter for the class. <br>
     *
     * @return
     */
    public int getMin() {
        return min;
    }


    /**
     * This <b>"setMin"</b> method is the setter for the class. <br>
     *
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * This <b>"getMax"</b> method is the getter for the class. <br>
     * @return
     */
    public int getMax() {
        return max;
    }


    /**
     * This <b>"setMax"</b> method is the setter for the class. <br>
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }
}
