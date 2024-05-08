package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{

	WebDriver driver;
	
	@FindBy(xpath="/html/body/form/table/tbody/tr[1]/td[2]/input")
	WebElement userId;
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="btnLogin")
	//@CacheLookup
	WebElement loginButton;

	public LoginPage(WebDriver Idriver) {
		super();
		this.driver = Idriver;
		
		PageFactory.initElements(Idriver, this);
	}


	public void setUserName(String username) {
		
		userId.sendKeys(username);
	
	}


	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}

	
	public void clickLoginButton() {
		loginButton.click();

	}
	
	
}
