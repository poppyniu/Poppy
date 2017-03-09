package WB2C.UIAutomation;

import WB2CCommon.CommonAssert;
import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CCommon.ImageTextMaterialUtil;
import WB2CConstants.NodeIPConstants;
import WB2CConstants.SideMenuConstants;
import WB2CConstants.TestAccounts;
import WB2CConstants.URLConstants;
import WB2CPages.ImageTextPage;
import WB2CPages.LoginPage;
import com.domain.wx.MaterialNews;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

public class AddSecondaryImageText {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private ImageTextPage imageTextPage = new ImageTextPage();
    private ImageTextMaterialUtil imageTextMaterialUtil = new ImageTextMaterialUtil();
    private MaterialNews materialNews1;

    @Parameters({"browser"})
    @Test
    public void secondaryImageTextAdd(String testNGBrowser) throws Exception {
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
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.wechat_text_image_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='materialNewsMgmtFrame']"));
        //prepare 1 test data
        materialNews1 = imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//a[@id='btnRefresh']"));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath("//input[@id='searchText']"), "test");
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath("//img[@src='/WCPPRO/api/file/5/test.jpg']"))) {
            System.out.println("Input test value can search the correct data, test pass! ");
        } else
            CommonAssert.fail("The search function does not work well, test fail!");
        //add secondary image text
        String testDataPath = System.getProperty("testdata1");
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[3]/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 3);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='title']"), "secondary title", 1);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='author']"), "secondary author", 1);
        JavascriptExecutor removeAttribute = (JavascriptExecutor)driver;
        removeAttribute.executeScript("document.getElementsByClassName('k-widget k-upload k-header k-upload-empty')[0].style.display='block';", new Object[0]);
        CommonWebDriver.wait(driver, 3);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(testDataPath);
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//textarea[@id='summary']"), "secondary summary", 1);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='url']"), "https://www.baidu.com", 1);
        CommonWebDriver.clickElement(driver, By.xpath("//div[@id='tabs']/ul/li[2]/a"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//div[@id='myEditor']"), "secondary editor", 1);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 3);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='container']/div[3]/div/div[2]/ul/li/img"))) {
            System.out.println("Create secondary image text material succeed, test pass! ");
        } else
            CommonAssert.fail("Create secondary image text material get error, test fail!");

    }

    @AfterTest
    public void tearDown() {
        //delete the newly added test data to keep test env clean
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews1.getId());
        CommonWebDriver.wait(driver, 3);
        driver.quit();
    }
}
