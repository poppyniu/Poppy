package WB2CPages;

import WB2CCommon.CommonWebDriver;
import WB2CConstants.SideMenuConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class ImageTextPage {

    public void CreateImageTextMaterial(WebDriver driver) throws Exception {
        String testDataPath = System.getProperty("testdata");
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.wechat_management_xpath));
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.wechat_text_image_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='materialNewsMgmtFrame']"));
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='title']"), "test title", 1);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='author']"), "test author", 1);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(testDataPath);
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//textarea[@id='summary']"), "test summary", 1);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='url']"), "https://www.baidu.com", 1);
        CommonWebDriver.clickElement(driver, By.xpath("//div[@id='tabs']/ul/li[2]/a"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//div[@id='myEditor']"), "test editor", 1);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath("//img[@src='/WCPPRO/api/file/5/test.jpg']"))) {
            System.out.println("Create new text image material succeed, test pass! ");
        }
        else
            Assert.fail("Create new image test get error, test fail!");

    }

    public void CreateSecondaryImageTextMaterial(WebDriver driver) throws Exception {
        String testDataPath = System.getProperty("testdata1");
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='title']"), "test title", 1);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='author']"), "test author", 1);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(testDataPath);
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
    }
}
