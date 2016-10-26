package TestNG1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class Case3 {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		long id = Thread.currentThread().getId();
		System.out.println("Before test-class. Thread id is: " + id);
	}

	@Test
	public void PoppyCase3() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://www.sip-gjn.com/");
		driver.manage().window().maximize();
		WebElement loginBtn = driver.findElement(By.className("login"));
		loginBtn.click();
		Thread.sleep(3000);
		driver.navigate().to("http://www.sip-gjn.com/Login.aspx?type=1");
		WebElement name = driver.findElement(By.id("Body_txtAccount"));
		name.sendKeys("zhangman");
		WebElement pwd = driver.findElement(By.id("Body_txtPassword"));
		pwd.sendKeys("665258rain");
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).perform();
		action.sendKeys(Keys.DOWN).perform();
		action.sendKeys(Keys.DOWN).perform();
		WebElement login2Btn = driver.findElement(By.id("Body_btnLogin"));
		login2Btn.click();
		WebElement info = driver.findElement(By.id("PageTop1_lnkUpdateInfo"));
		if (info.isDisplayed() == true) {
			Assert.assertTrue(true);
			System.out.println("login succeed ! ");
		}

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
