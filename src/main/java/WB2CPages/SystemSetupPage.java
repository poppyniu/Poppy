package WB2CPages;

import WB2CCommon.CommonUtil;
import WB2CCommon.CommonWebDriver;
import WB2CConstants.NodeIPConstants;
import WB2CConstants.SideMenuConstants;
import WB2CConstants.TestAccounts;
import WB2CConstants.URLConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.List;

/**
 * Created by Joe.Liu on 2017/1/9.
 */
public class SystemSetupPage {

    public static void UserMngCreate(WebDriver driver, String inputValue) throws Exception {

        CommonWebDriver.clickElementWhenPresent(driver,
                By.xpath(SideMenuConstants.system_setup_xpath));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(SideMenuConstants.system_setup_user_management));
        CommonWebDriver.wait(driver, 4);
        CommonWebDriver.switchToFrame(driver, By.xpath("//iframe[@id='userMgmtFrame']"));
        CommonWebDriver.clickElement(driver, By.xpath("//a[@id='btnNew']"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='username']"), inputValue);
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//*[@id='nickname']"), inputValue);
        CommonWebDriver.sendKeysToElement(driver,By.xpath(".//form[@id='userForm']/ul/li/input[@id='password']"),"pass");
        CommonWebDriver.sendKeysToElement(driver, By.xpath(".//form[@id='userForm']/ul/li/input[@id='repassword']"), "pass");
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='userForm']/ul/li[7]/span/span[1]/span/span"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='userType_listbox']/li[2]"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='userForm']/ul/li[8]/span/span[1]/span/span"));
        CommonWebDriver.wait(driver, 2);
        CommonWebDriver.clickElement(driver, By.xpath(".//*[@id='userGroupId_listbox']/li[2]"));
        CommonWebDriver.wait(driver, 1);
        CommonWebDriver.clickElementWhenPresent(driver,By.xpath(".//*[@id='btnSave']"));
        CommonWebDriver.wait(driver, 2);



    }
}

