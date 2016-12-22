package WB2C.UIAutomation;

import java.net.URL;
import java.util.List;
import WB2CCommon.CommonAssert;
import WB2CCommon.CommonUtil;
import WB2CConstants.*;
import WB2CPages.CustomMenuPage;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import WB2CCommon.CommonWebDriver;
import WB2CPages.LoginPage;

public class CustomMenuSynchronize {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void SynchronizeCustomMenu(String testNGBrowser) throws Exception {
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
        CommonWebDriver.wait(driver,2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.wechat_custom_menu_xpath));
        CommonWebDriver.wait(driver,4);
        CommonWebDriver.switchToFrame(driver,By.xpath("//iframe[@id='wxMenuMgmtFrame']"));
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='btnSync']"));
        CommonWebDriver.wait(driver,1);
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='alertify-ok']"));
        CommonWebDriver.wait(driver,1);
        if(CommonWebDriver.isElementDisplayed(driver,By.xpath(".//*[@id='alertify']/div/article/p")))
        {
            System.out.println("Sync to we chat server succeed, test pass! ");
        }
        else
            CommonAssert.fail("Sync to we chat server get error, test fail!");

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}