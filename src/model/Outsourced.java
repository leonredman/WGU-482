package model;

/**
 * This Outsourced class is used to describe outsourced parts. <br>
 */
public class Outsourced extends Part {
    // extends Part class for inheritance
    private String companyName;
// Call super in constructor
    public Outsourced(int id, String name, double price, int stock, int max, int min, String companyName){
        super(id, name, price, stock, max, min);

        this.companyName = companyName;
    }


    /**
     * This <b>"getCompanyName"</b> method is the getter for the class.
     *
     * @return
     */
    public String getCompanyName() {
        return companyName;
    }


    /**
     * This <b>"setCompanyName"</b> method is the setter for the class.
     *
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
