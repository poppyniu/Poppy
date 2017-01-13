package WB2C.UIAutomation;

import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CConstants.NodeIPConstants;
import WB2CConstants.TestAccounts;
import WB2CConstants.URLConstants;
import WB2CPages.LoginPage;
import WB2CPages.SelfServicePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Created by Joe.Liu on 2016/12/22.
 */
public class SelfServiceInvalidCreate {

        private WebDriver driver;
        private LoginPage loginPage;
        private TestAccounts testaccounts;
        private DesiredCapabilities desiredCapabilities;
        private SelfServicePage selfServicePage = new SelfServicePage();

        @Parameters({"browser"})
        @Test

        public void SelfServiceInvalidCreate(String testNGBrowser) throws Exception {
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
            selfServicePage.InvalidCreateSelfServiceMaterial(driver);
        }

        @AfterTest
        public void tearDown() {
            driver.quit();
        }
}
