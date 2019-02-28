package models;

public class Product {
    
    //ATTRIBUTES
    private int productID;
    private String productName;
    private double price;
    private int stockLevel;

    
    //CONSTRUCTORS
    public Product(){
        this.productID = 0;
        this.productName = "null";
        this.price = 0.00;
        this.stockLevel = 0;
    }
    public Product(String productName, double price, int stockLevel){
        this.productName = productName;
        this.price = price;
        this.stockLevel = stockLevel;
    }
    public Product(int productID, String productName, double price, int stockLevel) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.stockLevel = stockLevel;
    }
    
    //GETTERS AND SETTERS
    //PRODUCT ID
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }

    //PRODUCT NAME
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    //PRICE
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    //STOCK LEVEL
    public int getStockLevel() {
        return stockLevel;
    }
    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }
    
    //METHODS AND FUNCTIONS
    //toString() override
    @Override
    public String toString(){
        return productName;
    }
}
