package WB2C.UIAutomation;

import WB2CCommon.CommonAssert;
import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CCommon.TestNGListener;
import WB2CConstants.NodeIPConstants;
import WB2CConstants.SideMenuConstants;
import WB2CConstants.TestAccounts;
import WB2CConstants.URLConstants;
import WB2CPages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

@Listeners({ TestNGListener.class })
public class AutoResponseRefresh extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void refreshAutoResponse(String testNGBrowser) throws Exception {
        desiredCapabilities = CommonUtil.getBrowser(testNGBrowser);
        String url = NodeIPConstants.windows_Node1_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        loginPage = new LoginPage(driver);
        testaccounts = new TestAccounts();
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername, TestAccounts.testuserpwd);
        CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.wechat_management_xpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.we_chat_auto_response));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='automessageMgmtFrame']"));
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(".//*[@id='searchText']"), "hello");
        String searchTextValue = CommonWebDriver.getElement(driver, By.xpath(".//*[@id='searchText']")).getAttribute("value");
        if (searchTextValue.contains("hello")) {
            System.out.println("The search text box is not empty, scenario pass! ");
        } else
            CommonAssert.fail("The search text box is empty, scenario fail! ");
        //Click refresh button
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='btnRefresh']"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='searchText']")).getAttribute("value").equals("")) {
            System.out.println("The refresh function works well, test pass! ");
        } else
            CommonAssert.fail("The refresh function does not works well, test fail! ");

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
