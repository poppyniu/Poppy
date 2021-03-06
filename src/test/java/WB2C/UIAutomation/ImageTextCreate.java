package WB2C.UIAutomation;

import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CCommon.ImageTextMaterialUtil;
import WB2CCommon.TestNGListener;
import WB2CConstants.NodeIPConstants;
import WB2CConstants.SideMenuConstants;
import WB2CConstants.TestAccounts;
import WB2CConstants.URLConstants;
import WB2CPages.ImageTextPage;
import WB2CPages.LoginPage;
import com.domain.wx.MaterialNews;
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
public class ImageTextCreate extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private ImageTextPage imageTextPage = new ImageTextPage();

    @Parameters({"browser"})
    @Test
    public void imageTextValidCreate(String testNGBrowser) throws Exception {
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
        //create image text material
        imageTextPage.CreateImageTextMaterial(driver);
        //delete the newly added test data to keep test env clean
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//a[@id='btnRefresh']"));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(".//*[@id='searchText']"), "test title");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='container']/div[3]/div/div[3]/ul/li[3]/a/i"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[4]/a[1]"));
        CommonWebDriver.wait(driver, 2);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
