	
	package dBTest;
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
	
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;
	
	public class DataBaseConnections {
			// Connection object
			static Connection con = null;
			// Statement object
			private static Statement stmt;
			//String dbName = "stldatabase";
			// Constant for Database URL
			public static String DB_URL = "jdbc:mysql://localhost:3306/stldatabase";// database name
			//Database Username
			public static String DB_USER = "stldatabase";
			// Database Password
			public static String DB_PASSWORD = "stldatabase1234";
			
			
			@BeforeTest
			public static void setUp() throws Exception {
		
	
				DataBaseReader db = new DataBaseReader();
				System.out.println(db.toString());
				
				// Database connection				
			try
			{
			String dbClass = "com.mysql.cj.jdbc.Driver";
			Class.forName(dbClass);
			
			// Get connection to DB
			Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("Database Connection Established successfully");
			
			// Statement object to send the SQL statement to the Database
			stmt = con.createStatement();
			System.out.println("************");
			
			}
			catch (Exception e)
			{
			e.printStackTrace();
			
			System.out.println("Database Connection Failed");
			System.out.println("************");

			
			}
			
			}
			
			@Test
			public void testData() throws SQLException {
				
			try
			{
			String query = "select * from user_profile";
			
			//Get the contents of user info  from user_profile table			
			ResultSet res = stmt.executeQuery(query);//DDL
				
			
			// Print the result untill all the records are printed
			// res.next() returns true if there is any next record else returns false
			while (res.next())
			{
			System.out.print(res.getString(1));
			System.out.print(" " + res.getString(2));
			System.out.print(" " + res.getString(3));
			System.out.println(" " + res.getString(4));
			}
			}
			catch(Exception e)
			{
				 // Display message when exceptions occurs

	        	System.out.println(e);

			//e.printStackTrace();
			}
			stmt.close(); // close statement

			}
			
			//@Test
			public void updateData() throws SQLException {
				
				
				String query = null;
				int update = stmt.executeUpdate(query);
				if(update ==1) {
					System.out.println("inserted successfully : " + query);
				}
				else
				{
	                System.out.println("insertion failed");

	                // Closing the connections
	                con.close();

				}
				
			}
			
			@AfterTest
			public void tearDown() throws Exception {
			// Close DB connection
			if (con != null) {
			con.close();
			// close connection
	        System.out.println("Connection Closed....");
			}
			}
			}