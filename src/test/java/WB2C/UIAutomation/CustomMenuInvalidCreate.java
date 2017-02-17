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

public class CustomMenuInvalidCreate {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void invalidCreateCustomMenu(String testNGBrowser) throws Exception {
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
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath("//span[@class='k-icon k-warning']")) & CommonWebDriver.isElementDisplayed(driver, By.xpath("//form[@id='form']/ul/li[2]/span/span")) & CommonWebDriver.isElementDisplayed(driver, By.xpath("//form[@id='form']/ul/li[8]/span/span[2]/span"))) {
            System.out.println("Do not input name,key,state see the correct warning message, test pass! ");
            CommonWebDriver.clickElement(driver, By.xpath("//span[@class='k-icon k-i-close']"));
        } else
            CommonAssert.fail("Do not input name,key,state does not see the correct warning message, test fail!");
        //create more than 3 first level menu
        List<WebElement> levelOneIcon = driver.findElements(By.xpath("//i[@class='icon-reorder level-one']"));
        int levelOneIconCount = levelOneIcon.size();
        int needCreateNum = 3 - levelOneIconCount;
        if (needCreateNum == 0) {
            CustomMenuPage.CreateFirstLevelMenu(driver);
            CommonUtil.alertExist(driver, "最多允许创建3个一级菜单");
            CommonWebDriver.wait(driver,2);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath("html/body/div[7]/div[1]/div/a/span"));
            CommonWebDriver.wait(driver,1);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='grid']/table/thead/tr/th[1]/a/li"));
            CommonWebDriver.wait(driver,1);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='btnRemove']"));
            CommonWebDriver.wait(driver,2);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='alertify-ok']"));
        } else if (needCreateNum == 1) {
            CustomMenuPage.CreateFirstLevelMenu(driver);
            CommonWebDriver.wait(driver, 2);
            CustomMenuPage.CreateFirstLevelMenu(driver);
            CommonUtil.alertExist(driver, "最多允许创建3个一级菜单");
            CommonWebDriver.wait(driver,2);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath("html/body/div[7]/div[1]/div/a/span"));
            CommonWebDriver.wait(driver,1);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='grid']/table/thead/tr/th[1]/a/li"));
            CommonWebDriver.wait(driver,1);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='btnRemove']"));
            CommonWebDriver.wait(driver,2);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='alertify-ok']"));
        } else if (needCreateNum == 2) {
            CustomMenuPage.CreateFirstLevelMenu(driver);
            CommonWebDriver.wait(driver, 2);
            CustomMenuPage.CreateFirstLevelMenu(driver);
            CommonWebDriver.wait(driver, 2);
            CustomMenuPage.CreateFirstLevelMenu(driver);
            CommonUtil.alertExist(driver, "最多允许创建3个一级菜单");
            CommonWebDriver.wait(driver,2);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath("html/body/div[7]/div[1]/div/a/span"));
            CommonWebDriver.wait(driver,1);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='grid']/table/thead/tr/th[1]/a/li"));
            CommonWebDriver.wait(driver,1);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='btnRemove']"));
            CommonWebDriver.wait(driver,2);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='alertify-ok']"));
        } else if (needCreateNum == 3) {
            CustomMenuPage.CreateFirstLevelMenu(driver);
            CommonWebDriver.wait(driver, 2);
            CustomMenuPage.CreateFirstLevelMenu(driver);
            CommonWebDriver.wait(driver, 2);
            CustomMenuPage.CreateFirstLevelMenu(driver);
            CommonWebDriver.wait(driver, 2);
            CustomMenuPage.CreateFirstLevelMenu(driver);
            CommonUtil.alertExist(driver, "最多允许创建3个一级菜单");
            CommonWebDriver.wait(driver,2);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath("html/body/div[7]/div[1]/div/a/span"));
            CommonWebDriver.wait(driver,1);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='grid']/table/thead/tr/th[1]/a/li"));
            CommonWebDriver.wait(driver,1);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='btnRemove']"));
            CommonWebDriver.wait(driver,2);
            CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='alertify-ok']"));
        }

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
