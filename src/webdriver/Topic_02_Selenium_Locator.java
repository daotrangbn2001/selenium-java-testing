package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//open page Register
		driver.get("https://demo.nopcommerce.com/register");
	}

	@Test
	public void TC_01_Id() {
		driver.findElement(By.id("FirstName")).sendKeys("abc");
	}

	@Test
	public void TC_02_Class() {
		//open page search
		driver.get("https://demo.nopcommerce.com/search");
		
		//tim className=serachText and nhap text vao search textbox
		driver.findElement(By.className("search-text")).sendKeys("Dell");
	}

	@Test
	public void TC_03_Name() {
		//tim name and click vao o checkBox
		driver.findElement(By.name("advs")).click();
	}

	@Test
	public void TC_04_TagName() {
		//tim ra bao nhieu the input
		System.out.println(driver.findElement(By.tagName("input")).getSize());
	}

	@Test
	public void TC_05_LinkText() {
		//click vao link Address => click tuyet doi
		driver.findElement(By.linkText("Addresses")).click();
	}

	@Test
	public void TC_06_PartialLinkText() {
		//click vao link Apply for vendor account => click tuong doi
		driver.findElement(By.partialLinkText("vendor account")).click();
	}
	
	@Test
	public void TC_07_Css() {
		//open page Register
		driver.get("https://demo.nopcommerce.com/register");
		
		driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("Trang");
	}

	@Test
	public void TC_08_Xpath() {
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Dao");
	}

	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}
