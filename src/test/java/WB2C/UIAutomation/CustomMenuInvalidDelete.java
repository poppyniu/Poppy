package WB2C.UIAutomation;

import WB2CCommon.CommonAssert;
import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CConstants.NodeIPConstants;
import WB2CConstants.SideMenuConstants;
import WB2CConstants.TestAccounts;
import WB2CConstants.URLConstants;
import WB2CPages.CustomMenuPage;
import WB2CPages.LoginPage;
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

public class CustomMenuInvalidDelete {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void InvalidDeleteCustomMenu(String testNGBrowser) throws Exception {
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
        // select nothing and delete
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='btnRemove']"));
        CommonWebDriver.wait(driver, 1);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@class='alertify-log alertify-log-delete alertify-log-show']"))) {
            System.out.println("Select nothing to delete, see the correct wanring message, test pass! ");
        } else CommonAssert.fail("Select nothing to delete, does not see the correct wanring message, test fail!");

        WebElement table = driver.findElement(By.xpath(".//*[@class='k-selectable']"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int rowsCount = rows.size() - 1;
        if (rowsCount == 0) {
            CustomMenuPage.CreateFirstLevelMenu(driver);
            CommonWebDriver.wait(driver, 2);
            CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//tbody/tr[1]/td[1]/a/li"));
            CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//tbody/tr[1]/td[7]/a[4]"));
            CommonWebDriver.wait(driver, 1);
            if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='alertify']/div/article/p"))) {
                System.out.println("Confirm to delete dialog appears! ");
                //cancel delete custom menu
                CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='alertify-cancel']"));
                CommonWebDriver.wait(driver, 2);
                WebElement table1 = driver.findElement(By.xpath(".//*[@class='k-selectable']"));
                List<WebElement> rows1 = table1.findElements(By.tagName("tr"));
                int rowsCount1 = rows1.size() - 1;
                if (rowsCount1 == 1) {
                    System.out.println("The custom menu is not deleted, test pass! ");
                    //delete test data
                    CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//tbody/tr[1]/td[1]/a/li"));
                    CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//tbody/tr[1]/td[7]/a[4]"));
                    CommonWebDriver.wait(driver, 1);
                    CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='alertify-ok']"));
                } else CommonAssert.fail("Cancel the delete get error, test fail!");

            } else
                CommonAssert.fail("Confirm to delete dialog does not appears, test fail!");
        } else {
            CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//tbody/tr[1]/td[1]/a/li"));
            CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//tbody/tr[1]/td[7]/a[4]"));
            CommonWebDriver.wait(driver, 1);
            if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='alertify']/div/article/p"))) {
                System.out.println("Confirm to delete dialog appears! ");
                //cancel delete custom menu
                CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='alertify-cancel']"));
                CommonWebDriver.wait(driver, 2);
                WebElement table1 = driver.findElement(By.xpath(".//*[@class='k-selectable']"));
                List<WebElement> rows1 = table1.findElements(By.tagName("tr"));
                int rowsCount1 = rows1.size() - 1;
                if (rowsCount1 == rowsCount) {
                    System.out.println("The custom menu is not deleted, test pass! ");
                } else CommonAssert.fail("Cancel the delete function get error, test fail!");

            } else
                CommonAssert.fail("Confirm to delete dialog does not appears, test fail!");
        }

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
