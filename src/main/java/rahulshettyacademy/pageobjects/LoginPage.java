package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class LoginPage  extends AbstractComponent{
	
	WebDriver driver;

	public LoginPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement password;
	
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css= "[class*='flyInOut']")
	WebElement errorMessage;

	
	public ProductCatalog login(String uname, String Password) {
		userEmail.sendKeys(uname);
		password.sendKeys(Password);
		submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	public String getErrorMessage()
	{
		waitForWebElmentToAppear(errorMessage);
		return errorMessage.getText();
		
	}
}
