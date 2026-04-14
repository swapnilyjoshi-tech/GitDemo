package rahulshettyacademy;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LoginPage;

public class StandAlonTest2 {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		LoginPage loginPage = new LoginPage( driver);
		loginPage.goTo();
        loginPage.login("swapnilyjoshi@gmail.com","Edenred2018*");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		/*
		driver.findElement(By.id("userEmail")).sendKeys("swapnilyjoshi@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Edenred2018*");
		driver.findElement(By.id("login")).click();
		*/
		/*
		List<WebElement> products  =  driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().
				filter(product-> product.findElement(By.cssSelector("b"))
						.getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); */
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
WebElement prod =	products.stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
     WebElement addToCartBtn = prod.findElement(By.cssSelector(".card-body button:last-of-type"));
     JavascriptExecutor js = (JavascriptExecutor) driver;
     js.executeScript("arguments[0].click();", addToCartBtn);

   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
   //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
   wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
   
   WebElement cart =  driver.findElement(By.cssSelector("[routerlink*='cart']"));
   js.executeScript("arguments[0].click();", cart);
   
   
   List<WebElement> cartproducts =  driver.findElements(By.cssSelector(".cartSection h3"));
   
   Boolean match = cartproducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(productName));
   Assert.assertTrue(match);
   
   WebElement Checkout  = driver.findElement(By.cssSelector(".totalRow button"));
 //li[@class='totalRow']/button
   js.executeScript("arguments[0].click();", Checkout);
   /*
   WebElement Country  = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
  // js.executeScript("arguments[0].value = 'Ind'; arguments[0].dispatchEvent(new Event('input'));", Country);
   String text = "Ind";
   for(char c : text.toCharArray()) {
       js.executeScript("arguments[0].value += '" + c + "'; arguments[0].dispatchEvent(new Event('input'));", Country);
       Thread.sleep(100);
   }*/
  
   Thread.sleep(3000);
   Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
   //a.click(driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")));
   //driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
   WebElement country  = driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)"));

   js.executeScript("arguments[0].click();", country);		

   WebElement submit  = driver.findElement(By.cssSelector(".action__submit"));
   js.executeScript("arguments[0].click();", submit);		
//driver.findElement(By.cssSelector(".action__submit")).click();
boolean label = driver.findElement(By.cssSelector(".hero-primary")).isDisplayed();

Assert.assertTrue(label);

  
   
   
   
   
	}

}
