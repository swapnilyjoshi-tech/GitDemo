package rahulshettyacademy;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LoginPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class StandAlonTest extends BaseTest{
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData",groups="purchase")
public void submitOrder(HashMap<String,String> input) throws Exception
	{
		String productName = input.get("product");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		loginPage.goTo();

		ProductCatalog productCatalog = loginPage.login(input.get("email"),input.get("password"));
		System.out.println("Login successfullt");
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.navigateToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.gotocheckOut();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
        
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
public void OrderHistoryTest() throws Exception
	{
		ProductCatalog productCatalog = loginPage.login("swapnilyjoshi@gmail.com","Edenred2018*");
		OrderPage orderPage = productCatalog.navigateToOrderPage();
		Assert.assertTrue( orderPage.verifyOrderDisplay(productName));
		        Thread.sleep(5000);
	}

/*
	@DataProvider
	public Object [][] getData()
	{
		return new Object [][] {{"swapnilyjoshi@gmail.com","Edenred2018*","ZARA COAT 3"},{"swapnilyjoshi@gmail.com","Edenred2018*","ADIDAS ORIGINAL"}};
		
	}
	*/
	@DataProvider
	public Object [][] getData() throws Exception
	{/*
		HashMap<Object,Object> map =  new HashMap<Object,Object>();
		map.put("uname","swapnilyjoshi@gmail.com");
		map.put("pass","Edenred2018*");
		map.put("productname","ZARA COAT 3");
		
		HashMap<Object,Object> map1 =  new HashMap<Object,Object>();
		map1.put("uname","swapnilyjoshi@gmail.com");
		map1.put("pass","Edenred2018*");
		map1.put("productname","ADIDAS ORIGINAL");
		*/
		
		List <HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + 
                "/src/test/java/rahulshettyacademy/data/Purchaseorder.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
		
	}
	
	
}
