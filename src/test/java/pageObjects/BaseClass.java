package pageObjects;

import java.time.Duration;

import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ReadConfig;

public class BaseClass {

	public WebDriver driver;
	ReadConfig readConfig = new ReadConfig();
	public String  baseURL= readConfig.getBaseURL();
	public String  userName = readConfig.getUserName();
	public String  password = readConfig.getPasswaord();
	public static Logger logger;

	
//	public String  baseURL= "https://demo.guru99.com/V4/index.php";
//	public String  userName = "1303";
//	public String  password = "Guru99";
//	

	@BeforeSuite
	public void setUpProject() {
		
		WebDriverManager.chromedriver().setup();
		//WebDriverManager.chromedriver().startRecording("Record");
		driver = new ChromeDriver();
				
	}
	
	@BeforeClass
	public void openBrowser()  {
		
      driver.get("https://www.google.com/");
      
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
      String cUrl = driver.getCurrentUrl();
      System.out.println(cUrl);
      String title = driver.getTitle();
      System.out.println(title);
     
      
	}
	@AfterClass
	public void finishTesting() {
		System.out.println("After Suite Browser is closing");
	      System.out.println("End Time: "+ System.currentTimeMillis());
	}
	
	@AfterSuite
	public void closeBrowser() {
		//if(!driver==null) {
		
		driver.quit();
//      System.out.println("After Suite Browser is closing");
//      System.out.println("End Time: "+ System.currentTimeMillis());
//	//}
	}
	
}
