package WB2C.UIAutomation;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import WB2CConstants.LoginConstants;
import WB2CConstants.SideMenuConstants;
import WB2CConstants.TestAccounts;
import WB2CConstants.URLConstants;
import WB2CConstants.NodeIPConstants;
import WB2CCommon.CommonWebDriver;
import WB2CPages.LoginPage;

public class DashboardCheck {
	WebDriver driver;
	LoginPage loginPage;
	TestAccounts testaccounts;
	DesiredCapabilities desiredCapabilities;

	@DataProvider(name = "data")
	public Object[][] data() {
		return new Object[][] { { NodeIPConstants.linux_Node1_ip, "htmlunit" } };
	}

	@Test(dataProvider = "data")
	public void DashboardCheckItems(String nodeURL, String browser)
			throws Exception {
		driver = new FirefoxDriver();
		driver = new HtmlUnitDriver();
		if (browser == "htmlunit") {
			desiredCapabilities = DesiredCapabilities.htmlUnit();
		} else if (browser == "firefox") {
			desiredCapabilities = DesiredCapabilities.firefox();
		}
		String url = nodeURL + "/wd/hub";
		driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
		loginPage = new LoginPage(driver);
		testaccounts = new TestAccounts();
		CommonWebDriver.get(driver, URLConstants.loginPageUrl.toString());
		loginPage.loginWithValidCredential(testaccounts.testbrandcode,
				testaccounts.testusername, testaccounts.testuserpwd);
		CommonWebDriver.clickElementWhenPresent(driver,
				By.xpath(SideMenuConstants.dashboard_xpath));
		CommonWebDriver.isElementDisplayed(driver,
				By.xpath("//h3[@class='page-title']"));
		Boolean pageTitleExist = CommonWebDriver.isElementDisplayed(driver,
				By.xpath("//h3[@class='page-title']"));
		if (pageTitleExist == true) {
			System.out.println("The dashboaed items are correctly displayed! ");
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
