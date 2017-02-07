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

public class SessionManagementSearch {


    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private String url;

    @Parameters({"browser"})
    @Test
    public void searchSessionManagement(String testNGBrowser) throws Exception {
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
                By.xpath(SideMenuConstants.customer_service_xpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.session_management_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='sessionMgmtFrame']"));
        //get value from the first line
        int i = 4;
        do {
            String ii = Integer.toString(i);
            String inputSearch = driver.findElement(By.xpath("//tr/td[" + ii + "]")).getText().trim();
            CommonWebDriver.clearTextbox(driver, By.xpath("//input[@id='searchText']"));
            CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='searchText']"), inputSearch,
                    "quick reply Search text box.");
            CommonWebDriver.clickElementWhenPresent(driver,
                    By.xpath("//a[@id='btnSearch']/li"));
            CommonWebDriver.wait(driver, 2);
            String actualValue = driver.findElement(By.xpath("//tr/td[" + ii + "]")).getText().trim();
            Assert.assertEquals(actualValue, inputSearch);
            CommonWebDriver.clearTextbox(driver, By.xpath("//input[@id='searchText']"));
            CommonWebDriver.clickElementWhenPresent(driver,
                    By.xpath("//a[@id='btnSearch']/li"));
            CommonWebDriver.wait(driver, 2);
            i++;
        } while (i < 6);

    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}


