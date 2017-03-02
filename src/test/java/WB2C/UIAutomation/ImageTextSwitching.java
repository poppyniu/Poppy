package WB2C.UIAutomation;

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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

public class ImageTextSwitching {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private ImageTextPage imageTextPage = new ImageTextPage();
    private ImageTextMaterialUtil imageTextMaterialUtil = new ImageTextMaterialUtil();
    private MaterialNews materialNews1;
    private MaterialNews materialNews2;
    private MaterialNews materialNews3;
    private MaterialNews materialNews4;
    private MaterialNews materialNews5;
    private MaterialNews materialNews6;
    private MaterialNews materialNews7;
    private MaterialNews materialNews8;
    private MaterialNews materialNews9;
    private MaterialNews materialNews10;
    private MaterialNews materialNews11;


    @Parameters({"browser"})
    @Test
    public void imageTextPageSwitching(String testNGBrowser) throws Exception {
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
        //prepare 10 test data
        materialNews1 = imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver, 2);
        materialNews2 = imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver, 2);
        materialNews3 = imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver, 2);
        materialNews4 = imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver, 2);
        materialNews5 = imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver, 2);
        materialNews6 = imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver, 2);
        materialNews7 = imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver, 2);
        materialNews8 = imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver, 2);
        materialNews9 = imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver, 2);
        materialNews10 = imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver, 2);
        materialNews11 = imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//a[@id='btnRefresh']"));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//span[@class='k-icon k-i-arrow-s']"));
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
        CommonWebDriver.wait(driver, 3);
        List<WebElement> iconPlusList = CommonWebDriver.getElements(driver, By.xpath(".//*[@id='container']//div[contains(@style,'visible')]//a[@class='js_add']/i"), 3);
        int iconPlusCount = iconPlusList.size();
        if (iconPlusCount == 10) {
            System.out.println("The first page has 10 data, test pass!");
        }
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//span[@class='k-icon k-i-arrow-s']"));
        action.sendKeys(Keys.DOWN).perform();
        action.sendKeys(Keys.ENTER).perform();
        CommonWebDriver.wait(driver, 3);
        //Switch to page 2
        List<WebElement> iconPlusList2 = CommonWebDriver.getElements(driver, By.xpath(".//*[@id='container']//div[contains(@style,'visible')]//a[@class='js_add']/i"), 3);
        int iconPlusCount2 = iconPlusList2.size();
        String inputText = CommonWebDriver.getElement(driver, By.xpath("//input[@class='k-input']")).getAttribute("value");
        if (iconPlusCount2 >= 1 && inputText.equals("2")) {
            System.out.println("The second page has more than one data, switch page succeed, test pass!");
        } else {
            Assert.fail("The switch function does not work well, test fail");
        }
    }

    @AfterTest
    public void tearDown() {
        //delete the newly added 10 test data to keep test env clean
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews1.getId());
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews2.getId());
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews3.getId());
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews4.getId());
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews5.getId());
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews6.getId());
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews7.getId());
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews8.getId());
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews9.getId());
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews10.getId());
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews11.getId());
        driver.quit();
    }
}
