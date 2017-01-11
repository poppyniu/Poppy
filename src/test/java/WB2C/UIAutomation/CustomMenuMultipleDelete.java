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

public class CustomMenuMultipleDelete {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void MultipleDeleteCustomMenu(String testNGBrowser) throws Exception {
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
        WebElement table = driver.findElement(By.xpath(".//*[@class='k-selectable']"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int rowsCount = rows.size() - 1;
        if (rowsCount >= 2) {
            CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//tbody/tr[1]/td[1]/a/li"));
            CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//tbody/tr[2]/td[1]/a/li"));
            CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='btnRemove']"));
            CommonWebDriver.wait(driver, 1);
            if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='alertify']/div/article/p"))) {
                System.out.println("Confirm to delete dialog appears! ");
                //delete custom menu
                CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='alertify-ok']"));
                CommonWebDriver.wait(driver, 2);
                WebElement table1 = driver.findElement(By.xpath(".//*[@class='k-selectable']"));
                List<WebElement> rows1 = table.findElements(By.tagName("tr"));
                int rowsCount1 = rows1.size() - 1;
                int expectCount = 2;
                if (rowsCount - rowsCount1 == expectCount) {
                    System.out.println("The custom menu is deleted, test pass! ");
                } else CommonAssert.fail("The custom menu is not deleted, test fail!");

            } else
                CommonAssert.fail("Confirm to delete dialog does not appears, test fail!");
        } else {
            CustomMenuPage.CreateFirstLevelMenu(driver);
            CommonWebDriver.wait(driver, 2);
            CustomMenuPage.CreateFirstLevelMenu(driver);
            CommonWebDriver.wait(driver, 2);
            CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//tbody/tr[1]/td[1]/a/li"));
            CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//tbody/tr[2]/td[1]/a/li"));
            CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='btnRemove']"));
            CommonWebDriver.wait(driver, 1);
            if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='alertify']/div/article/p"))) {
                System.out.println("Confirm to delete dialog appears! ");
                //delete custom menu
                CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='alertify-ok']"));
                CommonWebDriver.wait(driver, 2);
                WebElement table1 = driver.findElement(By.xpath(".//*[@class='k-selectable']"));
                List<WebElement> rows1 = table1.findElements(By.tagName("tr"));
                int rowsCount1 = rows1.size() - 1;
                if (rowsCount1 == rowsCount) {
                    System.out.println("The custom menu is deleted, test pass! ");
                } else CommonAssert.fail("The custom menu is not deleted, test fail!");

            } else
                CommonAssert.fail("Confirm to delete dialog does not appears, test fail!");
        }

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
