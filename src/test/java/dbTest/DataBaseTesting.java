package dbTest;


import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.LoginPage;



public class DataBaseTesting extends BaseClass{

	String dbName;
	DataBaseConnections db;

	public DataBaseTesting() {
		System.out.println("Creating DB Connection");
	}
	public DataBaseTesting(String dbName) {
		this.dbName = dbName;
		System.out.println("Creating DB Connection for: " +dbName);
	}
	
	
	@Test(priority=2)
	public void testDbConnection() throws Exception {
	DataBaseConnections.setUpDbConnection();
	 db = new DataBaseConnections();

		DataBaseConnections.setUpDbConnection();
		db.getUser();
		String userName = DataBaseConnections.username;
		System.out.println(userName);
		
		String password = DataBaseConnections.password;
		System.out.println(password);
	}	
	@Test(priority=1)
	public void testLandingPage() {
	logger.info(baseURL);
		driver.get(baseURL);
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));	
		System.out.println("Application URL: "+ baseURL);
		String actual = driver.getTitle();
		System.out.println(actual);
		String expected = "Guru99 Bank Home Page";
		Assert.assertEquals(actual, expected);
	}
		

	
	@Test(priority=3)
	public void testLoginPage(){


	LoginPage lo = new LoginPage(driver);

	lo.setUserName(userName);
	lo.setPassword(password);	
	lo.clickLoginButton();			
	String actualHomePage = driver.getTitle();
	System.out.println(actualHomePage);
	String expectedHomePage = "Guru99 Bank Manager HomePage";
	Assert.assertEquals(actualHomePage, expectedHomePage);
	logger.info(expectedHomePage);
	}	
	
	@Test(priority=4)
	public void testGetUsers() throws Exception {
		DataBaseConnections.setUpDbConnection();
		db.getUser();
		
	}
	
	//@Test(priority=4)

	public void testCreateUser() {
		
	}
	
	//@Test(priority=4)

	public void testUpdateUser() {
		
	}
	
	//@Test(priority=4)

	public void testDeleteUser() {
		
		
	}
	
}
