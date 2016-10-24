package TestNG1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class Case2 {
	WebDriver driver;

	@Test
	public void PoppyCase2() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.get("http://www.sip-gjn.com/default.aspx");
		driver.manage().window().maximize();
		WebElement loginBtn = driver.findElement(By.className("login"));
		loginBtn.click();
		Thread.sleep(5000);
		driver.navigate().to("http://www.sip-gjn.com/Login.aspx?type=1");
		Thread.sleep(3000);
		WebElement userNameTB = driver.findElement(By.id("Body_txtAccount"));
		userNameTB.sendKeys("zhangman");
		WebElement userPWDTB = driver.findElement(By.id("Body_txtPassword"));
		userPWDTB.sendKeys("665258rain");
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).perform();
		action.sendKeys(Keys.DOWN).perform();
		action.sendKeys(Keys.DOWN).perform();
		Thread.sleep(3000);
		WebElement loginBTN = driver.findElement(By.id("Body_btnLogin"));
		loginBTN.click();
		Thread.sleep(3000);
		WebElement userinfo = driver.findElement(By
				.id("PageTop1_lnkUpdateInfo"));
		if (userinfo.isDisplayed()==true) {
			Assert.assertTrue(true);
			System.out.print("zhangman login succeed! ");
		}

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
