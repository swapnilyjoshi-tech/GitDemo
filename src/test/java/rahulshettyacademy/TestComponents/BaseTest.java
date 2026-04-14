package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver intilizeDriver() throws Exception {

		Properties prop = new Properties();
		String path = System.getProperty("user.dir")
				+ "//src//main//java//rahulshettyacademy//resource//GlobalData.properties";

		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		//String browserName = prop.getProperty("browser");
		// System.out.println("Your properties :-" + browserName);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		if (browserName.contains("chrome")) {
			ChromeOptions option = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
				option.addArguments("headless");

			}
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			//WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.edge.driver", "C:\\Users\\swapn\\Downloads\\edgedriver_win64\\msedgedriver.exe");
			//C:\Users\swapn\Downloads\edgedriver_win64
			//System.setProperty("webdriver.edge.driver","edge.exe");
			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws Exception {

		String filePath = filepath;

		File jsonFile = new File(filePath);
		String jsonContent = FileUtils.readFileToString(jsonFile, "UTF-8"); // ✅ Modern syntax

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getsScreenShot(String testcasename, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcasename + ".png";

	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws Exception {
		driver = intilizeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
