package WB2CCommon;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestNGListener extends TestListenerAdapter{
    private static Logger log = LoggerFactory.getLogger(CommonWebDriver.class);

    @Override
    public void onTestSuccess(ITestResult tr) {
        log.info("Test Success");
        super.onTestSuccess(tr);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        log.error("Test Failure");
        super.onTestFailure(tr);
        takeScreenShot(tr);
    }

    private void takeScreenShot(ITestResult tr) {
        CommonWebDriver commonWebDriver = (CommonWebDriver) tr.getInstance();
        WebDriver driver = commonWebDriver.getDriver();
        System.out.println(driver.getTitle());
        commonWebDriver.takeScreenShot();

    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log.error("Test Skipped");
        super.onTestSkipped(tr);
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test Finsh");
        super.onTestStart(result);
    }

    @Override
    public void onStart(ITestContext testContext) {
        log.info("Test Start");
        super.onStart(testContext);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        log.info("Test Finish");
        super.onFinish(testContext);
    }

}
