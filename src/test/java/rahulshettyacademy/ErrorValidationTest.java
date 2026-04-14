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
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LoginPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

import rahulshettyacademy.TestComponents.Retry;
public class ErrorValidationTest extends BaseTest{

@Test(retryAnalyzer= Retry.class)	
public void LoginErrorValidation() throws Exception
	{
		
		//loginPage.goTo();
		
		ProductCatalog productCatalog = loginPage.login("swapnilyjoshi@gmail.com","Edenred2018**");
		loginPage.getErrorMessage();
		Assert.assertEquals(loginPage.getErrorMessage(),"Incorrect email or password.");
		System.out.println("Git changes");
        
	}
	
	@Test(groups= {"ErrorHandling"})
	public void ProductErrorValidation()
	{
	String productName = "ZARA COAT 3";
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		loginPage.goTo();

		ProductCatalog productCatalog = loginPage.login("swapnilyjoshi@gmail.com","Edenred2018*");
		System.out.println("Login successfullt");
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.navigateToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);

	}
	

}
