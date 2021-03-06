package WB2C.UIAutomation;

import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CCommon.TestNGListener;
import WB2CConstants.*;
import WB2CPages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

@Listeners({ TestNGListener.class })
public class SessionManagementRefresh extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private String url;

    @Parameters({"browser"})
    @Test
    public void refreshSessionManagement(String testNGBrowser) throws Exception {
        desiredCapabilities = CommonUtil.getBrowser(testNGBrowser);
        url = NodeIPConstants.windows_Node1_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        loginPage = new LoginPage(driver);
        testaccounts = new TestAccounts();
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername, TestAccounts.testuserpwd);
        CommonWebDriver.isElementDisplayed(driver,
                By.xpath(LoginConstants.span_username_xpath));
        CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.customer_service_xpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.session_management_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='sessionMgmtFrame']"));
        CommonWebDriver.clickElement(driver, By.xpath("//tr/td/a[@class='k-icon k-plus']"));
        CommonWebDriver.wait(driver, 2);
        //a class="k-link"
        boolean iconMinus = CommonWebDriver.isElementDisplayed(driver, By.xpath("//tr/td/a[@class='k-icon k-minus']"));
        Assert.assertTrue(iconMinus);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnRefresh']"));
        CommonWebDriver.wait(driver, 2);
        boolean iconPlus = CommonWebDriver.isElementDisplayed(driver, By.xpath("//tr/td/a[@class='k-icon k-plus']"));
        Assert.assertTrue(iconPlus);
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}
