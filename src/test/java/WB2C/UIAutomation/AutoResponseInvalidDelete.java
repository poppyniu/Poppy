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
public class AutoResponseInvalidDelete extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void invalidDeleteAutoResponse(String testNGBrowser) throws Exception {
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
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.we_chat_auto_response));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='automessageMgmtFrame']"));
        //select nothing and delete
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='btnRemove']"));
        CommonWebDriver.wait(driver, 1);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='alertify-logs']/article"))) {
            System.out.println("Select nothing to delete see the correct warning message, test pass!");
        } else
            CommonAssert.fail("Select nothing to delete does not see the correct warning message, test fail! ");
        //prepare test data
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='keywords']"), "hello");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='wxAutoMsgForm']/ul/li[2]/span/span/span/span"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='matchMode_option_selected']"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='content']"), "hello");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(".//*[@id='searchText']"), "hello");
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[4]")).getAttribute("innerHTML").contains("hello")) {
            System.out.println("Create auto response succeed, test pass! ");
        } else
            CommonAssert.fail("Create auto response get error, test fail! ");
        //delete test data and click cancel
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[8]/a[4]/li"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='alertify']/div/article/p"))) {
            System.out.println("Delete a item see the correct warning dialog, test pass!");
        } else
            CommonAssert.fail("Delete a item does not see the correct warning dialog, test fail!");
        //click cancel button to cancel delete
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='alertify-cancel']"));
        CommonWebDriver.wait(driver, 2);
        WebElement table = driver.findElement(By.xpath("//table[@class='k-selectable']"));
        List<WebElement> rows2 = table.findElements(By.tagName("tr"));
        int tableCount = rows2.size();
        int expectCount = 2;
        if (tableCount == expectCount) {
            System.out.println("Cancel a delete succeed, test pass! ");
            //delete test data
            CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[8]/a[4]/li"));
            CommonWebDriver.wait(driver, 2);
            CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='alertify-ok']"));
            CommonWebDriver.wait(driver, 2);
        } else
            CommonAssert.fail("Cancel a delete get error, test fail!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
