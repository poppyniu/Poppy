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
public class CustomMenuRefresh extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void refreshCustomMenu(String testNGBrowser) throws Exception {
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
                By.xpath(SideMenuConstants.wechat_management_xpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.wechat_custom_menu_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='wxMenuMgmtFrame']"));
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='grid']/table/thead/tr/th[1]/a/li"));
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='btnRefresh']"));
        CommonWebDriver.wait(driver, 2);
        boolean checkBoxSelected = CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/thead/tr/th[1]/a/li")).isSelected();
        if (!checkBoxSelected) {
            System.out.println("Refresh custom menu page succeed, test pass! ");
        } else
            CommonAssert.fail("Refresh custom menu page get error, test fail!");

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
