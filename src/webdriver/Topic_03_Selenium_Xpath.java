package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_Xpath {
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
		
		//open page Selenium WebDriver API
		driver.get("https://automationfc.github.io/basic-form/");	
	}

	@Test
	public void TC_01_Nestedtext() {
		driver.findElement(By.xpath("//h5[text()='Michael Jackson']"));
		//Result: have 4
	}

	@Test
	public void TC_02_Contains() {
		driver.findElement(By.xpath("//h5[contains(text(),'Michael Jackson')]"));
		//Result: have 4
		
		//Lấy tất cả thẻ h5 có text="Michael Jackson"
		driver.findElement(By.xpath("//h5[contains(.,'Michael Jackson')]"));
		//Result: have 8
	}

	@Test
	public void TC_03_Concat() {
		driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What',\"'s happened?\")]"));
		//Result: Hello "John", What's happened?
	}
	
	@Test
	public void TC_04_And() {
		driver.findElement(By.xpath("//h5[text()='Michael Jackson' and @id='four']"));
		//Result: have 1
	}
	
	@Test
	public void TC_05_Or() {
		driver.findElement(By.xpath("//h5[text()='Michael Jackson' or @id='four']"));
		//Result: have 4
	}
	
	@Test
	public void TC_06_Not() {
		driver.findElement(By.xpath("//h5[not(text()='Michael Jackson')]"));
		//Result: have 15
	}
	
	@Test
	public void TC_06_Last() {
		driver.findElement(By.xpath("(//h5[text()='Michael Jackson'])[last()]"));
		//Result: have 1
	}
	
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

}
