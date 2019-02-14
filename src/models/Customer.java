package models;

import java.util.HashMap;
import java.util.Map;

public class Customer extends User{

    //ATTRIBUTES
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String postcode;
    private boolean isRegistered;
    private HashMap<Integer, Order> orders;
    
    //CONSTRUCTORS
    public Customer(){
        super();
        this.addressLine1 = "null";
        this.addressLine2 = "null";
        this.town = "null";
        this.postcode = "null";
        this.isRegistered = false;
        orders = new HashMap();
    }
    public Customer(String addressLine1, String addressLine2, String town, String postcode, boolean isRegistered, String username, String password, String forename, String surname) {
        super(username, password, forename, surname);
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.postcode = postcode;
        this.isRegistered = isRegistered;
        orders = new HashMap();
    }
        
    //GETTERS AND SETTERS
    //ADDRESS LINE 1
    public String getAddressLine1() {
        return addressLine1;
    }
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    //ADDRESS LINE 2
    public String getAddressLine2() {
        return addressLine2;
    }
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    //TOWN
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }

    //POSTCODE
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    //IS REGISTERED
    public boolean isIsRegistered() {
        return isRegistered;
    }
    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
    
    //ORDERS
    public HashMap<Integer, Order> getOrders() {
        return orders;
    }
    public void setOrders(HashMap<Integer, Order> orders) {
        this.orders = orders;
    }
    
    //METHODS AND FUNCTIONS
    //DISPLAY GREETING FOR CUSTOMER HOME
    public String displayGreeting(){
        return String.format("Welcome %s %s, Enjoy Shopping!", getForename(), getSurname());
    }
    
    public void addOrder(Order o)
    {
        DBManager db = new DBManager();
        int orderId = db.addOrder(o, this.getUsername());
        
        orders.put(orderId, o);
        orders.get(orderId).setOrderId(orderId);
    }
    
    public int generateUniqueOrderId()
    {
        int orderId = 0;
        for(Map.Entry<Integer, Order> element : orders.entrySet())
        {
            if(orders.containsKey(orderId))
            {
                orderId++;
            }
        }
        return orderId;
    }
    
    public Order findLatestOrder()
    {
        Order lastOrder = new Order();
        if(orders.isEmpty())
        {
            addOrder(lastOrder);
        }
        else
        {
            lastOrder = orders.entrySet().iterator().next().getValue();
            for(Map.Entry<Integer, Order> o : orders.entrySet())
            {
                Order actualOrder = o.getValue();
                if(actualOrder.getOrderDate().after(lastOrder.getOrderDate()))
                {
                    lastOrder = actualOrder;
                }
            }
            
            if(lastOrder.getStatus().equals("Complete"))
            {
                lastOrder = new Order();
                addOrder(lastOrder);
            }
        }
        return lastOrder;
    }
}
