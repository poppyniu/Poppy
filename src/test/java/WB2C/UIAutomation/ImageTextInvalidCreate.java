package WB2C.UIAutomation;

import WB2CCommon.CommonAssert;
import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CConstants.NodeIPConstants;
import WB2CConstants.SideMenuConstants;
import WB2CConstants.TestAccounts;
import WB2CConstants.URLConstants;
import WB2CPages.LoginPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

public class ImageTextInvalidCreate {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void imageTextInValidCreate(String testNGBrowser) throws Exception {
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
                By.xpath(SideMenuConstants.wechat_management_xpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.wechat_text_image_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='materialNewsMgmtFrame']"));
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath("//span[@class='k-icon k-warning']"))) {
            System.out.println("Do not input title see the correct warning message, test pass! ");
        }
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='title']"), "test title", 1);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if (alertText.contains("创建失败")) {
            System.out.println("Do not upload cover image see the correct dialog, test pass! ");
        } else
            CommonAssert.fail("Do not upload cover image does not see the correct dialog, test fail!");

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
