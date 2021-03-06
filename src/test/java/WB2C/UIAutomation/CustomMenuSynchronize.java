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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

@Listeners({ TestNGListener.class })
public class CustomMenuSynchronize extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void synchronizeCustomMenu(String testNGBrowser) throws Exception {
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
        //create test data
        WebElement table1 = driver.findElement(By.xpath("//table[@class='k-selectable']"));
        List<WebElement> rows1 = table1.findElements(By.tagName("tr"));
        int oldTableCount = rows1.size();
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='name']"), "test menu");
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='event']"), "1");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='form']/ul/li[3]/span/span/span/span"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath("//ul[@id='type_listbox']/li[3]"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='text']"), "test response");
        CommonWebDriver.clickElement(driver, By.xpath("//form[@id='form']/ul/li[8]/span/span/span/span"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElement(driver, By.xpath("//ul[@id='state_listbox']/li[1]"));
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        WebElement table2 = driver.findElement(By.xpath("//table[@class='k-selectable']"));
        List<WebElement> rows2 = table2.findElements(By.tagName("tr"));
        int newTableCount = rows2.size();
        int expectCount = 1;
        if (newTableCount - oldTableCount == expectCount) {
            System.out.println("Create custom menu succeed, test pass! ");
        } else
            CommonAssert.fail("Create custom menu get error, test fail!");
        //test sync function
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='btnSync']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='alertify-ok']"));
        CommonWebDriver.wait(driver, 5);
        if (CommonWebDriver.getElement(driver,By.xpath(".//*[@id='alertify']/div/article/p")).getText().contains("成功")||CommonWebDriver.getElement(driver,By.xpath(".//*[@id='alertify']/div/article/p")).getText().contains("synchronized successfully")) {
            System.out.println("Sync to we chat server succeed, test pass! ");
            CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='alertify-ok']"));
            CommonWebDriver.wait(driver, 2);
        } else
            CommonAssert.fail("Sync to we chat server get error, test fail!");
        //delete test data
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[7]/a[4]/li"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//button[@id='alertify-ok']"));
    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
