package WB2C.UIAutomation;

import java.net.URL;
import java.io.File;

import WB2CCommon.CommonUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import WB2CConstants.LoginConstants;
import WB2CConstants.TestAccounts;
import WB2CConstants.URLConstants;
import WB2CConstants.NodeIPConstants;
import WB2CCommon.CommonWebDriver;
import WB2CPages.LoginPage;

public class LoginSucceed {
	WebDriver driver;
	LoginPage loginPage;
	TestAccounts testaccounts;
	DesiredCapabilities desiredCapabilities;
    String url;

	@Parameters({"browser"})
	@Test
	public void TestLoginPass(String testNGBrowser) throws Exception {
		desiredCapabilities= CommonUtil.getBrowser(testNGBrowser);
        url = NodeIPConstants.windows_local_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
		loginPage = new LoginPage(driver);
		testaccounts = new TestAccounts();
		CommonWebDriver.get(driver, URLConstants.loginPageUrl.toString());
		driver.manage().window().maximize();
		loginPage.loginWithValidCredential(testaccounts.testbrandcode,
				testaccounts.testusername, testaccounts.testuserpwd);
		CommonWebDriver.isElementDisplayed(driver,
				By.xpath(LoginConstants.span_username_xpath));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
