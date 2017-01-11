package WB2CUtility;

import org.openqa.selenium.WebDriver;

;

public class GetUrlThread extends Thread {

    private WebDriver driver;
    private String URL;
    private boolean openFailed;

    public GetUrlThread(WebDriver driver, String URL) {
        this.driver = driver;
        this.URL = URL;
        this.openFailed = true;
    }

    @Override
    public void run() {
        try {
            driver.get(URL);
            setOpenFailed(false);
        } catch (Exception e) {
            //throw new MyException("Fail to load URL " + URL, e);
            System.out.println("Try to reload URL " + URL);
        }

    }

    public boolean getOpenFailed() {
        return openFailed;
    }

    public void setOpenFailed(boolean value) {
        openFailed = value;
    }


}
