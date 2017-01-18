package WB2C.UIAutomation;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

import WB2CCommon.CommonAssert;
import WB2CCommon.CommonUtil;
import WB2CConstants.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import WB2CCommon.CommonWebDriver;
import WB2CPages.LoginPage;
import WB2CPages.SelfServicePage;

public class SelfServiceValidCreate {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private SelfServicePage selfServicePage = new SelfServicePage();

    @Parameters({"browser"})
    @Test

    public void validCreateSelfService(String testNGBrowser) throws Exception {
        desiredCapabilities = CommonUtil.getBrowser(testNGBrowser);
        String url = NodeIPConstants.windows_Node1_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        loginPage = new LoginPage(driver);
        testaccounts = new TestAccounts();
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername1, TestAccounts.testuserpwd);
        CommonWebDriver.navigateAndLoadPage(driver,URLConstants.homePageUrl,3);
        //create image text material
        selfServicePage.CreateSelfServiceMaterial(driver);
        //delete the newly added test data to keep test env clean
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//div[@id='grid']/table/tbody/tr[5]/td[9]/a[4]/li"));
        CommonWebDriver.wait(driver,4);
        CommonWebDriver.clickElement(driver,By.xpath("//button[@id='alertify-ok']"));
        CommonWebDriver.wait(driver,1);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}


