package WB2C.UIAutomation;

import WB2CCommon.CommonAssert;
import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

public class GroupMessageExecute {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void executeGroupMessage(String testNGBrowser) throws Exception {
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
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.we_chat_group_message));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='wxPushMessageMgmtFrame']"));
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='name']"), "test group message");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='tabs-1']/ul/li[2]/span/span/span/span"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='msgType_listbox']/li[2]"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='text']"), "test group message");
        //click tab2
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='tabs']/ul/li[2]/a"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='tabs-2']/ul/li[1]/span/span/span/span"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='memberCategoryId_listbox']/li[1]"));
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='tabs-2']/ul/li[2]/span/span/span/span"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='gender_listbox']/li[2]"));
//        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='tabs-2']/ul/li[3]/span/span/span/span"));
//        CommonWebDriver.wait(driver, 2);
//        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='province_listbox']/li[3]"));
//        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='tabs-2']/ul/li[4]/span/span/span/span"));
//        CommonWebDriver.wait(driver, 2);
//        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='city_option_selected']"));
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(".//*[@id='searchText']"), "group message");
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[2]")).getAttribute("innerHTML").contains("group message")) {
            System.out.println("Create group message succeed, scenario pass! ");
        } else
            CommonAssert.fail("Create group message get error, scenario fail! ");
        //execute this new created group message
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[8]/a[2]/li"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='alertify']/div/article/p"))) {
            System.out.println("Execute group message see the confirming dialog, test pass! ");
        } else
            CommonAssert.fail("Execute group message does not see the confirming dialog, test fail! ");
        //click ok to confirm execute
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='alertify-ok']"));
        CommonWebDriver.wait(driver, 1);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='alertify-logs']/article"))) {
            System.out.println("Execute group message see the wanrning message, scenario pass! ");
        } else
            CommonAssert.fail("Execute group message does not the wanrning message, scenario fail! ");
        CommonWebDriver.wait(driver, 3);
        String status = CommonWebDriver.getElement(driver, By.xpath(".//*[@id='grid']/table/tbody/tr/td[3]")).getAttribute("innerHTML");
        if (status.contains("成功")) {
            System.out.println("Execute group message succeed, test pass! ");
        } else
            CommonAssert.fail("Execute group message get error, test fail! ");


    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
