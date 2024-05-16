package dbTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.LoginPage;

public class DataTesting extends BaseClass {
	
	
	
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
				
				
				@BeforeClass
				public void setUp() {
			
		
//					DataBaseReader db = new DataBaseReader();
//					System.out.println(db.toString());
					
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
	
	@Test
	public void testData() throws SQLException {
		
	try
	{
		
		String query = "select * from user";
	
	//Get the contents of user info  from user_profile table			
	 ResultSet res = stmt.executeQuery(query);//DDL
		
	
	// Print the result untill all the records are printed
	// res.next() returns true if there is any next record else returns false
	while (res.next())
	{
		System.out.println(".........User Informations: ..........");
		
		String username = res.getString(3);
		String pw = res.getString(2);

	System.out.print(" \nUsername: "+res.getString(3));
	System.out.print(" \nPassword: " + res.getString(2));
	
	
	System.out.println("\n............");
//	LoginPage log = new LoginPage(driver);
//	log.setUserName(Username);
//	log.setPassword(pw);
//	log.clickLoginButton();
//	
//	LoginPage lo = new LoginPage(driver);	
//	lo.setUserName(username);
//	lo.setPassword(pw);	
//	lo.clickLoginButton();			
	String actualHomePage = driver.getTitle();
	System.out.println(actualHomePage);
	String expectedHomePage = "Guru99 Bank Manager HomePage";
	Assert.assertEquals(actualHomePage, expectedHomePage);
	
	
	driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input")).sendKeys(username);
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.name("btnLogin")).click();

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

	}
