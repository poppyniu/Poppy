package EBusinessAPITest;

import APICommon.CommomConstants;
import APICommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.testng.annotations.Test;


public class OrderGetAPITest {

    @Test
    public void orderGetAPITest() throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/order?memberId="+ CommomConstants.memberID;
        String jsonStr = JsonUtility.getJsonContent(url);
        if (jsonStr.contains("fullAddress")) {
            System.out.println("Get order API works well,test pass!");
        } else
            CommonAssert.fail("Get order API does not work well, test fail!");
    }
}
