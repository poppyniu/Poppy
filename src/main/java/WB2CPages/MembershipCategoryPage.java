package WB2CPages;

import WB2CCommon.CommonWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MembershipCategoryPage {
    public static void CreateMembershipCategory(WebDriver driver,String categoryName) throws Exception {
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='name']"), categoryName);
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='description']"), "test description");
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);
    }
}
