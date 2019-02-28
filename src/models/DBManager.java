package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DBManager
{
    //store the driver for reading from the databaseS
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    
    //store the hardcoded connection string of the database location
    private final String connectionString = "jdbc:ucanaccess://D:\\Java Projects\\Shop_30247197\\data\\ShopDB.accdb";
    
    //LOAD CUSTOMERS FROM THE DATABASE
    public HashMap<String, Customer> loadCustomers()
    {
        //instantiate an empty hashmap of customers
        HashMap<String, Customer> customers = new HashMap();
        
        try 
        {
            //establish the driver
            Class.forName(driver);
            
            //establish the connection
            Connection conn = DriverManager.getConnection(connectionString);
            
            //instantiate the sql statement
            Statement sql = conn.createStatement();
            
            //execute select statement query and store the results
            ResultSet rs = sql.executeQuery("Select * from Customers");
            
            //while there are rows left to loop through
            while(rs.next())
            {
                //store all the values from the current row of the result set
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                String Forename = rs.getString("FirstName");
                String Surname = rs.getString("LastName");
                String AddressLine1 = rs.getString("AddressLine1");
                String AddressLine2 = rs.getString("AddressLine2");
                String Town = rs.getString("Town");
                String Postcode = rs.getString("Postcode");
                
                //create a new customer using the values
                Customer customer = new Customer(AddressLine1, AddressLine2, Town, Postcode, true, Username, Password, Forename, Surname);
                
                //add the customer to the hashmap
                customers.put(Username, customer);
            }
            
            //close the connection
            conn.close();
            
        }
        //if exception is thrown
        catch (Exception e) 
        {
            //print error to console
            System.out.println("Error loading customers from database");
        }
        finally
        {
            //load customer orders into the hashmap
            customers = loadCustomerOrders(customers);
            
            //load customer orderlines into the hashmap
            customers = loadCustomerOrderLines(customers);
            
            //return hashmap of customers
            return customers;
        }
    }
    
    //LOAD STAFF FROM DATABASE
    public HashMap<String, Staff> loadStaff()
    {
        //instantiate an empty hashmap of staff
        HashMap<String, Staff> staff = new HashMap();
        
        try 
        {
            //establish the driver
            Class.forName(driver);
            
            //establish the connection
            Connection conn = DriverManager.getConnection(connectionString);
            
            //instantiate the sql statement
            Statement sql = conn.createStatement();
            
            //execute select statement query and store the results
            ResultSet rs = sql.executeQuery("Select * from Staff");
            
            //while there are rows left to loop through
            while(rs.next())
            {
                //store all the values from the current row of the result set
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                String Forename = rs.getString("FirstName");
                String Surname = rs.getString("LastName");
                String Position = rs.getString("Position");
                double Salary = rs.getDouble("Salary");
                
                //create a new staff member using the values
                Staff staffMember = new Staff(Position, Salary, Username, Password, Forename, Surname);
                
                //add the staff member to the hashmap
                staff.put(Username, staffMember);
            }
            
            //close the connection
            conn.close();
            
        } 
        //if exception is thrown
        catch (Exception e) 
        {
            //print error to console
            System.out.println("Error loading staff from database");
        }
        finally
        {
            //return hashmap of staff
            return staff;
        }
    }
    
    //LOAD PRODUCTS FROM THE DATABASE
    public HashMap<Integer, Product> loadProducts()
    {
        //instantiate an empty hashmap of products
        HashMap<Integer, Product> products = new HashMap();
        
        try 
        {
            //establish the driver
            Class.forName(driver);
            
            //establish the connection
            Connection conn = DriverManager.getConnection(connectionString);
            
            //instantiate the sql statement
            Statement sql = conn.createStatement();
            
            //execute select statement query and store the results
            ResultSet rs = sql.executeQuery("Select * from Products");
            
            //while there are rows left to loop through
            while(rs.next())
            {
                //store all the values from the current row of the result set
                int ProductId = rs.getInt("ProductId");
                String Name = rs.getString("ProductName");
                double Price = rs.getDouble("Price");
                int StockLevel = rs.getInt("StockLevel");
                String Measurement = rs.getString("Measurement");
                int Size = rs.getInt("Size");
                
                //if size isnt 0, then product must be footwear
                if(Size != 0)
                {
                    //create footwear using the values
                    Footwear footwear = new Footwear(Size, ProductId, Name, Price, StockLevel);
                    
                    //add footwear to products hashmap
                    products.put(ProductId, footwear);
                }
                //else product must be clothing
                else
                {
                    //create clothing using the values
                    Clothing clothing = new Clothing(Measurement, ProductId, Name, Price, StockLevel);
                    
                    //add clothing to products hashmap
                    products.put(ProductId, clothing);
                }
            }
            
            //close the connection
            conn.close();
        } 
        //if exception is thrown
        catch (Exception e) 
        {
            //print error to console
            System.out.println("Error loading products from database");
        }
        finally
        {
            //return hashmap of products
            return products;
        }
    }
    
    //EDIT PRODUCT IN THE DATABASE
    public void editProduct(Product p)
    {
        //instantiate the differentiators for products 
        String Measurement = "NULL";
        int Size = 0;
        
        //if the product is clothing
        if(p.getClass().getName().equals("models.Clothing"))
        {
            //cast product as clothing
            Clothing c = (Clothing) p;
            
            //store the measurement
            Measurement = String.valueOf(c.getMeasurement());
        }
        //else product is footwear
        else
        {
            //cast product as footwear
            Footwear f = (Footwear) p;
            
            //store the size
            Size = f.getSize();
        }
        
        try 
        {
            //establish the driver
            Class.forName(driver);
            
            //establish the connection
            Connection conn = DriverManager.getConnection(connectionString);
            
            //instantiate the sql statement
            Statement sql = conn.createStatement();
            
            //execute update statement in the database where the product Id's match
            sql.executeUpdate("UPDATE Products "
                    + "SET ProductName = '" + p.getProductName() + "'"
                    + ", Price = '" + p.getPrice() + "'"
                    + ", StockLevel = '" + p.getStockLevel() + "'"
                    + ", Measurement = '" + Measurement + "'"
                    + ", Size = " + Size
                    + " WHERE ProductID = '" + p.getProductID() + "'");
            
            //close the connection
            conn.close();
        } 
        //if exception is thrown
        catch (Exception e) 
        {
            //print error to console
            System.out.println("Error editing product in the database");
        }
    }
    
    //REMOVE PRODUCT FROM DATABASE
    public void deleteProduct(Product p)
    {
        try 
        {
            //establish the driver
            Class.forName(driver);
            
            //establish the connection
            Connection conn = DriverManager.getConnection(connectionString);
            
            //instantiate the sql statement
            Statement sql = conn.createStatement();
            
            //execute delete statement in the database where the product Id's match
            sql.executeUpdate("DELETE FROM Products WHERE ProductId = '" + p.getProductID() + "'");
        } 
        //if exception is thrown
        catch (Exception e) 
        {
            //print error to console
            System.out.println("Error deleting product from database");
        }
    }
    
    //ADD PRODUCT TO THE DATABASE
    public void addProduct(Product p)
    {
        //instantiate the differentiators for products 
        String Measurement = "NULL";
        int Size = 0;
        
        //if the product is clothing
        if(p.getClass().getName().equals("models.Clothing"))
        {
            //cast product as clothing
            Clothing c = (Clothing) p;
            
            //store the measurement
            Measurement = String.valueOf(c.getMeasurement());
        }
        //else product is footwear
        else
        {
            //cast product as footwear
            Footwear f = (Footwear) p;
            
            //store the size
            Size = f.getSize();
        }
        
        try 
        {
            //establish the driver
            Class.forName(driver);
            
            //establish the connection
            Connection conn = DriverManager.getConnection(connectionString);
            
            //instantiate the sql statement
            Statement sql = conn.createStatement();
            
            //execute insert statement to add product into products table
            sql.executeUpdate("INSERT INTO Products (ProductName, Price, StockLevel, Measurement, Size) "
                + "VALUES ('" + p.getProductName() + "' , '"
                + p.getPrice()  + "' , '" 
                + p.getStockLevel()  + "' , '"
                + Measurement  + "' , "
                + Size + ")");
            
            
            //close the connection
            conn.close();
        } 
        //if exception is thrown
        catch (Exception e) 
        {
            //print error to console
            System.out.println("Error adding product to the database");
        }
    }
    
    public Customer customerLogin(String Username, String Password)
    {
        HashMap<String, Customer> customers = loadCustomers();
        
        if(customers.containsKey(Username))
        {
            Customer foundCustomer = customers.get(Username);
            if(foundCustomer.getPassword().equals(Password))
                return foundCustomer;
        }
        return null;
    }
    
    public Staff staffLogin(String Username, String Password)
    {
        HashMap<String, Staff> staff = loadStaff();
        
        if(staff.containsKey(Username))
        {
            Staff staffMember = staff.get(Username);
            if(staffMember.getPassword().equals(Password))
                return staffMember;
        }
        return null;
    }
    
    public boolean registerCustomer(Customer c)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
             
            Statement sql = conn.createStatement();
            sql.executeUpdate("INSERT INTO Customers(Username, Password, FirstName, LastName, AddressLine1, AddressLine2, Town, Postcode)"
                    + " VALUES ('" 
                    + c.getUsername() + "' , '" 
                    + c.getPassword()  + "' , '" 
                    + c.getForename() + "' , '" 
                    + c.getSurname() + "' , '" 
                    + c.getAddressLine1() + "' , '" 
                    + c.getAddressLine2() + "' , '" 
                    + c.getTown() + "' , '" 
                    + c.getPostcode() + "')");
            
            conn.close();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error writing to the database");
            return false;
        }
    }
    
    public void editCustomer(Customer c)
    {
        try 
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            Statement sql = conn.createStatement();
            sql.executeUpdate("UPDATE Customers "
                    + "SET Password = '" + c.getPassword() + "'"
                    + ", FirstName = '" + c.getForename() + "'"
                    + ", LastName = '" + c.getSurname() + "'"
                    + ", AddressLine1 = '" + c.getAddressLine1() + "'"
                    + ", AddressLine2 = '" + c.getAddressLine2() + "'"
                    + ", Town = '" + c.getTown() + "'"
                    + ", Postcode = '" + c.getPostcode() + "'"
                    + " WHERE Username = '" + c.getUsername() + "'");
            conn.close();
        } 
        catch (Exception e) 
        {
            System.out.println("Error writing to the database");
        }
    }
    
    public void deleteCustomer(Customer c)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement sql = conn.createStatement();
            
            sql.executeUpdate("DELETE FROM Customers WHERE Username = '" + c.getUsername() + "'");
            conn.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error deleting from the database");
        }
    }
    
        public int addOrderLine(OrderLine ol, int orderId)
    {
        int orderLineId = 0;
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement sql = conn.createStatement();
            sql.executeUpdate("INSERT INTO OrderLines (ProductId, Quantity, LineTotal, OrderId)"
                    + " VALUES ('"
                    + ol.getProduct().getProductID() + "', '"
                    + ol.getQuantity() + "', '"
                    + ol.getLineTotal() + "', "
                    + orderId + ")");
            
            ResultSet rs = sql.getGeneratedKeys();
            
            if(rs.next())
            {
                orderLineId = rs.getInt(1);
            }
         
            conn.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error writing orderline to database");
        }
        return orderLineId;
    }
    
    public int addOrder(Order o, String username)
    {
        int orderId = 0;
        
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement sql = conn.createStatement();
            
            sql.executeUpdate("INSERT INTO Orders (OrderDate, Username, OrderTotal, Status)"
                    + " VALUES ('" 
                    + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(o.getOrderDate()) + "', '"
                    + username + "', '"
                    + o.getOrderTotal() + "', '"
                    + o.getStatus() + "')");
            
            ResultSet rs = sql.getGeneratedKeys();
            
            if(rs.next())
            {
                orderId = rs.getInt(1);
            }
         
            conn.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error writing order to database");
        }
        return orderId;
    }
    
    public void updateOrderTotal(int orderId, double newTotal)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement sql = conn.createStatement();
            
            sql.executeUpdate("UPDATE Orders "
                    + "SET OrderTotal = '" + newTotal
                    + "' WHERE OrderId = '" + orderId + "'");
            
            conn.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error updating ordertotal to database");
        }        
    }
    
    public void completeOrder(int orderId)
    {
        try 
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement sql = conn.createStatement();
            
            sql.executeUpdate
                    ("UPDATE Orders " 
                    + "SET Status = 'Complete' "
                    + "WHERE OrderId = '" + orderId + "'");
            
            conn.close();
        } 
        catch (Exception e)
        {
             System.out.println("Error completing order");
        }
    }
    
    public void deleteOrderLine(int orderLineId)
    {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement sql = conn.createStatement();
            
            sql.executeUpdate
                    ("DELETE FROM OrderLines "
                    + "WHERE OrderLineId = '" + orderLineId + "'");
            conn.close();
            
        } 
        catch (Exception e) 
        {
             System.out.println("Error deleting order line from database");
        }
    }
    
    public HashMap<String, Customer> loadCustomerOrders(HashMap<String, Customer> customers)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement sql = conn.createStatement();
            
            ResultSet rs = sql.executeQuery("SELECT * FROM Orders");
            
            while(rs.next())
            {
                int orderId = rs.getInt("OrderId");
                
                String orderDateStr = rs.getString("OrderDate");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date orderDate = format.parse(orderDateStr);
                
                String customer = rs.getString("Username");
                double orderTotal = rs.getDouble("OrderTotal");
                String status = rs.getString("Status");
                
                Customer customerWithOrder = customers.get(customer);
                Order loadedOrder = new Order(orderId, orderDate, customerWithOrder, orderTotal, status);
                
                customerWithOrder.getOrders().put(orderId, loadedOrder);
            }
        }
        catch (Exception e) 
        {
            System.out.println("Error loading orders from database");
        }
        finally
        {
            return customers;
        }
    }
    
    public HashMap<String, Customer> loadCustomerOrderLines(HashMap<String, Customer> customers)
    {
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement sql = conn.createStatement();
            
            ResultSet rs = sql.executeQuery("SELECT * FROM OrderLines");
            
            while(rs.next())
            {
                int orderLineId = rs.getInt("OrderLineId");
                int productId = rs.getInt("ProductId");
                int quantity = rs.getInt("Quantity");
                int orderId = rs.getInt("OrderId");
                
                for(Map.Entry<String, Customer> cEntry : customers.entrySet())
                {
                    Customer customer = cEntry.getValue();
                    if(customer.getOrders().containsKey(orderId))
                    {
                        Order orderForOrderLine = customer.getOrders().get(orderId);
                        
                        Product product = loadProducts().get(productId);
                        OrderLine orderLine = new OrderLine(orderLineId, product, quantity);
                        
                        orderForOrderLine.getOrderLines().put(orderLineId, orderLine);
                    }
                }
            }
        }
        catch (Exception e) 
        {
            System.out.println("Error loading orders from database");
        }
        finally
        {
            return customers;
        }
    }
    
    public void editOrderLine(OrderLine orderLine)
    {
        try 
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement sql = conn.createStatement();
            
            sql.executeUpdate(
                    "UPDATE OrderLines "
                    + "SET Quantity = '" + orderLine.getQuantity() + "',"
                    + " LineTotal = '" + orderLine.getLineTotal() + "'"
                    + " WHERE OrderLineId = '" + orderLine.getOrderLineId() + "'"
            );
            conn.close();
        } 
        catch (Exception e) 
        {
             System.out.println("Error editing orderline in database");
        }
    }
    
    public void editStockLevel(Product product, OrderLine orderLine)
    {
        int quantity = orderLine.getQuantity();
        int stock = product.getStockLevel() - quantity;
        
        try 
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement sql = conn.createStatement();
            
            sql.executeUpdate("UPDATE Products "
                            + "SET StockLevel = '" + stock + "' "
                            + "WHERE ProductId = '" + product.getProductID() + "'");
            
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("Error editing stock level in database");
        }
    }
}
