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
public class GroupManagementInvalidDelete extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void invalidDeleteGroupManagement(String testNGBrowser) throws Exception {
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
        //select nothing and delete
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='btnRemove']"));
        CommonWebDriver.wait(driver, 1);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='alertify-logs']/article"))) {
            System.out.println("Select nothing to delete see the correct warning message, test pass!");
        } else
            CommonAssert.fail("Select nothing to delete does not see the correct warning message, test fail! ");
        //prepare test data
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='name']"), "test group");
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='description']"), "test group description");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(".//*[@id='searchText']"), "test group");
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[2]")).getAttribute("innerHTML").contains("test group")) {
            System.out.println("Create group succeed, scenario pass! ");
        } else
            CommonAssert.fail("Create group get error, scenario fail! ");
        //cancel delete test data
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[5]/a[2]/li"));
        CommonWebDriver.wait(driver, 1);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='alertify']/div/article/p"))) {
            System.out.println("Click delete button see the correct warning dialog, test pass! ");
        } else
            CommonAssert.fail("Click delete button does not see the warning dialog, test fail!");
        //click cancel button
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='alertify-cancel']"));
        CommonWebDriver.wait(driver, 1);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[2]")).getAttribute("innerHTML").contains("test group")) {
            System.out.println("Cancel delete succeed, test pass! ");
        } else
            CommonAssert.fail("Cancel delete get error, test fail! ");
        //delete test data
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[5]/a[2]/li"));
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='alertify-ok']"));


    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
