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

public class GroupManagementAuthority {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void authorityGroupManagement(String testNGBrowser) throws Exception {
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
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.system_setup_group_management));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='userGroupMgmtFrame']"));
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='name']"), "Automation test");
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='description']"), "test group description");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(".//*[@id='searchText']"), "Automation test");
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[2]")).getAttribute("innerHTML").contains("Automation test")) {
            System.out.println("Create group succeed, test pass! ");
        } else
            CommonAssert.fail("Create group get error, test fail! ");
        //authority the new group
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[5]/a[1]/li"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='usersWindow']/form/ul/li[5]/div/input[1]"));
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='usersWindow']/form/ul/li[5]/div/input[2]"));
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='usersWindow']/form/ul/li[5]/div/input[3]"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='btnPermissions']"));
        CommonWebDriver.wait(driver, 3);

        //click user management edit user authority
        CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.system_setup_xpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.system_setup_user_management));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='userMgmtFrame']"));
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(".//*[@id='searchText']"), "poppytest");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[7]/a[2]/li"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='userForm']/ul/li[8]/span/span/span/span"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='userGroupId_listbox']/li[3]"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        //logout from current user
        CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("//i[@class='icon-angle-down']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//*[@class='dropdown-menu']/*/a[@href='logout.html']"));
        CommonWebDriver.wait(driver, 5);
        Boolean loginPageTitleExist = CommonWebDriver.isElementDisplayed(driver,
                By.xpath("//*[@class='form-title']"));
        if (loginPageTitleExist) {
            System.out.println("Log out succeed,test pass!");
        }
        //login with poppytest
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername3, TestAccounts.testuserpwd);
        CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
        CommonWebDriver.wait(driver, 5);
        //judge if menu exists
        if (!CommonWebDriver.isMemberManagementMenuExist(driver, "icon-group")) {
            System.out.println("The user can't see wechat management menu, test pass! ");
        } else
            CommonAssert.fail("The user can see wechat management menu, test fail! ");

        //delete test data
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("html/body/div[2]/div[1]/ul/li[4]/a/span[1]"));
        CommonWebDriver.wait(driver, 2);
        //remove user from new created group
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath(SideMenuConstants.system_setup_user_management));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='userMgmtFrame']"));
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(".//*[@id='searchText']"), "poppytest");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='grid']/table/tbody/tr/td[7]/a[2]/li"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='userForm']/ul/li[8]/span/span/span/span"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='userGroupId_listbox']/li[1]"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);

        //delete new group
        CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("html/body/div[2]/div[1]/ul/li[4]/a/span[1]"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.system_setup_group_management));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='userGroupMgmtFrame']"));
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(".//*[@id='searchText']"), "Automation test");
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[2]")).getAttribute("innerHTML").contains("Automation test")) {
            System.out.println("Create group succeed, test pass! ");
        } else
            CommonAssert.fail("Create group get error, test fail! ");

        //delete test data
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[5]/a[2]/li"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='alertify-ok']"));

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
