package WB2CCommon;

import WB2CConstants.AppConstants;
import WB2CConstants.FormConstants;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    public static void printLog(String text) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        if (AppConstants.LOG_STEP) {
            Reporter.log(text + "<br>");
        }
        logger.info(text);

    }

    /**
     * Will take screenshot.
     *
     * @param driver  WebDriver on a page, or WebElement on a page.
     * @param dirName The file path to save screenshot(s).
     * @return screenshot Path
     * @throws Exception
     */
    public static String takeScreenShot(WebDriver driver, String testName, String dirName) throws Exception {
        if (!(new File(dirName).isDirectory())) {
            new File(dirName).mkdir();
        }

        String time = getDataTimeString("yyyyMMdd-HHmmss");
        String screenshotPath = dirName + File.separator + testName + "-" + time + ".png";

        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(screenshotPath));

        } catch (Exception ex) {
            logger.info(ex.toString());
            throw new CommonException("Fail to take screenshot.", ex);
        }
        return screenshotPath;
    }

    /**
     * Find an iframe element in another iframe
     *
     * @param driver
     * @param iframeBy
     * @return
     * @throws Exception
     */
    public static WebElement findIframe(WebDriver driver, By iframeBy) {
        WebElement retElement = null;
        try {
            List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));

            for (WebElement iframeEle : iframeList) {
                driver.switchTo().frame(iframeEle);
                if (CommonUtil.isExist(driver, iframeBy)) {
                    retElement = iframeEle;
                }
                driver.switchTo().defaultContent();
            }
        } catch (Exception ex) {
            retElement = null;
        }
        return retElement;
    }

    /**
     * Judge if element exist
     *
     * @param driver
     * @param by
     * @return
     */
    public static boolean isExist(WebDriver driver, By by) {
        try {
            WebElement element = driver.findElement(by);
            if (element.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            ;
        }
        return false;
    }

    /**
     * Get all rows of specified column
     *
     * @param driver
     * @param tableBy
     * @param column
     * @return
     */
    public static List<WebElement> getCellElementsByColumn(WebDriver driver, By tableBy, int column) {
        List<WebElement> elements = new ArrayList<WebElement>();
        if (isExist(driver, tableBy)) {
            WebElement table = driver.findElement(tableBy);
            List<WebElement> rows = table.findElements(By.xpath("./tr"));
            for (WebElement row : rows) {
                WebElement cell = getCell(row, column);
                elements.add(cell);
            }

        }
        return elements;
    }


    public static int getTableColumnNumber(WebDriver driver, By by, String headerName) {
        int column = 0;
        boolean exist = false;
        List<WebElement> elements = driver.findElements(by);

        for (WebElement header : elements) {
            column++;
            if (header.getText().equals(headerName)) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            column = 0;
            //throw new CommonException("Unable to find target column with header name '" + headerName + "'");
        }
        return column;
    }


    /**
     * Get specified cell element
     *
     * @param row
     * @param cell Column number the element you want to find
     * @return
     */
    private static WebElement getCell(WebElement row, int cell) {
        List<WebElement> cells;
        WebElement target = null;

        cell = cell - 1;
        if (row.findElements(By.tagName("th")).size() > 0) {
            cells = row.findElements(By.tagName("th"));
            target = cells.get(cell);
        }
        if (row.findElements(By.tagName("td")).size() > 0) {
            cells = row.findElements(By.tagName("td"));
            target = cells.get(cell);
        }
        return target;
    }


    /**
     * when open a new window, switch to new window
     *
     * @return return original window
     */
    public static String switchToNewWindow(WebDriver driver) throws Exception {
        String currentHandle = driver.getWindowHandle();
        int index = 0;
        try {
            for (String winHandle : driver.getWindowHandles()) {
                index++;
                if (index > 1) {
                    driver.switchTo().window(winHandle);
                }
                //logger.info("title:"+driver.getTitle());
            }
        } catch (Exception e) {
            throw new CommonException("Fail to witch to new window", e);
        }
        return currentHandle;
    }

    /**
     * switch back to parent window
     *
     * @param driver
     * @param parentWindow
     */
    public static void switchWindowBack(WebDriver driver, String parentWindow) throws Exception {
        try {
            // close the new window
            driver.close();
            // Switch back to original browser (first window)
            driver.switchTo().window(parentWindow);
        } catch (Exception e) {
            throw new CommonException("Fail to witch back to parent window", e);
        }
    }

    /**
     * Use AutoIt to handle file choose dialog
     *
     * @param fileToOpen Full file name with path that will be upload
     * @throws Exception
     */
//	public static void handleOpenFileDialog(String fileToOpen) throws Exception {
//		//String userDir = System.getProperty("user.dir");
//		//File pathOfFileOpenTool = new File(userDir + "\\tools\\HandleOpenFileDialog.exe");
//		File pathOfFileOpenTool = new File(Helper.getSourceFilePath() + "\\tools\\HandleOpenFileDialog.exe");
//		logger.info("Handle open file dialog tool path: " + pathOfFileOpenTool.toString());
//		Runtime.getRuntime().exec(pathOfFileOpenTool + " " + fileToOpen);
//		Thread.sleep(3000);
//	}	

    /**
     * Use AutoIt to handle download dialog to download file to default location
     *
     * @throws Exception
     */
//	public static void handleDownloadDialog() throws Exception {
//		String pathOfDownloadFileTool = Helper.getSourceFilePath() + "\\tools\\HandleDownloadDialog.exe";
//		logger.info("Handle open file dialog tool path: " + pathOfDownloadFileTool);
//		Runtime.getRuntime().exec(pathOfDownloadFileTool);
//		Thread.sleep(2000);
//	}

    /**
     * Return download directory that save file into
     *
     * @return
     */
    public static String getDownloadDir() {
        File downloadDir = new File(System.getProperty("java.io.tmpdir") + File.separator + "k2download");
        if (!downloadDir.exists()) {
            downloadDir.mkdir();
        }
        return downloadDir.toString();
    }


    /**
     * Generate txt/doc file to system temp folder
     *
     * @param fileType File's postfix
     * @return
     * @throws Exception
     */
    public static String createTempDocument(String fileType) throws Exception {
        OutputStreamWriter pw = null;
        String input = "123456789abc";
        String fileName = "TEST_" + getDataTimeString("yyyyMMddHHmmss") + "." + fileType;
        String filePath = System.getProperty("java.io.tmpdir") + fileName;

        try {
            pw = new OutputStreamWriter(new FileOutputStream(filePath), "GBK");
            pw.write(input);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return filePath;
    }

    public static String getDataTimeString(String format) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(format);
        String dateTimeString = dateTimeFormat.format(new java.util.Date());
        return dateTimeString;
    }

    public static String getARandomString(int length) {
        String baseString = "1234567890`-=qwertyuiop[]asdfghjkl;'zxcvbnm,./~!@#$%^&*()_+" +
                "QWERTYUIOP{}|ASDFGHJKL:ZXCVBNM<>?";
        Random rand = new Random();
        String randomString = "";
        for (int i = 0; i < length; i++) {
            char randomCharacter = baseString.charAt(rand.nextInt(baseString.length()));
            randomString += randomCharacter;
        }
        return randomString;
    }

    public static int getARandomNumber(int maxValue) throws Exception {
        Random r = new Random();
        int randomNumber = Math.abs(r.nextInt(maxValue));
        return randomNumber;
    }

    public static String getARandomNumericString(int length) {
        String baseString = "1234567890";
        Random rand = new Random();
        String randomString = "";
        for (int i = 0; i < length; i++) {
            char randomCharacter = baseString.charAt(rand.nextInt(baseString.length()));
            randomString += randomCharacter;
        }
        return randomString;
    }

    /**
     * Generate a docx document to system temp folder
     *
     * @return
     * @throws Exception
     */
    public static String createTempDocxFile() throws Exception {
        FileOutputStream out = null;
        String input = "hello world!";
        String fileName = "TEST_" + getDataTimeString("yyyyMMddHHmmss") + ".docx";
        String filePath = System.getProperty("java.io.tmpdir") + fileName;

        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph p1 = doc.createParagraph();
        XWPFRun r1 = p1.createRun();
        r1.setText(input);
        try {
            out = new FileOutputStream(filePath);
            doc.write(out);
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return filePath;
    }


    /**
     * Delete file or folder
     *
     * @param file
     * @throws Exception
     */
    public static void deleteFile(File file) throws Exception {
        if (file.isDirectory()) {
            String fileList[] = file.list();
            if (fileList.length == 0) {
                file.delete();
            } else {
                int size = fileList.length;
                for (int i = 0; i < size; i++) {
                    String fileName = fileList[i];
                    String fullPath = file.getPath() + "/" + fileName;
                    File fileOrFolder = new File(fullPath);
                    deleteFile(fileOrFolder);
                }
            }
        } else {
            file.delete();
        }
    }

    /**
     * Refresh page if specified element is not displaying, refresh 3 times at mostly
     * This method is not applicable for refresh a page to wait for an element inside in iframe
     *
     * @param driver
     * @param by     By object of the element you want to find
     */
    public static boolean refreshPageIfElementIsNotDisplay(WebDriver driver, By by) {
        boolean isDisplay = CommonWebDriver.isElementDisplayedAndEnabled(driver, by);
        int count = 0;

        while (!isDisplay && count < 4) {
            CommonWebDriver.wait(driver, 4);
            driver.navigate().refresh();
            count++;
            isDisplay = CommonWebDriver.isElementDisplayedAndEnabled(driver, by);
        }
        return isDisplay;
    }

    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
        CommonWebDriver.wait(driver, 2);
    }

    /**
     * Get target item's row number in a table body section
     *
     * @param tableBy
     * @param name
     * @param column  Document name column number located in the document table
     * @return target document row number in document view
     */
    public static int findTargetItemRowNum(WebDriver driver, By tableBy, String name, int column) {
        int row = 0;
        boolean exist = false;
        List<WebElement> docNameList = CommonUtil.getCellElementsByColumn(driver, tableBy, column);
        for (WebElement docItem : docNameList) {
            row++;
//        	System.out.println("docItem.getText():"+docItem.getText());
            if (docItem.getText().equals(name)) {
                exist = true;
                break;
            }
        }

        return exist ? row : 0;
    }

    public static int findTargetItemRowNum(WebDriver driver, By tableBodyBy, By tableHeaderBy, String itemName, String headerName) {
        int row = 0;
        boolean exist = false;

        int column = CommonUtil.getTableColumnNumber(driver, tableHeaderBy, headerName);

        List<WebElement> itemNameList = CommonUtil.getCellElementsByColumn(driver, tableBodyBy, column);
        for (WebElement item : itemNameList) {
            row++;
            if (item.getText().equals(itemName)) {
                exist = true;
                break;
            }
        }

        return exist ? row : 0;
    }


    /**
     * Fill in lookup field (e.g. a person's name)
     *
     * @param driver
     * @param fieldXpath
     * @param value
     * @throws Exception
     */
    public static void fillInLookupField(WebDriver driver, String fieldXpath, String value) throws Exception {
        //input a lookup field
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(fieldXpath), value);
        CommonWebDriver.wait(driver, 4);
        //wait for field to be resolved, there will be ; character appended when resolved
        if (!CommonUtil.isExist(driver, By.xpath(fieldXpath + "//span[@class='resolved']"))) {
            CommonWebDriver.wait(driver, 4);
        }
    }

    /**
     * Fill in lookup field (e.g. a person's name)
     *
     * @param driver
     * @param fieldXpath
     * @param value
     * @param fieldDesc
     * @throws Exception
     */
    public static void fillInLookupField(WebDriver driver, String fieldXpath, String value, String fieldDesc) throws Exception {
        //input a lookup field
        CommonWebDriver.sendKeysWithEnterToElement(driver, By.xpath(fieldXpath), value, fieldDesc);
        CommonWebDriver.wait(driver, 4);
        //wait for field to be resolved, there will be ; character appended when resolved
        if (!CommonUtil.isExist(driver, By.xpath(fieldXpath + "//span[@class='resolved']"))) {
            CommonWebDriver.wait(driver, 4);
        }
    }

    public static void waitPageLoad(WebDriver driver) {
        int count = 0;
        while (isExist(driver, By.xpath("//img[contains(@src,'loading')]")) && count < 4) {
            CommonWebDriver.wait(driver, 5);
            count++;
        }
    }

    /**
     * Get file name without postfix
     *
     * @param fileNameWithPostfix
     * @return file name without postfix
     */
    public static String getFileNameWithoutPostfix(String fileNameWithPostfix) {
        String ret = "";
        if (fileNameWithPostfix != null && !fileNameWithPostfix.equals("")) {
            int lastIndex = fileNameWithPostfix.lastIndexOf(".");
            if (lastIndex < 0) {
                return ret;
            }
            ret = fileNameWithPostfix.substring(0, lastIndex);
        }
        return ret;
    }

    /**
     * Click "OK" or "Cancel" on alert dialog
     *
     * @param driver
     * @param dismiss If true then click cancel button, otherwise then click OK button
     */
    public static void closeAlert(WebDriver driver, boolean dismiss) {
        try {
            Alert alert = driver.switchTo().alert();
            if (null == alert) {
                throw new NoAlertPresentException();
            }
            if (dismiss) {
                alert.dismiss();
            } else {
                alert.accept();
            }
        } catch (NoAlertPresentException e) {
            if (!dismiss) {
                throw new CommonException("There is no alert appears.", e);
            }
        }
    }


    /**
     * Execute specified java script
     *
     * @param driver
     * @param scriptString
     */
    public static void executeJavaScript(WebDriver driver, String scriptString) {
        ((JavascriptExecutor) driver).executeScript(scriptString);
        CommonWebDriver.wait(driver, 2);
    }

    /**
     * Scroll the scroll bar to specified position
     *
     * @param driver
     * @param height
     */
    public static void setScroll(WebDriver driver, int height) {
        String setscroll = "document.documentElement.scrollTop=" + height;
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(setscroll);
        CommonWebDriver.wait(driver, 2);
    }

    /**
     * Move scroll bar to specified element
     *
     * @param driver
     * @param element
     */
    public static void moveToElement(WebDriver driver, WebElement element) {
        if (element != null) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            CommonWebDriver.wait(driver, 2);
        }
    }

    /**
     * Move scroll bar to specified element
     *
     * @param driver
     * @param by
     */
    public static void moveToElement(WebDriver driver, By by) {
        WebElement element = CommonWebDriver.getElement(driver, by);
        if (element != null) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            CommonWebDriver.wait(driver, 1);
        }
    }

    /**
     * Current date add or minus specified days
     *
     * @param days         After or before these days from today
     * @param formatString Date format string(e.g. "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "MM/dd/yyyy")
     * @return
     */
    public static String dateAddDays(int days, String formatString) {
        String dateString = null;
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat(formatString);

        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        //c.add(c.YEAR, 1);
        c.add(c.DATE, days);

        Date temp_date = c.getTime();
        dateString = format.format(temp_date);
        return dateString;
    }

    /**
     * Current date add or minus specified months
     *
     * @param months
     * @param formatString Date format string(e.g. "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "MM/dd/yyyy")
     * @return
     */
    public static String dateAddMonths(int months, String formatString) {
        String dateString = null;
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat(formatString);

        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.add(c.MONDAY, months);

        Date temp_date = c.getTime();
        dateString = format.format(temp_date);
        return dateString;
    }

    /**
     * Convert string to expected date, if input value cannot be converted, will return it's original value
     *
     * @param value  String will be converted e.g. "Today+1", "Today-1", "Today"
     * @param format Date formate string e.g. "yyyy/MM/dd", "MM/dd/yyyy"
     * @return
     */
    public static String convertDateToExpectedString(String value, String format) {
        String ret = "";
        if (value == null || value.equals("")) {
            return value;
        }
        value = value.trim().toLowerCase();
        try {
            value = value.trim();
            if (value.equals("today")) {
                ret = CommonUtil.dateAddDays(0, format);
            } else if (value.contains("today")) {
                String[] strarray = value.split("today");
                int days = Integer.parseInt(strarray[1].trim().replaceAll(" ", ""));
                ret = CommonUtil.dateAddDays(days, format);
            } else if (value.equals("current month")) {
                ret = CommonUtil.dateAddDays(0, "MM");
            } else if (value.equals("date of next month")) {
                ret = CommonUtil.dateAddMonths(1, format);
            }
        } catch (Exception e) {
            return value;
        }
        return ret;
    }

    /**
     * Return full user name that used for login
     * e.g.
     * Neo Zhang -> Neo.Zhang@dextrysinc.onmicrosoft.com
     * Jessie Li -> Jessie.Li@dextrysinc.onmicrosoft.com
     *
     * @param simpleUsername e.g. Neo Zhang, Jessie Li
     * @return e.g. Neo.Zhang@dextrysinc.onmicrosoft.com
     */
    public static String getFullUsername(String simpleUsername) {
        if (simpleUsername != null && !simpleUsername.trim().equals("")) {
            simpleUsername = simpleUsername.trim();
            String regEx = "[' ']+";
            Pattern p = Pattern.compile(regEx);
            Matcher matcher = p.matcher(simpleUsername);
            String fullName = matcher.replaceAll(".") + "@dextrysinc.onmicrosoft.com";
            return fullName;
        } else {
            return simpleUsername;
        }
    }

    /**
     * Get message content of pop-up dialog
     *
     * @param driver
     * @return
     * @throws Exception
     */
    public static String getPopupMessageContent(WebDriver driver) throws Exception {
        String content = null;
        String xpath = "//div[@class='popupManager']//div[@class='popup-body-content']//div[@class='panel-body']";
        WebElement popupBody = CommonWebDriver.getElement(driver, By.xpath(xpath));
        if (popupBody != null) {
            content = popupBody.getText();
        }
        return content;
    }

    /**
     * Close pop-up dialog
     *
     * @param driver
     * @throws Exception
     */
    public static void closePopupBox(WebDriver driver) throws Exception {
        CommonWebDriver.clickElementWhenPresent(driver, By.xpath(FormConstants.ok_button_xpath), FormConstants.ok_button_desc);
        CommonWebDriver.wait(driver, 1);
    }

    /**
     * Find matched values in a string according to specified regular expression
     *
     * @param input input value
     * @param regex regex value
     * @return
     */
    public static List<String> findMatchedValues(String input, String regex) {
        List<String> set = new ArrayList<String>();
        String output;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            output = matcher.group();
            if (output != null) {
                set.add(output);
            }
        }
        return set;
    }
    
	/*
    public static List<String> obtainFilesListInAParticularDirectory(
			String pathName) {
		List<String> files = new ArrayList<>();
		try {
			Files.walk(Paths.get(pathName)).forEach(filepath -> {
				if (Files.isRegularFile(filepath)) {
					files.add(filepath.getFileName().toString());
				}
			});
		} catch (IOException ex) {
			// Catch exception, do nothing.
		}
		return files;
	}*/

    @Test
    public static DesiredCapabilities getBrowser(String testNGBrowser) {
        DesiredCapabilities desiredCapabilities = null;
        String pomBrowser = System.getProperty("browser").toString();
        if (pomBrowser.equals("firefox")) {
            desiredCapabilities = DesiredCapabilities.firefox();
        } else if (pomBrowser.equals("chrome")) {
            desiredCapabilities = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type");
            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        } else {
            if (testNGBrowser.equals("firefox")) {
                desiredCapabilities = DesiredCapabilities.firefox();
            } else if (testNGBrowser.equals("chrome")) {
                desiredCapabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
            }
        }
        return desiredCapabilities;
    }

    // put string into clipboard
    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }


    public static void alertExist(WebDriver driver, String content) {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText().toString();
        if (alertText.toString().contains(content)) {
            System.out.println("See the correct alert, test pass! ");
        } else
            CommonAssert.fail("Does not see the correct dialog, test fail!");
    }
}
