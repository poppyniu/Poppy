package WB2C.UIAutomation;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import WB2CCommon.CommonUtil;
import WB2CCommon.ImageTextMaterialUtil;
import WB2CConstants.*;
import com.domain.wx.MaterialNews;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import WB2CCommon.CommonWebDriver;
import WB2CPages.LoginPage;
import WB2CPages.ImageTextPage;

public class ImageTextEdit {

    private WebDriver driver;
    private LoginPage loginPage;
    private TestAccounts testaccounts;
    private DesiredCapabilities desiredCapabilities;
    private ImageTextPage imageTextPage = new ImageTextPage();
    private ImageTextMaterialUtil imageTextMaterialUtil=new ImageTextMaterialUtil();
    private MaterialNews materialNews;

    @Parameters({"browser"})
    @Test
    public void EditImageText(String testNGBrowser) throws Exception {
        String testDataPath1=System.getProperty("testdata1");
        desiredCapabilities = CommonUtil.getBrowser(testNGBrowser);
        String url = NodeIPConstants.windows_Node1_ip + "/wd/hub";
        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
        loginPage = new LoginPage(driver);
        testaccounts = new TestAccounts();
        CommonWebDriver.get(driver, URLConstants.loginPageUrl);
        driver.manage().window().maximize();
        loginPage.loginWithValidCredential(TestAccounts.testbrandcode,
                TestAccounts.testusername, TestAccounts.testuserpwd);
        CommonWebDriver.navigateAndLoadPage(driver,URLConstants.homePageUrl,3);
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.wechat_management_xpath));
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.wechat_text_image_xpath));
        CommonWebDriver.wait(driver,4);
        CommonWebDriver.switchToFrame(driver,By.xpath("//iframe[@id='materialNewsMgmtFrame']"));
        //prepare test data
        materialNews=imageTextMaterialUtil.createTextImageMaterial();
        CommonWebDriver.wait(driver,2);
        //edit the new created test data
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath("//a[@id='btnRefresh']"));
        CommonWebDriver.wait(driver,4);
        CommonWebDriver.sendKeysWithEnterToElement(driver,By.xpath("//input[@id='searchText']"),"test title");
        CommonWebDriver.wait(driver,2);
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath("//div[@id='container']/div[3]/div/div[3]/ul/li[2]/a/i"));
        CommonWebDriver.wait(driver,2);
        CommonWebDriver.clearTextbox(driver, By.xpath("//input[@id='title']"));
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//input[@id='title']"),"edit title",1);
        CommonWebDriver.clearTextbox(driver, By.xpath("//input[@id='author']"));
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//input[@id='author']"),"edit author",1);
        CommonWebDriver.clickElement(driver,By.xpath("//img[@id='image']"));
        CommonWebDriver.wait(driver,3);
        CommonUtil.setClipboardData(testDataPath1);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        CommonWebDriver.wait(driver,1);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        CommonWebDriver.wait(driver,3);
        CommonWebDriver.clearTextbox(driver, By.xpath("//textarea[@id='summary']"));
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//textarea[@id='summary']"),"edit summary",1);
        CommonWebDriver.clearTextbox(driver, By.xpath("//input[@id='url']"));
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//input[@id='url']"),"https://www.baidu.com",1);
        CommonWebDriver.clickElement(driver,By.xpath("//div[@id='tabs']/ul/li[2]/a"));
        CommonWebDriver.wait(driver,2);
        CommonWebDriver.clearTextbox(driver, By.xpath("//div[@id='myEditor']"));
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//div[@id='myEditor']"),"edit editor",1);
        CommonWebDriver.clickElement(driver,By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver,5);
        if(CommonWebDriver.isElementDisplayed(driver,By.xpath("//img[@src='/WCPPRO/api/file/5/test1.jpg']")))
        {
            System.out.println("Edit text image succeed, test pass! ");
        }
    }

    @AfterTest
    public void tearDown() {
        //delete the newly added test data to keep test env clean
        imageTextMaterialUtil.DeleteTextImageMaterial(materialNews.getId());
        driver.quit();
    }
}
