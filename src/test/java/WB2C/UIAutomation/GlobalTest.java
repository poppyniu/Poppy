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
public class GlobalTest extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;

    @Parameters({"browser"})
    @Test
    public void editAutoResponse(String testNGBrowser) throws Exception {
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
                By.xpath("html/body/div[1]/div/div/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 2);
        //select english as language
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='en']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.wechat_management_xpath));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='materialNewsMgmt']")).getAttribute("innerHTML").contains("image-text materials")) {
            System.out.println("The content management side menu is translated into english, test pass! ");
        } else
            CommonAssert.fail("The content management side menu is not translated into english, test fail! ");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.membership_management_xpath));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='wxMemberMgmt']")).getAttribute("innerHTML").contains("wechat member")) {
            System.out.println("The member management side menu is translated into english, test pass! ");
        } else
            CommonAssert.fail("The member management side menu is not translated into english, test fail! ");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.customer_service_xpath));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='quickMessageMgmt']")).getAttribute("innerHTML").contains("quick reply")) {
            System.out.println("The custom service system side menu is translated into english, test pass! ");
        } else
            CommonAssert.fail("The custom service system side menu is not translated into english, test fail! ");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.system_setup_xpath));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='userGroupMgmt']")).getAttribute("innerHTML").contains("group management")) {
            System.out.println("The system management side menu is translated into english, test pass! ");
        } else
            CommonAssert.fail("The system management side menu is not translated into english, test fail! ");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.statistical_analysis_xpath));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='reportMemLocMgmt']")).getAttribute("innerHTML").contains("member statistics")) {
            System.out.println("The analysis side menu is translated into english, test pass! ");
        } else
            CommonAssert.fail("The analysis side menu is not translated into english, test fail! ");
        //select chinese as language
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath("html/body/div[1]/div/div/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='zh-cn']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.wechat_management_xpath));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='materialNewsMgmt']")).getAttribute("innerHTML").contains("图文素材")) {
            System.out.println("The content management side menu is translated into chinese, test pass! ");
        } else
            CommonAssert.fail("The content management side menu is not translated into chinese, test fail! ");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.membership_management_xpath));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='wxMemberMgmt']")).getAttribute("innerHTML").contains("微信会员")) {
            System.out.println("The member management side menu is translated into chinese, test pass! ");
        } else
            CommonAssert.fail("The member management side menu is not translated into chinese, test fail! ");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.customer_service_xpath));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='quickMessageMgmt']")).getAttribute("innerHTML").contains("快速回复")) {
            System.out.println("The custom service system side menu is translated into chinese, test pass! ");
        } else
            CommonAssert.fail("The custom service system side menu is not translated into chinese, test fail! ");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.system_setup_xpath));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='userGroupMgmt']")).getAttribute("innerHTML").contains("群组管理")) {
            System.out.println("The system management side menu is translated into chinese, test pass! ");
        } else
            CommonAssert.fail("The system management side menu is not translated into chinese, test fail! ");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.statistical_analysis_xpath));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.getElement(driver, By.xpath(".//*[@id='reportMemLocMgmt']")).getAttribute("innerHTML").contains("会员统计")) {
            System.out.println("The analysis side menu is translated into chinese, test pass! ");
        } else
            CommonAssert.fail("The analysis side menu is not translated into chinese, test fail! ");

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
