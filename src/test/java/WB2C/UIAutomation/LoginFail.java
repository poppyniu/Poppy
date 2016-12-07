package WB2C.UIAutomation;

import java.io.File;
import java.net.URL;

import WB2CCommon.CommonUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import WB2CCommon.CommonWebDriver;
import WB2CConstants.LoginConstants;
import WB2CConstants.NodeIPConstants;
import WB2CConstants.TestAccounts;
import WB2CConstants.URLConstants;
import WB2CPages.LoginPage;

public class LoginFail {
	private WebDriver driver;
	private TestAccounts testaccounts;
	private DesiredCapabilities desiredCapabilities;

	@Parameters({"browser"})
	@Test
	public void TestLoginFail(String testNGBrowser) throws Exception {
		testaccounts = new TestAccounts();
		desiredCapabilities= CommonUtil.getBrowser(testNGBrowser);
		String url = NodeIPConstants.windows_Node1_ip + "/wd/hub";
		driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
		CommonWebDriver.get(driver, URLConstants.loginPageUrl);
		driver.manage().window().maximize();
		//clear brandcode textbox
		CommonWebDriver.clearTextbox(driver, By.xpath(LoginConstants.brandCode_input_xpath));
		//send keys to brandcode textbox
		CommonWebDriver.sendKeysToElement(driver,By.xpath(LoginConstants.brandCode_input_xpath), TestAccounts.testbrandcode,
				LoginConstants.brandCode_input_desc);
		CommonWebDriver.wait(driver, 1);	
		//clear username textbox
		CommonWebDriver.clearTextbox(driver, By.xpath(LoginConstants.username_input_xpath));
		//send keys to username textbox
		CommonWebDriver.sendKeysToElement(driver,By.xpath(LoginConstants.username_input_xpath), TestAccounts.testusername,
				LoginConstants.username_input_desc);
		CommonWebDriver.wait(driver, 1);
		//clear password text box if needed
		CommonWebDriver.clearTextbox(driver, By.xpath(LoginConstants.password_input_xpath));
		//send keys to pwd textbox
		CommonWebDriver.sendKeysToElement(driver,By.xpath(LoginConstants.password_input_xpath), TestAccounts.wrongpwd,
						LoginConstants.password_input_desc);
		CommonWebDriver.wait(driver, 1);
		CommonWebDriver.clickElementWhenPresent(driver,By.xpath(LoginConstants.login_button_xpath),
				LoginConstants.login_button_desc);
		CommonWebDriver.wait(driver, 4);
		Boolean userInfoExist = CommonWebDriver.isElementDisplayed(driver,
				By.id("msgLabel"));
		if (userInfoExist) {
			System.out.println("login failed, test pass ! ");
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
