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
public class GroupManagementEdit extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void editGroupManagement(String testNGBrowser) throws Exception {
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
        //edit test data
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[5]/a[3]/li"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clearTextbox(driver, By.xpath(".//*[@id='name']"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='name']"), "edit test group");
        CommonWebDriver.clearTextbox(driver, By.xpath(".//*[@id='description']"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='description']"), "edit test group description");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[2]")).getAttribute("innerHTML").contains("edit") & CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[3]")).getAttribute("innerHTML").contains("edit")) {
            System.out.println("Eit group name and description succeed, test pass! ");
        } else
            CommonAssert.fail("Eit group name and description get error, test fail! ");
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
