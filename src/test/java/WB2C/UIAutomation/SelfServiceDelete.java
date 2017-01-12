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
 * Created by Joe.Liu on 2016/12/26.
 */
public class SelfServiceDelete {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private SelfServicePage selfServicePage = new SelfServicePage();

    @Parameters({"browser"})
    @Test

    public void SelfServiceDelete(String testNGBrowser) throws Exception {
        desiredCapabilities = CommonUtil.getBrowser(testNGBrowser);
        String url = NodeIPConstants.windows_local_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        loginPage = new LoginPage(driver);
        testaccounts = new TestAccounts();
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername1, TestAccounts.testuserpwd);
        CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
        //create self service material
        selfServicePage.CreateSelfServiceMaterial(driver);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnRefresh']"));
        CommonWebDriver.wait(driver, 2);

        WebElement table = driver.findElement(By.xpath("//table[@class='k-selectable']"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int newTableCount = rows.size() - 1;


        //delete test data
        String chkXpath = "//tr[" + newTableCount + "]/td[1]//li";
        CommonWebDriver.clickElement(driver, By.xpath(chkXpath));
        CommonWebDriver.wait(driver, 2);

        CommonWebDriver.clickElement(driver,By.xpath("//a[@id='btnRemove']"));
        if(CommonWebDriver.isElementDisplayed(driver,By.xpath("//section[@id='alertify']")))
        {
            System.out.println("The correct delete pop up dialog appear, test pass!");
        }

        CommonWebDriver.clickElementWhenPresent(driver,By.xpath("//button[@id='alertify-cancel']"),"Delete account pop up dialog cancel button.");
        if(CommonWebDriver.isElementDisplayed(driver,By.xpath(chkXpath)))
        {
            System.out.println("The wechat account is not deleted, test pass!");
        }
        else
        CommonAssert.fail("cancel button does not work , test fail!");
        CommonWebDriver.wait(driver, 2);

        CommonWebDriver.clickElement(driver,By.xpath("//a[@id='btnRemove']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath("//button[@id='alertify-ok']"));
        CommonWebDriver.wait(driver, 2);
        if(!CommonWebDriver.isElementDisplayed(driver,By.xpath(chkXpath)))
        {
            System.out.println("The wechat account is deleted, test pass!");
        }
        else
        CommonAssert.fail("OK button doesn't work, test fail!");
    }

        @AfterTest
        public void tearDown() {
            driver.quit();
        }

}
