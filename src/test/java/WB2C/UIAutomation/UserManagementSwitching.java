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

public class UserManagementSwitching {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void switchUserManagement(String testNGBrowser) throws Exception {
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
        //click page2 button
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='grid']/div/ul/li[2]/a"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/div/span")).getAttribute("innerHTML").contains("显示11-")) {
            System.out.println("Switch to next page works well, scenario pass! ");
        } else
            CommonAssert.fail("Switch to next page get error, scenario fail! ");
        //click page1 page button
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='grid']/div/ul/li[1]/a"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/div/span")).getAttribute("innerHTML").contains("显示1-10")) {
            System.out.println("Switch to previous page works well, scenario pass! ");
        } else
            CommonAssert.fail("Switch to previous page get error, scenario fail! ");
        //click last page button
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='grid']/div/a[4]/span"));
        CommonWebDriver.wait(driver, 2);
        //click first page button
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='grid']/div/a[1]/span"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/div/span")).getAttribute("innerHTML").contains("显示1-10")) {
            System.out.println("Switch to first page works well, test pass! ");
        } else
            CommonAssert.fail("Switch to first page get error, test fail! ");
        CommonWebDriver.wait(driver, 2);
        //click next page button
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='grid']/div/a[3]/span"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/div/span")).getAttribute("innerHTML").contains("显示11-")) {
            System.out.println("Switch to next page works well, test pass! ");
        } else
            CommonAssert.fail("Switch to next page get error, test fail! ");
        CommonWebDriver.wait(driver, 2);
        //click last page button
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='grid']/div/a[2]/span"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/div/span")).getAttribute("innerHTML").contains("显示1-10")) {
            System.out.println("Switch to last page works well, test pass! ");
        } else
            CommonAssert.fail("Switch to last page get error, test fail! ");

        //delete test data
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[7]/a[3]/li"));
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='alertify-ok']"));

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
