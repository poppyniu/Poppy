package WB2CPages;

import WB2CCommon.CommonWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomMenuPage {

    public static void CreateFirstLevelMenu(WebDriver driver) throws Exception {
        //calculate the current table rows count
        WebElement table = driver.findElement(By.xpath("//table[@class='k-selectable']"));
        java.util.List<WebElement> rows = table.findElements(By.tagName("tr"));
        int oldTableCount = rows.size();
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='name']"), "test menu");
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='event']"), "test key");
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(".//*[@id='form']/ul/li[3]/span/span/span/span"));
        CommonWebDriver.clickElement(driver, By.xpath("//ul[@id='type_listbox']/li[3]"));
        CommonWebDriver.sendKeysToElement(driver, By.xpath("//input[@id='text']"), "test response");
        CommonWebDriver.clickElement(driver, By.xpath("//form[@id='form']/ul/li[8]/span/span/span/span"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElement(driver, By.xpath("//ul[@id='state_listbox']/li[1]"));
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);

    }
}
