package WB2CPages;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import WB2CCommon.CommonUtil;
import WB2CCommon.PageObject;
import WB2CConstants.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import WB2CCommon.CommonWebDriver;


public class ImageTextPage  {

    public void CreateImageTextMaterial(WebDriver driver) throws Exception
    {
        String testDataPath=System.getProperty("testdata");
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.wechat_management_xpath));
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.wechat_text_image_xpath));
        CommonWebDriver.wait(driver,4);
        CommonWebDriver.switchToFrame(driver,By.xpath("//iframe[@id='materialNewsMgmtFrame']"));
        CommonWebDriver.clickElement(driver,By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver,2);
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//input[@id='title']"),"test title",1);
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//input[@id='author']"),"test author",1);
        CommonWebDriver.clickElement(driver,By.xpath("//img[@id='image']"));
        CommonWebDriver.wait(driver,3);
        CommonUtil.setClipboardData(testDataPath);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        CommonWebDriver.wait(driver,2);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        CommonWebDriver.wait(driver,3);
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//textarea[@id='summary']"),"test summary",1);
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//input[@id='url']"),"https://www.baidu.com",1);
        CommonWebDriver.clickElement(driver,By.xpath("//div[@id='tabs']/ul/li[2]/a"));
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//div[@id='myEditor']"),"test editor",1);
        CommonWebDriver.clickElement(driver,By.xpath("//a[@id='btnSave']"));
        if(CommonWebDriver.isElementDisplayed(driver,By.xpath("//img[@src='/WCPPRO/api/file/5/test.jpg']")))
        {
            System.out.println("Create new text image material succeed, test pass! ");
        }

    }

    public void CreateSecondaryImageTextMaterial(WebDriver driver) throws Exception
    {
        String testDataPath=System.getProperty("testdata1");
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//input[@id='title']"),"test title",1);
        CommonWebDriver.sendKeysToElement(driver,By.xpath("//input[@id='author']"),"test author",1);
        CommonWebDriver.clickElement(driver,By.xpath("//img[@id='image']"));
        CommonWebDriver.wait(driver,3);
        CommonUtil.setClipboardData(testDataPath);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        CommonWebDriver.wait(driver,2);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        CommonWebDriver.wait(driver,3);
        CommonWebDriver.clickElement(driver,By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver,2);
    }
}
