package org.pharmacy.locator.helper;

import java.sql.*;

public class ConnectionHelper {
	
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection con = DriverManager.getConnection("jdbc:mysql://192.168.209.1:3306/Pharmacy?useSSL=false", "root", "root");
    	return con;
	}
}
