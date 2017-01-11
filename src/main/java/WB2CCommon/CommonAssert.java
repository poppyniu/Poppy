package WB2CCommon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class CommonAssert extends Assert {


    /**
     * Check whether element is exist by condition parameter
     *
     * @param condition The condition to evaluate
     * @param message   The error message that will be printed when assert error occurs
     * @param stepNum   The step number in test case
     */
    public static void verifyElementExist(boolean condition, String message, int stepNum) {
        Assert.assertTrue(condition, packMsg(message, stepNum));
    }

    /**
     * Check whether element is not exist
     *
     * @param condition The condition to evaluate
     * @param message   The error message that will be printed when assert error occurs
     * @param stepNum   The step number in test case
     */
    public static void verifyElementNotExist(boolean condition, String message, int stepNum) {
        Assert.assertFalse(condition, packMsg(message, stepNum));
    }

    /**
     * Asserts that two booleans are equal
     *
     * @param message  The error message that will be printed when assert error occurs
     * @param actual   The actual boolean value
     * @param expected The expected boolean value
     * @param stepNum  The step number in test case
     */
    public static void assertEquals(String message, boolean actual, boolean expected, int stepNum) {
        Assert.assertEquals(actual, expected, packMsg(message, stepNum));
    }

    /**
     * Asserts that two Strings are equal
     *
     * @param message
     * @param actualText
     * @param expectedText
     * @param stepNum
     */
    public static void assertEquals(String message, String actualText, String expectedText, int stepNum) {
        Assert.assertEquals(actualText, expectedText, packMsg(message, stepNum));
    }

    /**
     * Asserts that a condition is true. If it isn't,
     * an AssertionError, with the given message, is thrown.
     *
     * @param message   the assertion error message
     * @param condition the condition to evaluate
     * @param stepNum   the step number in test case
     */
    public static void assertTrue(String message, boolean condition, int stepNum) {
        Assert.assertTrue(condition, packMsg(message, stepNum));
    }

    /**
     * Asserts that a condition is false. If it isn't,
     * an AssertionError, with the given message, is thrown.
     *
     * @param message   the assertion error message
     * @param condition the condition to evaluate
     * @param stepNum   the step number in test case
     */
    public static void assertFalse(String message, boolean condition, int stepNum) {
        Assert.assertFalse(condition, packMsg(message, stepNum));
    }

    public static void verifyElementIsDisplayed(WebElement e, String elementName, int stepNum) {
        String message = "";
        if (e == null) {
            message = elementName + " does not exist.";
            Assert.fail(packMsg(message, stepNum));
        } else {
            message = elementName + " is not displayed.";
            Assert.assertTrue(e.isDisplayed(), packMsg(message, stepNum));
        }
    }

    public static void verifyElementIsDisplayed(WebDriver driver, By by, String elementName, int stepNum) {
        String message = elementName + " is not displayed.";
        Assert.assertTrue(CommonWebDriver.isElementDisplayedAndEnabled(driver, by), packMsg(message, stepNum));
    }

    public static void verifyElementIsNotDisplayed(WebElement e, String elementName, int stepNum) {
        String message = "";
        if (e == null) {
            message = elementName + " does not exist.";
            Assert.assertTrue(true, packMsg(message, stepNum));
        } else {
            message = elementName + " is not expected to be displayed!";
            Assert.assertFalse(e.isDisplayed(), packMsg(message, stepNum));
        }
    }

    public static void verifyElementIsEnabled(WebElement e, String elementName, int stepNum) {
        String message = "";
        if (e == null) {
            message = elementName + " does not exist.";
            Assert.fail(packMsg(message, stepNum));
        } else {
            message = elementName + " is not enabled.";
            Assert.assertTrue(e.isEnabled(), packMsg(message, stepNum));
        }
    }

    public static void verifyElementIsNotEnabled(WebElement e, String elementName, int stepNum) {
        String message = "";
        if (e == null) {
            message = elementName + " does not exist.";
            Assert.fail(packMsg(message, stepNum));
        } else {
            message = elementName + " is not expected to be enabled.";
            Assert.assertFalse(e.isEnabled(), packMsg(message, stepNum));
        }
    }

    public static void verifyElementIsSelected(WebElement e, String elementName, int stepNum) {
        String message = "";
        if (e == null) {
            message = elementName + " does not exist.";
            Assert.fail(packMsg(message, stepNum));
        } else {
            message = elementName + " is not selected.";
            Assert.assertTrue(e.isSelected(), packMsg(message, stepNum));
        }
    }


    public static void verifyTextContainsTextIgnoreCase(String message,
                                                        String actualText, String expectedPartialText, int stepNum) {

        Assert.assertTrue(actualText.toLowerCase().trim().contains(expectedPartialText.toLowerCase().trim()),
                packMsg(message, stepNum));
    }


    /**
     * Wrap up message with step number
     *
     * @param message
     * @param stepNum
     * @return
     */
    private static String packMsg(String message, int stepNum) {
        if (stepNum > 0) {
            message = "On step " + stepNum + ", " + message;
        }
        return message;
    }

}
