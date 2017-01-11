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

import java.net.URL;

public class QuickReplyPageSwtiching {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private String url;

    @Parameters({"browser"})
    @Test
    public void QuickReplyPageSwtiching(String testNGBrowser) throws Exception {
        desiredCapabilities = CommonUtil.getBrowser(testNGBrowser);
        url = NodeIPConstants.windows_Node1_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        loginPage = new LoginPage(driver);
        testaccounts = new TestAccounts();
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername1, TestAccounts.testuserpwd);
        CommonWebDriver.isElementDisplayed(driver,
                By.xpath(LoginConstants.span_username_xpath));
        CommonWebDriver.navigateAndLoadPage(driver, URLConstants.homePageUrl, 3);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.customer_service_xpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.quick_reply_xpath));
        CommonWebDriver.wait(driver, 3);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='quickMessageMgmtFrame']"));
        CommonWebDriver.wait(driver, 2);
        int i = 1;
        String input = "test quick reply";
        do {
            CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
            CommonWebDriver.wait(driver, 2);
            //String inputValue = String.format("%s%s", input, i);
            CommonWebDriver.sendKeysToElement(driver, By.xpath("//textarea[@id='content']"), input);
            CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
            CommonWebDriver.wait(driver, 5);
            i++;
        } while (i < 21);
        //click the next page
        CommonWebDriver.clickElement(driver, By.xpath("//span[@class='icon-step-forward']"));
        CommonWebDriver.wait(driver, 3);
        //显示11-20
        WebElement e = CommonWebDriver.getElement(driver, By.xpath("//span[@class='k-pager-info k-label']"));
        Assert.assertTrue(e.getText().contains("显示11-20"));
        //click the previous page
        CommonWebDriver.clickElement(driver, By.xpath("//span[@class='icon-step-backward']"));
        CommonWebDriver.wait(driver, 3);
        WebElement e1 = CommonWebDriver.getElement(driver, By.xpath("//span[@class='k-pager-info k-label']"));
        Assert.assertTrue(e1.getText().contains("显示1-10"));
        //click the second page
        CommonWebDriver.clickElement(driver, By.xpath("//a[@data-page='2']"));
        CommonWebDriver.wait(driver, 1);
        Assert.assertTrue(e.getText().contains("显示11-20"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='searchText']"), input,
                "quick reply Search text box.");
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("//a[@id='btnSearch']/li"));
        CommonWebDriver.wait(driver, 3);
        //delete test data
       for(int j = 0;; j++){
           String pageNum=CommonWebDriver.getElement(driver, By.xpath("//span[@class='k-state-selected']")).getText();
           if(pageNum.startsWith("0")){
               break;
           }
           CommonWebDriver.clickElement(driver,By.xpath("//a[@class='check-all']/li"));
           CommonWebDriver.wait(driver,2);
           CommonWebDriver.clickElement(driver,By.xpath("//a[@id='btnRemove']"));
           CommonWebDriver.wait(driver,2);
           CommonWebDriver.clickElement(driver,By.xpath("//button[@id='alertify-ok']"));
           CommonWebDriver.wait(driver,2);
           CommonWebDriver.clickElementWhenPresent(driver,
                   By.xpath("//a[@id='btnSearch']/li"));
           CommonWebDriver.wait(driver, 2);
        }
    }
    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

}
