package WB2C.UIAutomation;

import java.net.URL;
import WB2CCommon.CommonUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import WB2CConstants.SideMenuConstants;
import WB2CConstants.TestAccounts;
import WB2CConstants.URLConstants;
import WB2CConstants.NodeIPConstants;
import WB2CCommon.CommonWebDriver;
import WB2CPages.LoginPage;

public class DashboardCheck {
	private WebDriver driver;
	private LoginPage loginPage;
	private TestAccounts testaccounts;
	private DesiredCapabilities desiredCapabilities;

	@Parameters({"browser"})
	@Test
	public void DashboardCheckItems(String testNGBrowser) throws Exception {
		desiredCapabilities=CommonUtil.getBrowser(testNGBrowser);
		String url = NodeIPConstants.windows_Node1_ip + "/wd/hub";
		driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
		loginPage = new LoginPage(driver);
		testaccounts = new TestAccounts();
		CommonWebDriver.get(driver, URLConstants.loginPageUrl);
		loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
				TestAccounts.testusername, TestAccounts.testuserpwd);
		driver.manage().window().maximize();
		CommonWebDriver.clickElementWhenPresent(driver,
				By.xpath(SideMenuConstants.dashboard_xpath));
		Boolean pageTitleExist = CommonWebDriver.isElementDisplayed(driver,
				By.xpath("//h3[@class='page-title']"));
		Boolean blueBoardExist = CommonWebDriver.isElementDisplayed(driver,
				By.xpath("//div[@class='dashboard-stat blue']"));
		Boolean greenBoardExist = CommonWebDriver.isElementDisplayed(driver,
				By.xpath("//div[@class='dashboard-stat green']"));
		Boolean purpleBoardExist = CommonWebDriver.isElementDisplayed(driver,
				By.xpath("//div[@class='dashboard-stat purple']"));
		Boolean yellowBoardExist = CommonWebDriver.isElementDisplayed(driver,
				By.xpath("//div[@class='dashboard-stat yellow']"));
		Boolean subscribeChartExist = CommonWebDriver.isElementDisplayed(driver,
				By.xpath("//div[@id='subscribe']"));
		Boolean messageChartExist = CommonWebDriver.isElementDisplayed(driver,
				By.xpath("//div[@id='message']"));
		Boolean locationChartExist = CommonWebDriver.isElementDisplayed(driver,
				By.xpath("//div[@id='subscribeLocation']"));
		Boolean messageLocationChartExist = CommonWebDriver.isElementDisplayed(driver,
				By.xpath("//div[@id='messageLocation']"));
		if (pageTitleExist&blueBoardExist&greenBoardExist&purpleBoardExist&yellowBoardExist&subscribeChartExist&messageChartExist&locationChartExist&messageLocationChartExist) {
			System.out.println("The dashboaed items are correctly displayed,test pass!");
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
