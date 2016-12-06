package WB2C.UIAutomation;

import java.io.File;
import java.net.URL;

import WB2CCommon.CommonUtil;
import WB2CConstants.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import WB2CCommon.CommonWebDriver;
import WB2CPages.LoginPage;

public class LogOut {
    WebDriver driver;
    LoginPage loginPage;
    TestAccounts testaccounts;
    DesiredCapabilities desiredCapabilities;


    @Parameters({"browser"})
    @Test
    public void LogOut(String testNGBrowser)
            throws Exception {
        desiredCapabilities= CommonUtil.getBrowser(testNGBrowser);
        String url = NodeIPConstants.windows_local_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        loginPage = new LoginPage(driver);
        testaccounts = new TestAccounts();
        CommonWebDriver.get(driver, URLConstants.loginPageUrl.toString());
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(testaccounts.testbrandcode,
                testaccounts.testusername, testaccounts.testuserpwd);
        CommonWebDriver.wait(driver,2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("//i[@class='icon-angle-down']"));
        CommonWebDriver.wait(driver,2);
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath("//*[@class='dropdown-menu']/*/a[@href='logout.html']"));
        CommonWebDriver.wait(driver,10);
        Boolean loginPageTitleExist=CommonWebDriver.isElementDisplayed(driver,
                By.xpath("//*[@class='form-title']"));
        if(loginPageTitleExist==true)
        {
            System.out.println("Log out succeed,test pass!");
        }
    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
