package WB2CConstants;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AppConstants {
	
	public static final String IE_DRIVER = "IEDriverServer.exe";
	public static final String CHROME_DRIVER = "chromedriver.exe";
	public static final int STATUS_FAILURE = 2;
	public static final int STATUS_SKIP = 3;
	public static final String CONFIG = "appconfig.properties";
	public static final String CASE_PROPERTY = "TestCase.properties";
	public static final String DRIVER_FOLDER = "driver";
	public static final String DATA_FOLDER = "testdata";
	
	//wait time (second)
	public static long minorWait;
	public static long smallWait;
	public static long middleWait;
	public static long longWait;
	public static long customizeWait;
	public static int maxWaitCount;
	
	public static String envBaseUrl = null;
	public static String screenshotBasePath = null;
	public static String maxRetryCount = null;
	
	public static String browserName = "";
	public static String executor = "";
	public static String siteCollectionUrl = "";
	
	public static final boolean LOG_STEP = true;

}
