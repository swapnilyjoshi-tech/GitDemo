package rahulshettyacademy.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement Country;

	@FindBy(css = ".action__submit")
	WebElement Submit;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement SelectCountry;
	By results = By.cssSelector(".ta-results");

	public void selectCountry(String CountryName) throws InterruptedException {
		   Thread.sleep(3000);

		Actions a = new Actions(driver);
		a.sendKeys(Country, CountryName).build().perform();
		waitForElmentToAppear(results);
		jsClick(SelectCountry);
	}
	
	public  ConfirmationPage submitOrder()
	{
		jsClick(Submit);
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}