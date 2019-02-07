package models;

public class OrderLine {

    //ATTRIBUTES
    private int orderLineId;
    private Product product;
    private int quantity;
    private double lineTotal;
    
    //CONSTRUCTORS
    public OrderLine(int orderLineId, Product product, int quantity) {
        this.orderLineId = orderLineId;
        this.product = product;
        this.quantity = quantity;
        this.lineTotal = product.getPrice() * this.quantity;
    }
    
    //GETTERS AND SETTERS
    //ORDER LINE ID
    public int getOrderLineId() {
        return orderLineId;
    }
    public void setOrderLineId(int orderLineId) {
        this.orderLineId = orderLineId;
    }

    //PRODUCT
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    //QUANTITY
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //LINE TOTAL
    public double getLineTotal() {
        return lineTotal;
    }
    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }
}
