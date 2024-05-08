package testcases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.LoginPage;

	public class Test_TC_LoginPage_001 extends BaseClass{
			
		@Test(priority=1)
		public void testLoginPage() {
		
			driver.get(baseURL);
			driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
	
			
			String actual = driver.getTitle();
			System.out.println(actual);
			String expected = "Guru99 Bank Home Page";
			Assert.assertEquals(actual, expected);
		}
		
		@Test(priority=2)
			public void testHomePageTitle() {
			
			LoginPage lp = new LoginPage(driver);
	
			lp.setUserName(userName);
			lp.setPassword(password);
	
			lp.clickLoginButton();
			
			String actualHomePage = driver.getTitle();
			System.out.println(actualHomePage);
			String expectedHomePage = "Guru99 Bank Customer HomePage";
			Assert.assertEquals(actualHomePage, expectedHomePage);
			
		}
}
