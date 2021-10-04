/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retail_store_management_system;

import java.sql.*;

/**
 *
 * @author IR
 */
public class Connect {
    
    //PROJECT DETAILS
    static String projectName="Retail Store Management System";
    static String projectDeveloper="";
    static String projectUsername="admin";
    static String projectPassword="test";
    
    
    //MYSQL Details
    static String mysqlUsername="root"; // by default root is the username
    static String mysqlPassword=""; // Set your mysql password
    static String mysqlHost="localhost"; // by default localhost is the host for mysql
    static String mysqlPort="3306"; // by default 3306 is the default mysql port number
    
    
    public static Statement statement = null;
    public static Connection connection;
    public static ResultSet rs;
    public static PreparedStatement pstmt;
    
    /////Function for connect to the MySQL Server Database////////////
    public static void connect_mysql()
    {
    	try  
    	{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
    		connection = DriverManager.getConnection("jdbc:mysql://localhost/retail_store_management_system?" + "user="+mysqlUsername);
			statement=connection.createStatement();
   		}
   		catch(Exception e)  
                {
			System.out.println(" Error : "+ e.toString());
                }
    }
    
} 