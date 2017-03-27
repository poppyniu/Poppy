package WB2C.UIAutomation;

import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CConstants.NodeIPConstants;
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

public class LogOut {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;


    @Parameters({"browser"})
    @Test
    public void testLogOut(String testNGBrowser)
            throws Exception {
        desiredCapabilities = CommonUtil.getBrowser(testNGBrowser);
        String url = NodeIPConstants.windows_Node1_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        loginPage = new LoginPage(driver);
        testaccounts = new TestAccounts();
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername, TestAccounts.testuserpwd);
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("html/body/div[1]/div/div/ul/li[2]/a/i"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//*[@class='dropdown-menu']/*/a[@href='logout.html']"));
        CommonWebDriver.wait(driver, 10);
        Boolean loginPageTitleExist = CommonWebDriver.isElementDisplayed(driver,
                By.xpath("//*[@class='form-title']"));
        if (loginPageTitleExist) {
            System.out.println("Log out succeed,test pass!");
        }
    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
