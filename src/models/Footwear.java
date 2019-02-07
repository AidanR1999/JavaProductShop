package models;

public class Footwear extends Product{
    
    //ATTRIBUTES
    private int size;
    
    //CONSTRUCTORS
    public Footwear(int size, String productName, double price, int stockLevel) {
        super(productName, price, stockLevel);
        this.size = size;
    }
    public Footwear(int size, int productID, String productName, double price, int stockLevel) {
        super(productID, productName, price, stockLevel);
        this.size = size;
    }
    
    //GETTERS AND SETTERS
    //SIZE
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
}
