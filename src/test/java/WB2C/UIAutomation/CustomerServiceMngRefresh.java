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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Created by Joe.Liu on 2016/12/28.
 */
public class CustomerServiceMngRefresh {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private SelfServicePage selfServicePage = new SelfServicePage();

    @Parameters({"browser"})
    @Test

    public void CustomerServiceMngRefresh(String testNGBrowser) throws Exception {
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

        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.customer_service_xpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.customer_servicemng_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='serviceUserMgmtFrame']"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='searchText']"), "joe");
        CommonWebDriver.wait(driver, 4);

        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnRefresh']"));
        CommonWebDriver.wait(driver, 4);

        String textboxValue=CommonWebDriver.getElement(driver,By.xpath("//input[@id='searchText']")).getAttribute("value").toString();
        if(textboxValue.equals(""))
        {
            System.out.println("The page is refreshed, test pass");
        }
        else
            CommonAssert.fail("The page is not refreshed, test fail!");



    }

    //delete the newly added test data to keep test env clean
//        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//a[@id='btnRefresh']"));
//        CommonWebDriver.wait(driver,4);
//        CommonWebDriver.clickElement(driver,By.xpath("//div[@id='container']/div[3]/div/div[3]/ul/li[3]/a"));
//        CommonWebDriver.wait(driver,1);
//        Actions action = new Actions(driver);
//        action.sendKeys(Keys.ENTER).perform();
//        CommonWebDriver.wait(driver,2);


    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
