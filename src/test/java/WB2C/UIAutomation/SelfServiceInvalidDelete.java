package WB2C.UIAutomation;

import WB2CCommon.CommonAssert;
import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CConstants.NodeIPConstants;
import WB2CConstants.SideMenuConstants;
import WB2CConstants.TestAccounts;
import WB2CConstants.URLConstants;
import WB2CPages.LoginPage;
import WB2CPages.SelfServicePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

/**
 * Created by Joe.Liu on 2016/12/28.
 */
public class SelfServiceInvalidDelete {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private SelfServicePage selfServicePage = new SelfServicePage();

    @Parameters({"browser"})
    @Test

    public void SelfServiceInvalidDelete(String testNGBrowser) throws Exception {
        desiredCapabilities = CommonUtil.getBrowser(testNGBrowser);
        String url = NodeIPConstants.windows_Node1_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        loginPage = new LoginPage(driver);
        testaccounts = new TestAccounts();
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername1, TestAccounts.testuserpwd);
        CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);

        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.customer_service_xpath));
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.self_service_xpath));
        CommonWebDriver.wait(driver,4);
        CommonWebDriver.switchToFrame(driver,By.xpath("//iframe[@id='intelligenceMenuMgmtFrame']"));

       //delete test data
        CommonWebDriver.clickElement(driver,By.xpath("//a[@id='btnRemove']"));
        CommonWebDriver.wait(driver,2);
        if(CommonWebDriver.isElementDisplayed(driver,By.xpath("//section[@id='alertify-logs']/article")))
        {
            System.out.println("The correct delete pop up dialog appear‘尚未勾选记录，无法进行删除操作。’ test pass!");
        }
        else
        CommonAssert.fail("remove button does not work, test fail!");

        CommonWebDriver.wait(driver,4);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
