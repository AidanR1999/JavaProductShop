package models;

public class Product {
    
    //Atributes
    private int productID;
    private String productName;
    private double price;
    private int stockLevel;

    
    //Constructors
    //empty
    public Product(){
        this.productID = 0;
        this.productName = "null";
        this.price = 0.00;
        this.stockLevel = 0;
    }
    //all except productID
    public Product(String productName, double price, int stockLevel){
        this.productName = productName;
        this.price = price;
        this.stockLevel = stockLevel;
    }
    //every attribute
    public Product(int productID, String productName, double price, int stockLevel) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.stockLevel = stockLevel;
    }
    
    //toString() override
    @Override
    public String toString(){
        return productName;
    }
    
    //Getters and Setters
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockLevel() {
        return stockLevel;
    }
    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    
}
