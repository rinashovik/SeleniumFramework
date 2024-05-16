package pageObjects;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;import dbTest.DataBaseConnections;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ReadConfig;

public class BaseClass {

	public WebDriver driver;
	ReadConfig readConfig = new ReadConfig();
	public String  baseURL= readConfig.getBaseURL();
	public String  userName = readConfig.getUserName();
	public String  password = readConfig.getPasswaord();
	
	public String browser= readConfig.getBrowserName();
	public String bUrl = readConfig.getBrUrl();
	public static Logger logger;

	
//	public String  baseURL= "https://demo.guru99.com/V4/index.php";
//	public String  userName = "1303";
//	public String  password = "Guru99";
//
	
	@Parameters("browser")
	@BeforeSuite
	public void init(){
				
	//public void init(String browser) {
		logger= (Logger) LogManager.getLogger();
		logger.getName();
		
		try {
			
		if(browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("google")) 
		{
		WebDriverManager.chromedriver().setup();
		//WebDriverManager.chromedriver().startRecording("Record");
		driver = new ChromeDriver();
		driver.get("bUrl");

		}
		else if(browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("mozilla")) 
		
		{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.get("bUrl");
		
		}
		else if(browser.equalsIgnoreCase("ie") || browser.equalsIgnoreCase("internet explorer")) 
		{
		WebDriverManager.iedriver().setup();	
		driver = new InternetExplorerDriver();			
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();	
		driver.get("bUrl");

	}
		else 
	{
		throw new IllegalArgumentException();	
	}
		
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	
				
	}
			
		
	@BeforeTest
	public void setUp(){
		
	      //driver.get("https://www.google.com/");
			driver.get(bUrl);
			System.out.println("Browser URL: " + bUrl);
			System.out.println("Browser Name: " + browser);
			
      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
      driver.manage().window().maximize();
      String cUrl = driver.getCurrentUrl();
      System.out.println("Current URL: "+cUrl);
      String title = driver.getTitle();
      System.out.println("Title: "+title);
      logger.info("Browesr successfully set up");
	   driver.switchTo().newWindow(WindowType.TAB);
      driver.get("https://demo.guru99.com/V4/index.php");      
      driver.manage().window().maximize();

      
      
	}
	
	@AfterTest
	public void finishTesting(){
		System.out.println("After Suite Browser is closing");
	    System.out.println("End Time: "+ System.currentTimeMillis());
	    
	}
	
	//@AfterSuite
	public void closeBrowser() {
		//if(!driver==null) {
		
		driver.quit();
		
	}
	
}
