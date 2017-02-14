package WB2C.UIAutomation;

import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CConstants.*;
import WB2CPages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

public class WechatMembersEdit {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private String url;

    @Parameters({"browser"})
    @Test
    public void wechatMembersEdit(String testNGBrowser) throws Exception {
        desiredCapabilities = CommonUtil.getBrowser(testNGBrowser);
        url = NodeIPConstants.windows_Node1_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        loginPage = new LoginPage(driver);
        testaccounts = new TestAccounts();
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername, TestAccounts.testuserpwd);
        CommonWebDriver.isElementDisplayed(driver,
                By.xpath(LoginConstants.span_username_xpath));
        CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.membership_management_xpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.wechat_member_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='wxMemberMgmtFrame']"));
        //CommonWebDriver.wait(driver, 2);
        String expectValue = "shen";
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='searchText']"), expectValue,
                "Membership Category Search text box.");
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("//a[@id='btnSearch']/li"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("//li[@class='icon-pencil icon-large']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clearTextbox(driver, By.xpath("//input[@id='remark']"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='remark']"), "my wechat");
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//span[@class='k-select']"));
        CommonWebDriver.wait(driver, 2);
        WebElement option = driver.findElement(By.xpath("//*[@id='memberCategoryId_listbox']/li[3]"));
        String expect_category = option.getText();
        //CommonWebDriver.wait(driver, 1);
        option.click();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        CommonWebDriver.wait(driver, 1);
        //*[@id='wxMemberForm']/ul/li[9]/span/span/input
        //String expect_category=CommonWebDriver.getElement(driver,By.xpath("//*[@id='wxMemberForm']//li[9]//input")).getText();
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        //td4 会员类别 td7 备注
        //tbody/tr/td[4]
        String actual_category = CommonWebDriver.getElement(driver, By.xpath("//tbody/tr/td[4]")).getText();
        Assert.assertEquals(actual_category, expect_category);


    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

}
