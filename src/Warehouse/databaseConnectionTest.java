package Warehouse;

import java.sql.*;

public class databaseConnectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		accessDB();
		
		//insertIntoDB("Gnome & Garden");
		
	} // end main

	// variables to connect to database

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	static final String DB_URL = "jdbc:mysql://Localhost/nbgardens";

	static final String USER = "root";

	static final String PASS = "netbuilder";

	public static void accessDB(){
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//Connect to database
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Read from database
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql2 = "SELECT employeeID, firstName, lastName FROM employee WHERE employeeID = 1";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
			 int id = rs.getInt("employeeID");
			 String fname = rs.getString("firstName");
			 String lname = rs.getString("lastName");
			 			 System.out.println("ID: " + id + ", name: " + fname + " " + lname + ".");
			 			 System.out.println("");
			}
			rs.close();

			
		}catch (SQLException sqle) {
			 sqle.printStackTrace();
		} catch (Exception e) {
		 e.printStackTrace();
		} finally {
		 try {
		  if (stmt != null)
		   conn.close();
		  } catch (SQLException se) { }
		  try {
		   if (conn != null)
		    conn.close();
		   } catch (SQLException se) {
		    se.printStackTrace();
		   }
		  }
		  System.out.println("Goodbye!");
		 

		
	} // end accessDB()
	
	
	public static void insertIntoDB(String name){
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//Connect to database
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Read from database
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql2 = "INSERT INTO supplier (name)"
					+ "VALUES ("
					+ "'" + name + "')";
			stmt.executeUpdate(sql2);

			
		}catch (SQLException sqle) {
			 sqle.printStackTrace();
		} catch (Exception e) {
		 e.printStackTrace();
		} finally {
		 try {
		  if (stmt != null)
		   conn.close();
		  } catch (SQLException se) { }
		  try {
		   if (conn != null)
		    conn.close();
		   } catch (SQLException se) {
		    se.printStackTrace();
		   }
		  }
		  System.out.println("Goodbye!");
		 

		
	} // end insertIntoDB()
	
} // end class
