package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;


public class OrderGetAPITest {

    @Test
    public void orderGetAPITest( ) throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/order?memberId=4a27dfdc-0343-4d45-80fc-19b017f45e76";
        String jsonStr= JsonUtility.getJsonContent(url);
        if(jsonStr.contains("fullAddress"))
        {
            System.out.println("Get order API works well,test pass!");
        }
        else
            CommonAssert.fail("Get order API does not work well, test fail!");
    }
}
