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
public class QuickReplySearch extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private String url;

    @Parameters({"browser"})
    @Test
    public void searchQuickReply(String testNGBrowser) throws Exception {
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
                By.xpath(SideMenuConstants.quick_reply_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='quickMessageMgmtFrame']"));
        //get quick reply value from the first line
        String inputSearch = driver.findElement(By.xpath("//tr/td[2]")).getText().trim();
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='searchText']"), inputSearch,
                "quick reply Search text box.");
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("//a[@id='btnSearch']/li"));
        CommonWebDriver.wait(driver, 2);
        String actualValue = driver.findElement(By.xpath("//tr/td[2]")).getText().trim();
        Assert.assertEquals(actualValue, inputSearch);
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}
