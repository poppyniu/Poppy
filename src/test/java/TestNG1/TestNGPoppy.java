package TestNG1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNGPoppy {
	WebDriver driver;

	@Test
	public void TestNGCase() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://www.baidu.com/");

		driver.manage().window().maximize();

		WebElement txtbox = driver.findElement(By.name("wd"));
		txtbox.sendKeys("Gle");

		WebElement btn = driver.findElement(By.id("su"));
		btn.click();

		String expectedTitle = "Gle_百度搜索";
		String actualTitle = driver.getTitle();

		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
