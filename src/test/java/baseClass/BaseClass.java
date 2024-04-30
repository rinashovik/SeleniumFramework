package baseClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
		

    WebDriver driver;
		    
		@BeforeClass
		public void setUpProject() {

   
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.chromedriver().driverVersion("93.0.4577.63").setup();// setUp specific driver version

        
    
    }
    
    @Test(priority=1)
    public void openBrowser() {

        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        
      //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String title = driver.getTitle();
        System.out.println("Title: " +title);
    	String url = driver.getCurrentUrl();
    	System.out.println("Current URL : " + url);


        
        
    }
    
    
    
    @Test(priority=2, enabled=false)
    void testOpenHomePage() {
    	
    	
       	driver.get("https://www.timeanddate.com/");
    	
    	//driver.get("https://trytestingthis.netlify.app/");
    	
    	String url = driver.getCurrentUrl();
    	
    	System.out.println("Current URL : " + url);
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
    	
    	
    	driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));         

     	   WebElement text = driver.findElement(By.xpath("/html/body/div[5]/main/article/section/div[1]/section[1]/h3"));

     	   System.out.println("HTML Element: " + text.getTagName());
     	   System.out.println("Text: "+text.getText());
     	  
     	   WebElement ct = driver.findElement(By.id("clk_box"));    	       	   
     	   System.out.println("Curent Time: " +ct.getText());
     	   
     	   driver.switchTo().newWindow(WindowType.TAB);

     	    
    }
    
        
    
    @Test(priority=3)
    void testSecondHomePage() throws AWTException {
    	
    	driver.get("https://www.globalsqa.com/samplepagetest/");  
    	
    	driver.manage().window().maximize();
    	
    	String url = driver.getCurrentUrl();
    	System.out.println("Current URL : " + url);
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);  
    	
    	driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        
    	   
        WebElement text = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div[1]/div/div/div/div[2]/h1"));
     	   System.out.println("HTML Element: " + text.getTagName());
     	   System.out.println("Text: "+text.getText());
    	   
     	   
     	   WebElement text2 = driver.findElement(By.cssSelector("#wpcf7-f2598-p2599-o1 > form > p > strong"));
     	  
     	   
     	   System.out.println(text2.getText());
     	   
     	   
     	//Shortcut way to upload file   
     	   
     	//String filePath = System.getProperty("user.dir")+ "\\Files\\shadow.jpg";//good

    	WebElement fileLocator= driver.findElement(By.xpath("//*[@id=\"wpcf7-f2598-p2599-o1\"]/form/p/span/input"));
     	   
     	  //fileLocator.sendKeys(filePath); //Upload file from project directory

    	//fileLocator.sendKeys("C:\\Users\\risho\\OneDrive\\Desktop\\TestCaseTemplate.xls");//Good
    	//System.out.println("File Successfully Uploaded: " + filePath);

    	
    	//Using Robot Class
    	
    	    	 // driver.findElement(By.xpath("//*[@id=\"wpcf7-f2598-p2599-o1\"]/form/p/span/input")).click()

    	    		fileLocator.click();//  if click() doesn't work then use JavascriptExecutor Class

  	    	 //JavascriptExecutor js = (JavascriptExecutor)driver;
   	    	 
   	   //js.executeScript(filePath, null);
   	  // js.executeScript("arguments[0].click();",fileLocator);// Click action on the fileLocator
   	   
   	   /*
   	    * Copy the path
   	    *CTRL +V
   	    *Enter
   	    * 
   	    * 
   	    * 
   	    */


        Robot robot = new Robot();
        robot.delay(5000);
        
        // copy the file in the clipboard
     	String myString = "C:\\Users\\risho\\OneDrive\\Desktop\\TestCaseTemplate.xls"; 
     	
     	
        StringSelection stringSelection = new StringSelection(myString);
        // clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();  
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);  
   	//clipboard.setContents(stringSelection, null);
  
        // CTRL + V (press)
     	   robot.keyPress(KeyEvent.VK_CONTROL);
     	   robot.keyPress(KeyEvent.VK_V);
     	   
        // Release the Key
     	   robot.keyRelease(KeyEvent.VK_CONTROL);
     	   robot.keyRelease(KeyEvent.VK_V);
     	   
        // Enter the Key
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
     	    	
     	    	System.out.println("File Successfully Uploaded: " + myString);        
     	    	
     	    	
          	   driver.switchTo().newWindow(WindowType.TAB);

     	    
    }
    
    
    
   
    @Test(priority=4, enabled=false)
    public void fileUpLoad() throws AWTException, InterruptedException {
    	
    	driver.get("https://omayo.blogspot.com/"); 
    	driver.manage().window().maximize();
    	
    	driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
       
			String path = System.getProperty("user.dir")+"\\Files\\testingData.docx";

    	WebElement file = driver.findElement(By.id("uploadfile"));
    	
    	// shortcut way to upload file
    	
    	   	 // file.sendKeys(filePath+ "\\Files\\testingData.docx");//Good
    	   	  file.sendKeys(path);//Good

    	   	  
    	   	  //file.sendKeys(filePath + "\\Files\\sunset_2.jpg");// good

    			//file.sendKeys("C:\\Users\\risho\\OneDrive\\Desktop\\TestCaseTemplate.xls");//Good
    	   	  System.out.println("File Successfully Uploaded: " + path);

   	 
    }
    
 
    
    
    
    @AfterClass
    void closeBrowser() {
 
        driver.quit();
        System.out.println("Browser is closing");
    }

	
}





