package rahulshettyacademy.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;

	public CartPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartButton;
	@FindBy(css= ".totalRow button")
	WebElement Checkout;
	
	   
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match = cartButton.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(productName));
		   return match;
	}
	
	public CheckoutPage gotocheckOut()
	{
		jsClick(Checkout);
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
}
