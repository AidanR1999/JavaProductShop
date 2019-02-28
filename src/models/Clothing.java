package models;

public class Clothing extends Product{
    
    //ATTRIBUTES
    private String measurement;

    //CONSTRUCTORS
    public Clothing(String measurement, String productName, double price, int stockLevel){
        super(productName, price, stockLevel);
        this.measurement = measurement;
    }
    public Clothing(String measurement, int productID, String productName, double price, int stockLevel) {
        super(productID, productName, price, stockLevel);
        this.measurement = measurement;
    }
    
    //GETTERS AND SETTERS
    //MEASUREMENT
    public String getMeasurement() {
        return measurement;
    }
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
