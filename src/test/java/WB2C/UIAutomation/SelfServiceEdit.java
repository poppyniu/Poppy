package WB2C.UIAutomation;

import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CConstants.NodeIPConstants;
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

public class SelfServiceEdit {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private SelfServicePage selfServicePage = new SelfServicePage();

    @Parameters({"browser"})
    @Test

    public void editSelfService(String testNGBrowser) throws Exception {
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
        //create image text material
        selfServicePage.CreateSelfServiceMaterial(driver);

        WebElement table = driver.findElement(By.xpath("//table[@class='k-selectable']"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int newTableCount = rows.size() - 1;
        //select test data
        String chkXpath = "//tr[" + newTableCount + "]/td[1]//li";
        CommonWebDriver.clickElement(driver, By.xpath(chkXpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath("//tr[" + newTableCount + "]/td[9]/a[3]/li"));
        CommonWebDriver.wait(driver, 2);

        //delete the newly added test data to keep test env clean
        if(CommonWebDriver.isElementDisplayed(driver,By.xpath("//span[@id='window_wnd_title']")))
        {
            System.out.println("The correct edit pop up dialog appear, test pass!");
        }
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//input[@id='name']"),"edit name",1);
        CommonWebDriver.wait(driver,4);
        CommonWebDriver.clickElement(driver,By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver,4);
        //delete the newly added test data to keep test env clean
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//div[@id='grid']/table/tbody/tr[5]/td[9]/a[4]/li"));
        CommonWebDriver.wait(driver,4);
        CommonWebDriver.clickElement(driver,By.xpath("//button[@id='alertify-ok']"));
        CommonWebDriver.wait(driver,1);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
