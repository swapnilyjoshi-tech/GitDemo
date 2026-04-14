package rahulshettyacademy.AbstractComponent;

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

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {
	
	
	private WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css = "[routerlink*='myorder']")
	WebElement orderHeader;
	
	
	public CartPage navigateToCartPage()
 	{
 		jsClick(cartButton);
 		CartPage cartPage = new CartPage(driver);
 		return cartPage;
 	}
	
	public OrderPage navigateToOrderPage()
 	{
 		jsClick(orderHeader);
 		OrderPage orderPage = new OrderPage(driver);
 		return orderPage;
 	}
	
	
	public void waitForElmentToAppear(By findby) {
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));		
	wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitForWebElmentToAppear(WebElement findby) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));		
		wait.until(ExpectedConditions.visibilityOf(findby));
		}

	public void waitForElmentToDisapper(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));		
		wait.until(ExpectedConditions.invisibilityOf(ele));
		}
	
	public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

}
