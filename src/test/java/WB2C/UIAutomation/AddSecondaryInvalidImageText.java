package WB2C.UIAutomation;

import WB2CCommon.*;
import WB2CConstants.NodeIPConstants;
import WB2CConstants.SideMenuConstants;
import WB2CConstants.TestAccounts;
import WB2CConstants.URLConstants;
import WB2CPages.ImageTextPage;
import WB2CPages.LoginPage;
import com.domain.wx.MaterialNews;
import org.openqa.selenium.Alert;
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
public class AddSecondaryInvalidImageText extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private ImageTextPage imageTextPage = new ImageTextPage();
    private ImageTextMaterialUtil imageTextMaterialUtil = new ImageTextMaterialUtil();
    private MaterialNews materialNews1;

    @Parameters({"browser"})
    @Test
    public void secondaryInvalidImageTextAdd(String testNGBrowser) throws Exception {
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
        //add the first secondary image text
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[3]/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 3);
        imageTextPage.CreateSecondaryImageTextMaterial(driver);
        //add the second secondary image text
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[3]/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 3);
        imageTextPage.CreateSecondaryImageTextMaterial(driver);
        //add the third secondary image text
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[3]/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 3);
        imageTextPage.CreateSecondaryImageTextMaterial(driver);
        //add the fourth secondary image text
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[3]/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 3);
        imageTextPage.CreateSecondaryImageTextMaterial(driver);
        //add the fifth secondary image text
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[3]/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 3);
        imageTextPage.CreateSecondaryImageTextMaterial(driver);
        //add the sixth secondary image text
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[3]/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 3);
        imageTextPage.CreateSecondaryImageTextMaterial(driver);
        //add the seventh secondary image text
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[3]/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 3);
        imageTextPage.CreateSecondaryImageTextMaterial(driver);
        //add the eighth secondary image text
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[3]/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 3);
        imageTextPage.CreateSecondaryImageTextMaterial(driver);
        //add the eighth secondary image text
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[3]/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 3);
        imageTextPage.CreateSecondaryImageTextMaterial(driver);
        //add the tenth secondary image text
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='container']/div[3]/div/div[3]/ul/li[1]/a/i"));
        CommonWebDriver.wait(driver, 3);
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if (alertText.contains("多图文消息最多只支持10个图文")) {
            System.out.println("Do not upload cover image see the correct dialog, test pass! ");
            imageTextMaterialUtil.DeleteTextImageMaterial(materialNews1.getId());
        } else
            CommonAssert.fail("Do not upload cover image does not see the correct dialog, test fail!");

    }

    @AfterTest
    public void tearDown() {
        //delete the newly added test data to keep test env clean
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews1.getId());
        driver.quit();
    }
}
