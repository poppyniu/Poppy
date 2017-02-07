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

public class GroupManagementMultipleDelete {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void multipleDeleteGroupManagement(String testNGBrowser) throws Exception {
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
        //prepare 2 test data
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='name']"), "test group");
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='description']"), "test group description");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='name']"), "test1 group");
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='description']"), "test1 group description");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(".//*[@id='searchText']"), "test");
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[2]")).getAttribute("innerHTML").contains("test group")) {
            System.out.println("Create group succeed, scenario pass! ");
        } else
            CommonAssert.fail("Create group get error, scenario fail! ");
        //select all and delete test data
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='grid']/table/thead/tr/th[1]/a/li"));
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='btnRemove']"));
        CommonWebDriver.wait(driver, 1);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='alertify']/div/article/p")) & CommonWebDriver.getElement(driver, By.xpath(".//*[@id='alertify']/div/article/p")).getAttribute("innerHTML").contains("2")) {
            System.out.println("Click delete button see the correct warning dialog, test pass! ");
        } else
            CommonAssert.fail("Click delete button does not see the warning dialog, test fail!");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='alertify-ok']"));

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
