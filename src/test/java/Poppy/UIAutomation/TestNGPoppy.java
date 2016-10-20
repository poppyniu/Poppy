package Poppy.UIAutomation;

import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;


public class TestNGPoppy {
	WebDriver driver;

	@Test
	public void TestNGCase() throws Exception {
		// 如果火狐浏览器没有默认安装在C盘，需要制定其路径
		// System.setProperty("webdriver.firefox.bin",
		// "D:/Program Files/Mozilla firefox/firefox.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.baidu.com/");

		driver.manage().window().maximize();

		WebElement txtbox = driver.findElement(By.name("wd"));
		txtbox.sendKeys("Glen");

		WebElement btn = driver.findElement(By.id("su"));
		btn.click();

		String expectedTitle = "Glen_百度搜索";
		String actualTitle = driver.getTitle();

		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
