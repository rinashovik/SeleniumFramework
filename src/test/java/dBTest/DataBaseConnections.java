	
	package dbTest;
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

import org.testng.annotations.AfterClass;
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
			static String username;
			static String password;
			
			
		//	@BeforeClass
			public static void setUpDbConnection() throws Exception {
		
	
				//DataBaseReader db = new DataBaseReader();
				//System.out.println(db.toString());
				
				// Database connection				
			try
			{
			String dbClass = "com.mysql.cj.jdbc.Driver";
			Class.forName(dbClass);
			
			// Get connection to DB
			Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			
			// Create Statement object to send the SQL statement to the Database
			stmt = con.createStatement();
			
			System.out.println("Database Connection Established successfully");
			//logger.info("Database Connection Established successfully")
			System.out.println("************");
			
			}
			catch (Exception e)
			{
			e.printStackTrace();
			
			System.out.println("Database Connection Failed");
			//logger.info("Database Connection Failed");
			System.out.println("************");

			
			}


			}
			
		//	@Test(priority=3)
			public void getUser() throws SQLException {
				
			try
			{
				
				//String query = "select * from user_profile";
				String query = "select * from user";

			//Get the contents of user info  from user_profile table			
			 ResultSet res = stmt.executeQuery(query);//DDL
				
			
			// Print the result untill all the records are printed
			// res.next() returns true if there is any next record else returns false
//			while (res.next())
//			{
//				System.out.println(".........User Informations: ..........");
//			System.out.print("\nId: "+res.getString(1));
//			System.out.print("\nState: " + res.getString(2));
//			System.out.print("\nemail: " + res.getString(3));
//			System.out.print("\nFirst Name: " + res.getString(4));
//			System.out.print("\nLast Name:  " + res.getString(5));
//			System.out.print("\n............");
//			
			
			
			while (res.next())
			{
				System.out.println(".........User Informations: ..........");
				
				String username = res.getString(3);
				String password = res.getString(2);

			System.out.print(" \nUsername: "+res.getString(3));
			System.out.print(" \nPassword: " + res.getString(2));
			
			
			System.out.println("\n............");
			


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
			
		//	@Test(priority=2)
			public void updateUser() throws SQLException {
				
				
				String query = "update user_profile set state ='Texas' where (id = 5)";
				int update = stmt.executeUpdate(query);
				if(update ==1) {
					System.out.println("User successfully Updated : " + query);
				}
				else
				{
	                System.out.println("User Update failed");

	                // Closing the connections
	                con.close();

				}
				
			}
			
		//	@Test(priority=4)
			public void deleteUser()  {
				
				
				String query = "delete from user_profile where first_name='Taba'";
				
				try {
					stmt.execute(query);
					
					int deleteUser = 0;
					
					if(deleteUser ==1) {
						System.out.println("inserted successfully : " + query);
					}
					else
						{
			                System.out.println("insertion failed");
		
			                // Closing the connections
			                con.close();
						}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
					
			}
				
			//@Test(priority=1)
			public void createUser()  {
				
				//execute sql statement
				
				String insertValues = "insert into user_profile values(19,'Michigan','code589@hoymail.com','Roy','Khanna','158-568-9687')";
				
				try {
					stmt.execute(insertValues);
					System.out.println("Values inserted");

					//logger.info("");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Error found");
				}
			
				
			}
			
			@AfterClass
			public void tearDown() throws Exception {
			// Close DB connection
			if (con != null) {
			con.close();
			// close connection
	        System.out.println("Connection Closed....");
			}
			}
			}