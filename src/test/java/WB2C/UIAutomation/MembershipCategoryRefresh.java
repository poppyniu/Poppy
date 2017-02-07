package WB2C.UIAutomation;

import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CConstants.*;
import WB2CPages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

public class MembershipCategoryRefresh {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private String url;

    @Parameters({"browser"})
    @Test
    public void refreshMembershipCategory(String testNGBrowser) throws Exception {
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
                By.xpath(SideMenuConstants.membership_management_xpath));
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.member_category_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='wxMbrCategoryMgmtFrame']"));
        CommonWebDriver.clickElement(driver, By.xpath("//tr[1]/td[1]//li[@class='icon-check-empty icon-large']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnRefresh']"));
        CommonWebDriver.wait(driver, 2);
        boolean checkboxSelected = driver.findElement(By.xpath("//tr[1]/td[1]//li[@class='icon-check-empty icon-large']")).isSelected();
        if (!checkboxSelected) {
            System.out.println("The membership category page is refreshed, test pass!");
        } else {
            System.out.println("The membership category page is Not refreshed, test fail!");
        }
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}


