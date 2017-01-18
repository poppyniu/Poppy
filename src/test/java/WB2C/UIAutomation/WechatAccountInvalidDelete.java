package WB2C.UIAutomation;

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

public class WechatAccountInvalidDelete {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void accountInvalidDelete(String testNGBrowser) throws Exception {
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
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.wechat_acount_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='wxAccountMgmtFrame']"));
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnRemove']"));
        CommonWebDriver.wait(driver, 1);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath("//article[@class='alertify-log alertify-log-delete alertify-log-show']"))) {
            System.out.println("The correct delete pop up dialog appear, test pass!");
        }
        CommonWebDriver.wait(driver, 3);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//div[@id='grid']/table/tbody/tr/td[5]/a[2]"));
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//button[@id='alertify-cancel']"), "Delete account pop up dialog cancel button.");
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath("//table[@class='k-selectable']/tbody/tr/td[2]"))) {
            System.out.println("The wechat account is not deleted, test pass!");
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
