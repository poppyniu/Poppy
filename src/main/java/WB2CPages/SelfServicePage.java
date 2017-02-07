package WB2CPages;

import WB2CCommon.CommonWebDriver;
import WB2CConstants.SideMenuConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Joe.Liu on 2016/12/21.
 */
public class SelfServicePage {
    public void CreateSelfServiceMaterial(WebDriver driver) throws Exception {
        String testDataPath = System.getProperty("testdata").toString();
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.customer_service_xpath));
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.self_service_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='intelligenceMenuMgmtFrame']"));
        CommonWebDriver.clickElement(driver, By.xpath(".//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='menuKey']"), "3", 1);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='name']"), "menu 3", 1);
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath("//div[@id='window']/form/ul/li[4]/span/span/span/span"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath("//li[@id='parentId_option_selected']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        String textboxValue = CommonWebDriver.getElement(driver, By.xpath("//div[@id='grid']/table/tbody/tr[5]/td[4]")).getText();
        if (textboxValue.equals("menu 3")) {
            System.out.println("The item is created successfully, test pass");
        }

//        CommonWebDriver.clickElement(driver,By.xpath("//img[@id='image']"));
//        CommonWebDriver.wait(driver,3);
//        CommonUtil.setClipboardData(testDataPath);
//        Robot robot=new Robot();
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        CommonWebDriver.wait(driver,2);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//        CommonWebDriver.wait(driver,3);
//        CommonWebDriver.sendKeysToElement(driver,By.xpath("//textarea[@id='summary']"),"test summary",1);
//        CommonWebDriver.sendKeysToElement(driver,By.xpath("//input[@id='url']"),"https://www.baidu.com",1);
//        CommonWebDriver.clickElement(driver,By.xpath("//div[@id='tabs']/ul/li[2]/a"));
//        CommonWebDriver.sendKeysToElement(driver,By.xpath("//div[@id='myEditor']"),"test editor",1);
//        CommonWebDriver.clickElement(driver,By.xpath("//a[@id='btnSave']"));
//        if(CommonWebDriver.isElementDisplayed(driver,By.xpath("//img[@src='/WCPPRO/api/file/5/test.jpg']")))
//        {
//            System.out.println("Create new text image material succeed, test pass! ");
//        }

    }

    public void InvalidCreateSelfServiceMaterial(WebDriver driver) throws Exception {
        String testDataPath = System.getProperty("testdata").toString();
        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.customer_service_xpath));
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.self_service_xpath));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='intelligenceMenuMgmtFrame']"));
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='window']/form/ul/li[1]/span")) &
                CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='window']/form/ul/li[2]/span"))) {
            System.out.println("Do not input title see the correct warning message, test pass! ");
        }
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='menuKey']"), "test menuKey", 1);

        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
        if (CommonWebDriver.isElementDisplayed(driver, By.xpath(".//*[@id='window']/form/ul/li[2]/span"))) {
            System.out.println("Do not input title see the correct warning message, test pass! ");
        }
//        CommonWebDriver.sendKeysToElement(driver,By.xpath("//input[@id='name']"),"test title",1);
//        CommonWebDriver.clickElement(driver,By.xpath("//a[@id='btnSave']"));
//        CommonWebDriver.wait(driver,2);
//
//        Alert alert=driver.switchTo().alert();
//        String alertText=alert.getText().toString();
//        if(alertText.toString().contains("创建失败"))
//        {
//            System.out.println("Do not upload cover image see the correct dialog, test pass! ");
//        }
//        else
//            CommonAssert.fail("Do not upload cover image does not see the correct dialog, test fail!");
//
//    }
    }
}
