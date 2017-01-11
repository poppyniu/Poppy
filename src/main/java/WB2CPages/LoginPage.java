package WB2CPages;

import WB2CCommon.CommonWebDriver;
import WB2CCommon.PageObject;
import WB2CConstants.LoginConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageObject {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginWithValidCredential(String brandCode, String userName, String password) throws Exception {
        //clear brandcode text box if needed
        CommonWebDriver.clearTextbox(driver, By.xpath(LoginConstants.brandCode_input_xpath));
        // input brandcode
        CommonWebDriver.sendKeysToElement(driver, By.xpath(LoginConstants.brandCode_input_xpath), brandCode,
                LoginConstants.brandCode_input_desc);
        CommonWebDriver.wait(driver, 1);

        //clear username text box if needed
        CommonWebDriver.clearTextbox(driver, By.xpath(LoginConstants.username_input_xpath));
        // input username
        CommonWebDriver.sendKeysToElement(driver, By.xpath(LoginConstants.username_input_xpath), userName,
                LoginConstants.username_input_desc);
        CommonWebDriver.wait(driver, 1);

        //clear password text box if needed
        CommonWebDriver.clearTextbox(driver, By.xpath(LoginConstants.password_input_xpath));
        // input password
        CommonWebDriver.sendKeysToElement(driver, By.xpath(LoginConstants.password_input_xpath), password,
                LoginConstants.password_input_desc);
        CommonWebDriver.wait(driver, 1);

        // click sign in button
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(LoginConstants.login_button_xpath),
                LoginConstants.login_button_desc);
        CommonWebDriver.wait(driver, 4);
    }

}
