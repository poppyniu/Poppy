package WB2C.UIAutomation;

import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CConstants.*;
import WB2CPages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

public class MembershipCategoryMultipleItemsDelete {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private String url;

    @Parameters({"browser"})
    @Test
    public void deleteMembershipCategoryMultipleItems(String testNGBrowser) throws Exception {
        desiredCapabilities = CommonUtil.getBrowser(testNGBrowser);
        url = NodeIPConstants.windows_Node1_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        loginPage = new LoginPage(driver);
        testaccounts = new TestAccounts();
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername1, TestAccounts.testuserpwd);
        CommonWebDriver.isElementDisplayed(driver,
                By.xpath(LoginConstants.span_username_xpath));
        CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.membership_management_xpath));
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.member_category_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='wxMbrCategoryMgmtFrame']"));
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        //create the first membership category
        String newCat1 = "test member category1";
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='name']"), newCat1);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='description']"), "test description");
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        //create the second membership category
        String newCat2 = newCat1+"2";
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        String expectValue1 = "test member category1";
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='name']"), newCat2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='description']"), "test description");
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        //search multiple membership categories
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='searchText']"),newCat1 ,
                "Membership Category Search text box.");
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("//a[@id='btnSearch']/li"));
        //delete test data
        String chkAllXpath="//a[@class='check-all']/li";
        CommonWebDriver.clickElement(driver,By.xpath(chkAllXpath));
        CommonWebDriver.wait(driver,2);
        CommonWebDriver.clickElement(driver,By.xpath("//*[@id='btnRemove']"));
        CommonWebDriver.wait(driver,2);
        CommonWebDriver.clickElement(driver,By.xpath("//button[@id='alertify-ok']"));
        CommonWebDriver.wait(driver,2);
        String searchResult="//span[@class='k-state-selected']";
        String actualNumber=CommonWebDriver.getElement(driver,By.xpath(searchResult)).getText();
        String expectNumber="0";
        Assert.assertEquals(actualNumber,expectNumber);

    }
    @AfterTest
    public void tearDown() throws Exception { driver.quit(); }
}
