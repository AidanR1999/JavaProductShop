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
        //initialise orderline id
        int orderLineId = 0;
        
        //for every orderline in the orderlines hashmap
        for(Map.Entry<Integer, OrderLine> element : orderLines.entrySet())
        {
            //if orderline id already exists in hashmap
            if(orderLines.containsKey(orderLineId))
            {
                //increment orderline id
                orderLineId++;
            }
        }
        
        //return orderline id
        return orderLineId;
    }
    
    //ADD ORDER LINE TO DATABASE
    public void addOrderLine(OrderLine ol)
    {
        //create new instance of database
        DBManager db = new DBManager();
        
        //add orderline to database and store order line id
        int orderLineId = db.addOrderLine(ol, orderId);
        
        //add orderline to the hashmao
        orderLines.put(orderLineId, ol);
        orderLines.get(orderLineId).setOrderLineId(orderLineId);
        
        //get the total of the whole order
        calculateOrderTotal();
    }
    
    //CALCULATE ORDER TOTAL
    public void calculateOrderTotal()
    {
        //initailise order total
        orderTotal = 0;
        
        //for every orderline in the hashmap
        for(Map.Entry<Integer, OrderLine> element : orderLines.entrySet())
        {
            //get the current orderline in the loop
            OrderLine ol = element.getValue();
            
            //add the line total to the order total
            orderTotal = orderTotal + ol.getLineTotal();
        }
        
        //create instance of database
        DBManager db = new DBManager();
        
        //update ordertotal in the database
        db.updateOrderTotal(orderId, orderTotal);
    }
    
    //REMOVE ORDERLINE
    public void removeOrderLine(int productId)
    {
        //store the indices of the orderlines
        Iterator<Map.Entry<Integer, OrderLine>> iter = orderLines.entrySet().iterator();
        
        //while the iterator has an index after its current index
        while(iter.hasNext())
        {
            //get the orderline at the current iteration
            OrderLine actualOrderLine = iter.next().getValue();
            
            //if the productId of the current orderline = the productId of the product requested to be removed
            if(actualOrderLine.getProduct().getProductID() == productId)
            {
                //get the orderline id 
                int orderLineId = actualOrderLine.getOrderLineId();
                //create instance of database
                DBManager db = new DBManager();
                //delete orderline from database
                db.deleteOrderLine(orderLineId);
                
                //remove the orderline from the iterator
                iter.remove();
                
                //calculate the new order total
                calculateOrderTotal();
            }
        }
    }
   
    //UPDATE STOCK LEVEL
    public void updateStockLevel()
    {
        //for every orderline in the hashmap
        for(Map.Entry<Integer, OrderLine> olEntry : orderLines.entrySet())
        {
            //get the current orderline in the loop
            OrderLine orderLine = olEntry.getValue();
            
            //get the product in the orderline
            Product product = orderLine.getProduct();
            
            //create intsance of database
            DBManager db = new DBManager();
            
            //update the stock level in the database
            db.editStockLevel(product, orderLine);
        }
    }
    
    //CHECK IF PRODUCT IS IN BASKET
    public Optional<OrderLine> checkIfProductIsInBasket(int productId)
    {
        //get a nullable orderline
        Optional<OrderLine> orderLineIn = Optional.empty();
        
        //for every orderline in the hashmap
        for(Map.Entry<Integer, OrderLine> olEntry : orderLines.entrySet()) 
        {
            //get the current orderline in the loop
            OrderLine orderLine = olEntry.getValue();
            
            //get the product in the orderline
            Product product = orderLine.getProduct();

            //if the product id of the product = the product id of the product requested
            if(product.getProductID()== productId) 
            {
                //set the orderline to the current orderline in the loop
                orderLineIn = Optional.of(orderLine);
            }
        }
        
        //return nullable orderline
        return orderLineIn;
    }
}
