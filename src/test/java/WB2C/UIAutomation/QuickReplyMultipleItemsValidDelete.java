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
public class QuickReplyMultipleItemsValidDelete extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private String url;

    @Parameters({"browser"})
    @Test
    public void validDeleteQuickReplyMultipleItems(String testNGBrowser) throws Exception {

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
        //CommonWebDriver.wait(driver, 2);
        int i = 1;
        String input = "test quick reply";
        do {
            CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
            CommonWebDriver.wait(driver, 1);
            String inputValue = String.format("%s%s", input, i);
            CommonWebDriver.sendKeysToElement(driver, By.xpath("//textarea[@id='content']"), inputValue);
            CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
            CommonWebDriver.wait(driver, 2);
            i++;
        } while (i < 3);

        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='searchText']"), input,
                "quick reply Search text box.");
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("//a[@id='btnSearch']/li"));
        CommonWebDriver.wait(driver, 2);
        //delete test data
        CommonWebDriver.clickElement(driver, By.xpath("//a[@class='check-all']/li"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnRemove']"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElement(driver, By.xpath("//button[@id='alertify-ok']"));
        CommonWebDriver.wait(driver, 1);
        String searchResult = "//span[@class='k-state-selected']";
        String actualNumber = CommonWebDriver.getElement(driver, By.xpath(searchResult)).getText();
        String expectNumber = "0";
        Assert.assertEquals(actualNumber, expectNumber);
    }


    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}
