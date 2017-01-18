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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

public class EditSecondaryImageText {
    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private ImageTextPage imageTextPage = new ImageTextPage();
    private ImageTextMaterialUtil imageTextMaterialUtil = new ImageTextMaterialUtil();
    private MaterialNews materialNews1;

    @Parameters({"browser"})
    @Test
    public void secondaryImageTextEdit(String testNGBrowser) throws Exception {
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
        //add a secondary image text
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[3]/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 3);
        imageTextPage.CreateSecondaryImageTextMaterial(driver);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='container']/div[3]/div/div[2]/ul/li/img"))) {
            System.out.println("Create secondary image text material succeed, test pass! ");
        } else
            CommonAssert.fail("Create secondary image text material get error, test fail!");
        //edit the secondary image text
        Actions actions = new Actions(driver);
        WebElement secondImageTextLable = CommonWebDriver.getElement(driver, By.xpath(".//*[@id='container']/div[3]/div/div[2]/ul/li/label"));
        actions.moveToElement(secondImageTextLable).perform();
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[2]/ul/li/div/a[1]/i"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clearTextbox(driver, By.xpath("//input[@id='title']"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='title']"), "edit secondary title", 1);
        CommonWebDriver.clearTextbox(driver, By.xpath("//input[@id='author']"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='author']"), "edit secondary author", 1);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 5);
        String secondaryTitle = CommonWebDriver.getElement(driver, By.xpath(".//*[@id='container']/div[3]/div/div[2]/ul/li/label")).getAttribute("innerHTML");
        if (secondaryTitle.contains("edit secondary title")) {
            System.out.println("Edit secondary image text material succeed, test pass! ");
        } else
            CommonAssert.fail("Edit secondary image text material get error, test fail!");

    }

    @AfterTest
    public void tearDown() {
        //delete the newly added test data to keep test env clean
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews1.getId());
        driver.quit();
    }
}
