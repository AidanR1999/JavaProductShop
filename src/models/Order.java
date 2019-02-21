package models;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class Order {
    
    //ATTRIBUTES
    private int orderId;
    private Date orderDate;
    private Customer customer;
    private double orderTotal;
    private String status;
    private HashMap<Integer, OrderLine> orderLines;
    
    //CONSTRUCTORS
    public Order()
    {
        this.orderId = 0;
        this.orderDate = new Date();
        this.customer = new Customer();
        this.orderTotal = 0.00;
        this.status = "";
        this.orderLines = new HashMap();
    }
    public Order(int orderId, Date orderDate, Customer customer, double orderTotal, String status)
    {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customer;
        this.orderTotal = orderTotal;
        this.status = status;
        this.orderLines = new HashMap();
    }
    
    //GETTERS AND SETTERS
    //ORDER ID
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    //ORDER DATE
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    //CUSTOMER
    public Customer getCustomer(){
        return customer;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    
    //ORDER TOTAL
    public double getOrderTotal() {
        return orderTotal;
    }
    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }
    
    //STATUS
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    //ORDER LINES
    public HashMap<Integer, OrderLine> getOrderLines() {
        return orderLines;
    }
    public void setOrderLines(HashMap<Integer, OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
    
    
    //METHODS AND FUNCTIONS
    //GENERATE UNIQUE ORDER LINE ID
    public int generateUniqueOrderLineId()
    {
        int orderLineId = 0;
        
        for(Map.Entry<Integer, OrderLine> element : orderLines.entrySet())
        {
            if(orderLines.containsKey(orderLineId))
            {
                orderLineId++;
            }
        }
        return orderLineId;
    }
    
    //ADD ORDER LINE
    public void addOrderLine(OrderLine ol)
    {
        DBManager db = new DBManager();
        int orderLineId = db.addOrderLine(ol, orderId);
        
        orderLines.put(orderLineId, ol);
        orderLines.get(orderLineId).setOrderLineId(orderLineId);
        
        calculateOrderTotal();
    }
    
    //CALCULATE ORDER TOTAL
    public void calculateOrderTotal()
    {
        orderTotal = 0;
        
        for(Map.Entry<Integer, OrderLine> element : orderLines.entrySet())
        {
            OrderLine ol = element.getValue();
            orderTotal = orderTotal + ol.getLineTotal();
        }
        
        DBManager db = new DBManager();
        db.updateOrderTotal(orderId, orderTotal);
    }
    
    //REMOVE ORDERLINE
    public void removeOrderLine(int productId)
    {
        Iterator<Map.Entry<Integer, OrderLine>> iter = orderLines.entrySet().iterator();
        while(iter.hasNext())
        {
            OrderLine actualOrderLine = iter.next().getValue();
            if(actualOrderLine.getProduct().getProductID() == productId)
            {
                int orderLineId = actualOrderLine.getOrderLineId();
                DBManager db = new DBManager();
                db.deleteOrderLine(orderLineId);
                
                iter.remove();
                
                calculateOrderTotal();
            }
        }
    }
   
    //UPDATE STOCK LEVEL
    public void updateStockLevel()
    {
        
    }
    
    //CHECK IF PRODUCT IS IN BASKET
    public Optional<OrderLine> checkIfProductIsInBasket(int productId)
    {
        Optional<OrderLine> orderLineIn = Optional.empty();
        
        for(Map.Entry<Integer, OrderLine> olEntry : orderLines.entrySet()) 
        {
            OrderLine orderLine = olEntry.getValue();
            Product product = orderLine.getProduct();

            if(product.getProductID()== productId) 
            {
                orderLineIn = Optional.of(orderLine);
            }
        }
        return orderLineIn;
    }
}
