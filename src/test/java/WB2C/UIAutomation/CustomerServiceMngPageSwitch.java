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
import WB2CPages.SystemSetupPage;
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

public class CustomerServiceMngPageSwitch {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private SelfServicePage selfServicePage = new SelfServicePage();

    @Parameters({"browser"})
    @Test


    public void switchCustomerServiceMngPage(String testNGBrowser) throws Exception {
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
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.customer_servicemng_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='serviceUserMgmtFrame']"));

        WebElement table = driver.findElement(By.xpath("//table[@class='k-selectable']"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int oldTableCount = rows.size();
        if (oldTableCount < 11) {
            int i;
            CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
            for (i = 0; i < 10; i++) {

//                CommonWebDriver.clickElement(driver, By.xpath("//ul[@class='page-sidebar-menu']"));
                String input = "testuser";

                String inputValue = String.format("%s%s", input, i);

                SystemSetupPage.UserMngCreate(driver, inputValue);
            }
            CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
            CommonWebDriver.clickElementWhenPresent(driver,
                    By.xpath(SideMenuConstants.customer_service_xpath));
            CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.customer_servicemng_xpath));
            CommonWebDriver.wait(driver, 4);
            CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='serviceUserMgmtFrame']"));
        }

        CommonWebDriver.clickElement(driver, By.xpath("//div[@id='grid']/div/a[3]"));
        CommonWebDriver.wait(driver, 4);
        String pageValue = CommonWebDriver.getElement(driver, By.xpath("//span[@class='k-state-selected']")).getText();
        if (pageValue.equals("2")) {
            System.out.println("The page is switched correctly, test pass");
        } else
            CommonAssert.fail("The page is not switched , test fail!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
