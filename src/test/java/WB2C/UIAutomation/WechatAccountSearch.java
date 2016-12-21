package WB2C.UIAutomation;

import java.net.URL;
import WB2CCommon.CommonUtil;
import WB2CConstants.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import WB2CCommon.CommonWebDriver;
import WB2CPages.LoginPage;

public class WechatAccountSearch {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void AccountSearch(String testNGBrowser) throws Exception {
        desiredCapabilities = CommonUtil.getBrowser(testNGBrowser);
        String url = NodeIPConstants.windows_Node1_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        loginPage = new LoginPage(driver);
        testaccounts = new TestAccounts();
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername, TestAccounts.testuserpwd);
        CommonWebDriver.navigateAndLoadPage(driver,URLConstants.homePageUrl,3);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.wechat_management_xpath));
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.wechat_acount_xpath));
        CommonWebDriver.switchToFrame(driver,By.xpath("//iframe[@id='wxAccountMgmtFrame']"));
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//input[@id='searchText']"), "Burberr",
                "WeChat Account Search text box.");
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("//a[@id='btnSearch']/li"));
        if(CommonWebDriver.isElementDisplayed(driver,By.xpath("//table[@class='k-selectable']/tbody/tr/td[2]")))
        {
            System.out.println("The wechat account search function works well, test pass!");
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
