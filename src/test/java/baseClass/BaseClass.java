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
    void setUpProject() {

   
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.chromedriver().driverVersion("93.0.4577.63").setup();// setUp specific driver version

        
    
    }
    
    @Test(priority=1)
    void openBrowser() {

        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        
      //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

       String title = driver.getTitle();
        System.out.println("Title: " +title);
    	String url = driver.getCurrentUrl();
    	System.out.println("Current URL : " + url);
    	driver.get("https://www.globalsqa.com/samplepagetest/");


        
        
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
    
        
    
   // @Test(priority=3)
    void testSecondHomePage() throws AWTException {
    	
    	driver.get("https://www.globalsqa.com/samplepagetest/");
    	
    	
    	String url = driver.getCurrentUrl();
    	
    	System.out.println("Current URL : " + url);
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
    	
    	
    	driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        
    	   
        WebElement text = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div[1]/div/div/div/div[2]/h1"));
     	   System.out.println("HTML Element: " + text.getTagName());
     	   System.out.println("Text: "+text.getText());
     	   
     	   
     	   WebElement pic = driver.findElement(By.cssSelector("#wpcf7-f2598-p2599-o1 > form > p > strong"));
     	  
     	   
     	   System.out.println(pic.getText());
     	   
//     	   WebElement file = driver.findElement(By.xpath("//*[@id=\"wpcf7-f2598-p2599-o1\"]/form/p/span/input"));
//
//     	   //file.sendKeys("‪C:\\Users\\risho\\OneDrive\\Desktop\\TestCaseTemplate.xls");
//     	   
//
//     	   
//        file.click();
//     	//Thread.sleep(20001);
//        
//     	String myString = "‪C:\\Users\\risho\\OneDrive\\Desktop\\TestCaseTemplate.xls";   
//        StringSelection stringSelection = new StringSelection(myString);
//        Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();  
//        clipBoard.setContents(stringSelection, null);
//
//        Robot robot = new Robot();
//        
//     	   robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_V);
//        
//     	robot.keyRelease(KeyEvent.VK_CONTROL);
//        robot.keyRelease(KeyEvent.VK_V);
//        
//       robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//     	    	
//     	 
     	   
     	    
    }
    
    
    
   
    @Test(priority=4)
    void fileUpLoad() throws AWTException, InterruptedException {
    	
    	 WebElement file = driver.findElement(By.xpath("//*[@id=\"wpcf7-f2598-p2599-o1\"]/form/p/span/input"));

   	   //file.sendKeys("‪C:\\Users\\risho\\OneDrive\\Desktop\\TestCaseTemplate.xls");
   	   

   	   
      file.click();
   	Thread.sleep(2000);
      
   	String myString = "‪C:\\Users\\risho\\OneDrive\\Desktop\\TestCaseTemplate.xls";   
      StringSelection stringSelection = new StringSelection(myString);
      Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();  
      clipBoard.setContents(stringSelection, null);

      Robot robot = new Robot();
      
   	   robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_V);
      
   	robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_V);
      
     robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
   	    	
   	 
    }
    
  //  @Test
    void testForm() {
    	
    	
//    	driver.findElement(By.id("g2599-name")).sendKeys("Rina");
    	driver.findElement(By.xpath("//*[@id=\"g2599-name\"]")).sendKeys("Shovik");
    	
    	
    	
    	
    }
    
    
    
   // @AfterClass
    void closeBrowser() {
 
        driver.quit();
        System.out.println("Browser is closing");
    }

	
}





