package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_Xpath_Part_III {
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
	}

	@Test
	public void TC_01_Empty_Data() {
		//Mở form đăng kí
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Click nút đăng kí
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify
		List<WebElement> str_Name =  driver.findElements(By.id("txtFirstname-error"));
		String name = str_Name.get((int)(str_Name.size() - 1)).getText(); 
		Assert.assertEquals(name, "Vui lòng nhập họ tên");
		
		List<WebElement> str_Email =  driver.findElements(By.id("txtEmail-error"));
		String email = str_Email.get((int)(str_Email.size() - 1)).getText(); 
		Assert.assertEquals(email, "Vui lòng nhập email");
		
		List<WebElement> str_ReEmail =  driver.findElements(By.id("txtCEmail-error"));
		String reEmail = str_ReEmail.get((int)(str_ReEmail.size() - 1)).getText(); 
		Assert.assertEquals(reEmail, "Vui lòng nhập lại địa chỉ email");
		
		List<WebElement> str_Password =  driver.findElements(By.id("txtPassword-error"));
		String pass = str_Password.get((int)(str_Password.size() - 1)).getText(); 
		Assert.assertEquals(pass, "Vui lòng nhập mật khẩu");
		
		List<WebElement> str_RePassword =  driver.findElements(By.id("txtCPassword-error"));
		String rePass = str_RePassword.get((int)(str_RePassword.size() - 1)).getText(); 
		Assert.assertEquals(rePass, "Vui lòng nhập lại mật khẩu");
		
		List<WebElement> str_Phone =  driver.findElements(By.id("txtPhone-error"));
		String phone = str_Phone.get((int)(str_Phone.size() - 1)).getText(); 
		Assert.assertEquals(phone, "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		//Mở form đăng kí
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Dao Trang");
		driver.findElement(By.id("txtEmail")).sendKeys("223@22@2");
		driver.findElement(By.id("txtCEmail")).sendKeys("223@22@2");
		driver.findElement(By.id("txtPassword")).sendKeys("12345678");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("01234578921");
		
		
		//Click nút đăng kí
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
	}
	

	@Test
	public void TC_03_Incorect_Email() {
		//Mở form đăng kí
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Dao Trang");
		driver.findElement(By.id("txtEmail")).sendKeys("admin@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("admin@gmail.net");
		driver.findElement(By.id("txtPassword")).sendKeys("12345678");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("01234578921");
		
		
		//Click nút đăng kí
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Vertify
		List<WebElement> str_ReEmail =  driver.findElements(By.id("txtCEmail-error"));
		String reEmail = str_ReEmail.get((int)(str_ReEmail.size() - 1)).getText(); 
		Assert.assertEquals(reEmail, "Email nhập lại không đúng");
	}
	
	@Test
	public void TC_04_Invalid_Password() {
		//Mở form đăng kí
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Dao Trang");
		driver.findElement(By.id("txtEmail")).sendKeys("admin@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("admin@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("01234578921");
		
		
		//Click nút đăng kí
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Vertify
		List<WebElement> str_Password =  driver.findElements(By.id("txtPassword-error"));
		String pass = str_Password.get((int)(str_Password.size() - 1)).getText(); 
		Assert.assertEquals(pass, "Mật khẩu phải có ít nhất 6 ký tự");
		
		List<WebElement> str_RePassword =  driver.findElements(By.id("txtCPassword-error"));
		String rePass = str_RePassword.get((int)(str_RePassword.size() - 1)).getText(); 
		Assert.assertEquals(rePass, "Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test
	public void TC_05_Incorrect_Confirm_Password() {
		//Mở form đăng kí
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Dao Trang");
		driver.findElement(By.id("txtEmail")).sendKeys("admin@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("admin@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345678");
		driver.findElement(By.id("txtCPassword")).sendKeys("1237890");
		driver.findElement(By.id("txtPhone")).sendKeys("01234578921");
		
		
		//Click nút đăng kí
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Vertify
		List<WebElement> str_RePassword =  driver.findElements(By.id("txtCPassword-error"));
		String rePass = str_RePassword.get((int)(str_RePassword.size() - 1)).getText(); 
		Assert.assertEquals(rePass, "Mật khẩu bạn nhập không khớp");
	}

	@Test
	public void TC_06_Invalid_Phonenumber() {
		//Mở form đăng kí
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Dao Trang");
		driver.findElement(By.id("txtEmail")).sendKeys("admin@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("admin@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345678");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("01234");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Vertify1
		List<WebElement> str_Phone =  driver.findElements(By.id("txtPhone-error"));
		String phone = str_Phone.get((int)(str_Phone.size() - 1)).getText(); 
		Assert.assertEquals(phone, "Số điện thoại phải từ 10-11 số.");
		
		//Action2
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("012345335324");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Vertify2
		List<WebElement> str_Phone2 =  driver.findElements(By.id("txtPhone-error"));
		String phone2 = str_Phone2.get((int)(str_Phone2.size() - 1)).getText(); 
		Assert.assertEquals(phone2, "Số điện thoại phải từ 10-11 số.");
		
		//Action3
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("1234533532");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Vertify3
		List<WebElement> str_Phone3 =  driver.findElements(By.id("txtPhone-error"));
		String phone3 = str_Phone3.get((int)(str_Phone3.size() - 1)).getText(); 
		Assert.assertEquals(phone3, "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
