package models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
    
    //ATTRIBUTES
    private int orderId;
    private Date orderDate;
    private double orderTotal;
    private String status;
    private HashMap<Integer, OrderLine> orderLines;
    
    //CONSTRUCTORS
    public Order()
    {
        this.orderId = 0;
        this.orderDate = new Date();
        this.orderTotal = 0.00;
        this.status = "";
        this.orderLines = new HashMap();
    }
    public Order(int orderId, Date orderDate, double orderTotal, String status)
    {
        this.orderId = orderId;
        this.orderDate = orderDate;
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
        int oldOrderLineId = ol.getOrderLineId();
        
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
}
