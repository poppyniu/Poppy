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
public class UserManagementCreate extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void createUserManagement(String testNGBrowser) throws Exception {
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
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='username']"), "youyou");
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='nickname']"), "youyou");
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//form[@id='userForm']/ul/li/input[@id='password']"), "pass");
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//form[@id='userForm']/ul/li/input[@id='repassword']"), "pass");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='userForm']/ul/li[7]/span/span[1]/span/span"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='userType_listbox']/li[1]"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='userForm']/ul/li[8]/span/span[1]/span/span"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='userGroupId_listbox']/li[1]"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(".//*[@id='searchText']"), "youyou");
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[2]")).getAttribute("innerHTML").contains("youyou")) {
            System.out.println("Create user succeed, test pass! ");
        } else
            CommonAssert.fail("Create user get error, test fail! ");

        //logout from current user
        CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("html/body/div[1]/div/div/ul/li[2]/a/i"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//*[@class='dropdown-menu']/*/a[@href='logout.html']"));
        CommonWebDriver.wait(driver, 5);
        Boolean loginPageTitleExist = CommonWebDriver.isElementDisplayed(driver,
                By.xpath("//*[@class='form-title']"));
        if (loginPageTitleExist) {
            System.out.println("Log out succeed,test pass!");
        }
        //login with testuser
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername2, TestAccounts.testuserpwd);
        CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath("html/body/div[1]/div/div/ul/li[2]/a/span[2]")).getAttribute("innerHTML").contains("youyou")) {
            System.out.println("The new user can login succeed, test pass! ");
        } else
            CommonAssert.fail("The new user can not login, test fail! ");
        //delete test data
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.system_setup_xpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.system_setup_user_management));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='userMgmtFrame']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(".//*[@id='searchText']"), "youyou");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[7]/a[3]/li"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='alertify-ok']"));

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
