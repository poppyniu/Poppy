package WB2C.UIAutomation;

import WB2CCommon.CommonAssert;
import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CCommon.TestNGListener;
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
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;


@Listeners({ TestNGListener.class })
public class CustomerServiceMngEdit extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private SelfServicePage selfServicePage = new SelfServicePage();

    @Parameters({"browser"})
    @Test

    public void editCustomerServiceMng(String testNGBrowser) throws Exception {
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
                By.xpath(SideMenuConstants.customer_service_xpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.customer_servicemng_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='serviceUserMgmtFrame']"));
        CommonWebDriver.clickElement(driver, By.xpath("//div[@id='grid']/table/tbody/tr[3]/td[8]/a/li"));
        CommonWebDriver.wait(driver, 4);

        driver.findElement(By.xpath("//form[@id='concurrencyForm']/ul/li[1]/span/span/input[2]")).clear();

        CommonWebDriver.sendKeysToElement(driver, By.xpath("//form[@id='concurrencyForm']/ul/li[1]/span/span/input[2]"), "5", 1);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnConcurrencyUpdate']"));
        CommonWebDriver.wait(driver, 4);
        String textboxValue = CommonWebDriver.getElement(driver, By.xpath("//div[@id='grid']/table/tbody/tr[3]/td[6]")).getText();
        if (textboxValue.equals("5")) {
            System.out.println("The item is edited correctly, test pass");
        } else
            CommonAssert.fail("The item is not edited, test fail!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
