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

public class ProductCatalog extends AbstractComponent {
	
	WebDriver driver;

	public ProductCatalog(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List <WebElement> products;
	
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By toastMessage = By.cssSelector("#toast-container");

	
	public List<WebElement> getProductList()
	{
		waitForElmentToAppear(productsBy); 
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod =	getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;

	}
	
	   
    public void addProductToCart(String productName ) {
    	
    	WebElement prod = getProductByName(productName);
    		    WebElement addToCartBtn = prod.findElement(By.cssSelector(".card-body button:last-of-type"));
    		    jsClick(addToCartBtn);
    		    waitForElmentToAppear(toastMessage); 
    		    waitForElmentToDisapper(spinner);
    		    
    }

/*	WebElement cart =  driver.findElement(By.cssSelector("[routerlink*='cart']"));
    js.executeScript("arguments[0].click();", cart);
 	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));	
 	*/
 	
}
