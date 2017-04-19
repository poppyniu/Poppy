package WB2C.UIAutomation;

import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CCommon.ImageTextMaterialUtil;
import WB2CCommon.TestNGListener;
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
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

@Listeners({ TestNGListener.class })
public class ImageTextEdit extends CommonWebDriver{
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private ImageTextPage imageTextPage = new ImageTextPage();
    private ImageTextMaterialUtil imageTextMaterialUtil = new ImageTextMaterialUtil();
    private MaterialNews materialNews;

    @Parameters({"browser"})
    @Test
    public void editImageText(String testNGBrowser) throws Exception {
        String testDataPath1 = System.getProperty("testdata1");
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
        //prepare test data
        materialNews = imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver, 2);
        //edit the new created test data
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//a[@id='btnRefresh']"));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath("//input[@id='searchText']"), "test title");
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//div[@id='container']/div[3]/div/div[3]/ul/li[2]/a/i"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clearTextbox(driver, By.xpath("//input[@id='title']"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='title']"), "edit title", 1);
        CommonWebDriver.clearTextbox(driver, By.xpath("//input[@id='author']"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='author']"), "edit author", 1);
        CommonWebDriver.wait(driver, 2);
        JavascriptExecutor removeAttribute = (JavascriptExecutor)driver;
        removeAttribute.executeScript("document.getElementsByClassName('k-widget k-upload k-header k-upload-empty')[0].style.display='block';", new Object[0]);
        CommonWebDriver.wait(driver, 3);
        String testDataPath = System.getProperty("testdata1");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(testDataPath);
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clearTextbox(driver, By.xpath("//textarea[@id='summary']"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//textarea[@id='summary']"), "edit summary", 1);
        CommonWebDriver.clearTextbox(driver, By.xpath("//input[@id='url']"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='url']"), "https://www.baidu.com", 1);
        CommonWebDriver.clickElement(driver, By.xpath("//div[@id='tabs']/ul/li[2]/a"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clearTextbox(driver, By.xpath("//div[@id='myEditor']"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//div[@id='myEditor']"), "edit editor", 1);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 5);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath("//img[@src='/WCPPRO/api/file/3/test1.jpg']"))) {
            System.out.println("Edit text image succeed, test pass! ");
            imageTextMaterialUtil.DeleteTextImageMaterial(materialNews.getId());
        }
    }

    @AfterTest
    public void tearDown() {
        //delete the newly added test data to keep test env clean
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews.getId());
        driver.quit();
    }
}
