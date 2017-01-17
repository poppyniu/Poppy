package WB2CPages;

import WB2CCommon.CommonWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AutoResponsePage {
    public static void CreateAutoResponse(WebDriver driver) throws Exception {
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='keywords']"), "hello");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='wxAutoMsgForm']/ul/li[2]/span/span/span/span"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='matchMode_option_selected']"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='content']"), "hello");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
    }
}
