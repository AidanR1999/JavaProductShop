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
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    //net.ucanaccess.jdbc.UcanaccessDriver
    private final String connectionString = "jdbc:ucanaccess://D:\\Java Projects\\Shop_30247197\\data\\ShopDB.accdb";
    
    public HashMap<String, Customer> loadCustomers()
    {
        
        HashMap<String, Customer> customers = new HashMap();
        
        try 
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Customers");
            
            while(rs.next())
            {
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                String Forename = rs.getString("FirstName");
                String Surname = rs.getString("LastName");
                String AddressLine1 = rs.getString("AddressLine1");
                String AddressLine2 = rs.getString("AddressLine2");
                String Town = rs.getString("Town");
                String Postcode = rs.getString("Postcode");
                
                Customer customer = new Customer(AddressLine1, AddressLine2, Town, Postcode, true, Username, Password, Forename, Surname);
                customers.put(Username, customer);
            }
            conn.close();
            
        } 
        catch (Exception e) 
        {
            String message = e.getMessage();
        }
        finally
        {
            customers = loadCustomerOrders(customers);
            customers = loadCustomerOrderLines(customers);
            return customers;
        }
    }
    public HashMap<String, Staff> loadStaff()
    {
        
        HashMap<String, Staff> staff = new HashMap();
        
        try 
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Staff");
            
            while(rs.next())
            {
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                String Forename = rs.getString("FirstName");
                String Surname = rs.getString("LastName");
                String Position = rs.getString("Position");
                double Salary = rs.getDouble("Salary");
                
                Staff staffMember = new Staff(Position, Salary, Username, Password, Forename, Surname);
                staff.put(Username, staffMember);
            }
            conn.close();
            
        } 
        catch (Exception e) 
        {
            String message = e.getMessage();
        }
        finally
        {
            return staff;
        }
    }
    
    public HashMap<Integer, Product> loadProducts()
    {
        
        HashMap<Integer, Product> products = new HashMap();
        
        try 
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Products");
            
            while(rs.next())
            {
                int ProductId = rs.getInt("ProductId");
                String Name = rs.getString("ProductName");
                double Price = rs.getDouble("Price");
                int StockLevel = rs.getInt("StockLevel");
                String Measurement = rs.getString("Measurement");
                int Size = rs.getInt("Size");
                
                if(Size != 0)
                {
                    Footwear footwear = new Footwear(Size, ProductId, Name, Price, StockLevel);
                    products.put(ProductId, footwear);
                }
                else
                {
                    Clothing clothing = new Clothing(Measurement, ProductId, Name, Price, StockLevel);
                    products.put(ProductId, clothing);
                }
            }
            conn.close();
            
        } 
        catch (Exception e) 
        {
            String message = e.getMessage();
        }
        finally
        {
            return products;
        }
    }
    
    public void editProduct(Product p)
    {
        String Measurement = "NULL";
        int Size = 0;
        
        if(p.getClass().getName().equals("models.Clothing"))
        {
            Clothing c = (Clothing) p;
            Measurement = String.valueOf(c.getMeasurement());
        }
        else
        {
            Footwear f = (Footwear) p;
            Size = f.getSize();
        }
        
        try 
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            Statement sql = conn.createStatement();
            
            sql.executeUpdate("UPDATE Products "
                    + "SET ProductName = '" + p.getProductName() + "'"
                    + ", Price = '" + p.getPrice() + "'"
                    + ", StockLevel = '" + p.getStockLevel() + "'"
                    + ", Measurement = '" + Measurement + "'"
                    + ", Size = " + Size
                    + " WHERE ProductID = '" + p.getProductID() + "'");
            
            conn.close();
        } 
        catch (Exception e) 
        {
            System.out.println("Error writing to the database");
        }
    }
    
    public void deleteProduct(Product p)
    {
        try 
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement sql = conn.createStatement();
            
            sql.executeUpdate("DELETE FROM Products WHERE ProductId = '" + p.getProductID() + "'");
        } 
        catch (Exception e) 
        {
            System.out.println("Error deleting product from database");
        }
    }
    
    public void addProduct(Product p)
    {
        String Measurement = "NULL";
        int Size = 0;
        
        if(p.getClass().getName().equals("models.Clothing"))
        {
            Clothing c = (Clothing) p;
            Measurement = String.valueOf(c.getMeasurement());
        }
        else
        {
            Footwear f = (Footwear) p;
            Size = f.getSize();
        }
        
        try 
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            Statement sql = conn.createStatement();
            
            sql.executeUpdate("INSERT INTO Products (ProductName, Price, StockLevel, Measurement, Size) "
                + "VALUES ('" + p.getProductName() + "' , '"
                + p.getPrice()  + "' , '" 
                + p.getStockLevel()  + "' , '"
                + Measurement  + "' , "
                + Size + ")");
            
            
            conn.close();
        } 
        catch (Exception e) 
        {
            System.out.println("Error writing to the database");
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
                double lineTotal = rs.getDouble("LineTotal");
                int orderId = rs.getInt("OrderId");
                
                for(Map.Entry<String, Customer> cEntry : customers.entrySet())
                {
                    Customer customer = cEntry.getValue();
                    if(customer.getOrders().containsKey(orderId))
                    {
                        Order orderForOrderLine = customer.getOrders().get(orderId);
                        
                        Product product = loadProducts().get(productId);
                        OrderLine orderLine = new OrderLine(orderLineId, product, quantity, lineTotal);
                        
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
}
