package TestNG1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class Case1 {
	WebDriver driver;

	@Test
	public void TestNGPoppyCase() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://www.baidu.com/");

		driver.manage().window().maximize();

		WebElement map = driver.findElement(By.name("tj_trmap"));
		map.click();
		WebElement mapSearchbox=driver.findElement(By.id("sole-input"));		
		Boolean mapExist=mapSearchbox.isDisplayed();
		if (mapExist==true)
		{
			Assert.assertTrue(true);
		}
	
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
