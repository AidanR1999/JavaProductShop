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
    
    //ADD ORDER TO DATABASE
    public void addOrder(Order o)
    {
        //new instance of database
        DBManager db = new DBManager();
        //add order to database and get orderId
        int orderId = db.addOrder(o, this.getUsername());
        
        //put order in HashMap
        orders.put(orderId, o);
        orders.get(orderId).setOrderId(orderId);
    }
    
    //GENERTATE UNIQUE ORDER ID FOR ADDING ORDERS
    public int generateUniqueOrderId()
    {
        //initialise order id
        int orderId = 0;
        
        //for every order in the hashmap
        for(Map.Entry<Integer, Order> element : orders.entrySet())
        {
            //if the order id already exists in the hasmap
            if(orders.containsKey(orderId))
            {
                //increment the order id
                orderId++;
            }
        }
        
        //return the unique order id
        return orderId;
    }
    
    //FIND LATEST ORDER IN THE DATABASE
    public Order findLatestOrder()
    {
        //initialise new order
        Order lastOrder = new Order();
        
        //if the customers orders are empty
        if(orders.isEmpty())
        {
            //add an empty order to the hashmap
            addOrder(lastOrder);
        }
        else
        {
            //store the last order in the orders hashmap
            lastOrder = orders.entrySet().iterator().next().getValue();
            
            //for every order in the orders hashmap
            for(Map.Entry<Integer, Order> o : orders.entrySet())
            {
                //store the current order in the loop
                Order actualOrder = o.getValue();
                
                //if the date of the current order is newer than the date of the last order
                if(actualOrder.getOrderDate().after(lastOrder.getOrderDate()))
                {
                    //set the last order as the current order
                    lastOrder = actualOrder;
                }
            }
            
            //if the latest order is complete
            if(lastOrder.getStatus().equals("Complete"))
            {
                //create a new order
                lastOrder = new Order();
                
                //add the order to the database
                addOrder(lastOrder);
            }
        }
        
        //return the latest order
        return lastOrder;
    }
}
