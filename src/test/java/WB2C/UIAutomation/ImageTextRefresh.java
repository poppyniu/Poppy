package WB2C.UIAutomation;

import java.net.URL;
import WB2CCommon.CommonAssert;
import WB2CCommon.CommonUtil;
import WB2CConstants.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import WB2CCommon.CommonWebDriver;
import WB2CPages.LoginPage;

public class ImageTextRefresh {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void RefreshImageText(String testNGBrowser) throws Exception {
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
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.wechat_management_xpath));
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.wechat_text_image_xpath));
        CommonWebDriver.wait(driver,4);
        CommonWebDriver.switchToFrame(driver,By.xpath("//iframe[@id='materialNewsMgmtFrame']"));
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//input[@id='searchText']"),"test");
        CommonWebDriver.clickElement(driver,By.xpath("//a[@id='btnRefresh']"));
        CommonWebDriver.wait(driver,2);
        String textboxValue=CommonWebDriver.getElement(driver,By.xpath("//input[@id='searchText']")).getAttribute("value");
        if(textboxValue.equals(""))
        {
            System.out.println("The page is refreshed, test pass");
        }
        else
            CommonAssert.fail("The page is not refreshed, test fail!");

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}