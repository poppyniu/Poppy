package WB2C.UIAutomation;


import WB2CCommon.CommonAssert;
import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

public class UserManagementInvalidDelete {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void InvalidDeleteUserManagement(String testNGBrowser) throws Exception {
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
                By.xpath(SideMenuConstants.system_setup_xpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.system_setup_user_management));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='userMgmtFrame']"));
        //click delete button directly
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='btnRemove']"));
        CommonWebDriver.wait(driver, 1);
        if(CommonWebDriver.isElementDisplayed(driver,By.xpath(".//*[@id='alertify-logs']/article")))
        {
            System.out.println("Select nothing and delete see the correct warning message, test pass! ");
        } else
            CommonAssert.fail("Select nothing and delete does not see the correct warning message, test fail! ");
        //create test data
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='username']"), "testuser");
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='nickname']"), "testuser");
        CommonWebDriver.sendKeysToElement(driver,By.xpath(".//form[@id='userForm']/ul/li/input[@id='password']"),"pass");
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//form[@id='userForm']/ul/li/input[@id='repassword']"), "pass");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='userForm']/ul/li[7]/span/span[1]/span/span"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='userType_listbox']/li[1]"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='userForm']/ul/li[8]/span/span[1]/span/span"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='userGroupId_listbox']/li[1]"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(".//*[@id='searchText']"), "testuser");
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[2]")).getAttribute("innerHTML").contains("testuser")) {
            System.out.println("Search and create user succeed, test pass! ");
        } else
            CommonAssert.fail("Search and create user get error, test fail! ");
        //cancel the delete function
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[7]/a[3]/li"));
        CommonWebDriver.wait(driver,2);
        if(CommonWebDriver.isElementDisplayed(driver,By.xpath(".//*[@id='alertify']/div/article/p")))
        {
            System.out.println("Click delete button see the correct dialog, test pass! ");
        } else
            CommonAssert.fail("Click delete button does not see the correct dialog, test fail! ");
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='alertify-cancel']"));
        CommonWebDriver.wait(driver,2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[2]")).getAttribute("innerHTML").contains("testuser")) {
            System.out.println("Cancel delete succeed, test pass! ");
        } else
            CommonAssert.fail("Cancel delete get error, test fail! ");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
