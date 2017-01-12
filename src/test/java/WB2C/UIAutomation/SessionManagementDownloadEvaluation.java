package WB2C.UIAutomation;

import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CConstants.*;
import WB2CPages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

public class SessionManagementDownloadEvaluation {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private String url;

    @Parameters({"browser"})
    @Test
    public void SessionManagementUpfoldMessageRecords(String testNGBrowser) throws Exception {
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
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnDownload']"));
        CommonWebDriver.wait(driver, 1);
        //create robot object
        Robot robot = new Robot();
        Thread.sleep(1000L);
        //Click Down Arrow Key to select "Save File" Radio Button
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(1000L);
        // Click 3 times Tab to take focus on "OK" Button
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(1000L);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(1000L);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(1000L);
        //Click "Enter" Button to download file
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(5000L);
    }
    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}