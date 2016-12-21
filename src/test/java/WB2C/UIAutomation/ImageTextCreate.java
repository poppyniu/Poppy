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
import WB2CPages.ImageTextPage;

public class ImageTextCreate {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private ImageTextPage imageTextPage = new ImageTextPage();

    @Parameters({"browser"})
    @Test
    public void ImageTextValidCreate(String testNGBrowser) throws Exception {
        desiredCapabilities = CommonUtil.getBrowser(testNGBrowser);
        String url = NodeIPConstants.windows_Node1_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        loginPage = new LoginPage(driver);
        testaccounts = new TestAccounts();
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername, TestAccounts.testuserpwd);
        CommonWebDriver.navigateAndLoadPage(driver,URLConstants.homePageUrl,3);
        //create image text material
        imageTextPage.CreateImageTextMaterial(driver);
        //delete the newly added test data to keep test env clean
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//a[@id='btnRefresh']"));
        CommonWebDriver.wait(driver,4);
        CommonWebDriver.clickElement(driver,By.xpath("//div[@id='container']/div[3]/div/div[3]/ul/li[3]/a"));
        CommonWebDriver.wait(driver,1);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
        CommonWebDriver.wait(driver,2);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
