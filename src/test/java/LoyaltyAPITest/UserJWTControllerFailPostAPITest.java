package LoyaltyAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class UserJWTControllerFailPostAPITest {

    @Test
    public void userJWTControllerFailPostAPITest() throws Exception {
        //request url
        String url = "http://wechat1.dextrys.com:7777/api/authenticate";
        StringEntity inputBody1 = new StringEntity("{\"password\":\"admin\",\"rememberMe\":true,\"username\":\"111\"}");
        String postResult = JsonUtility.postJsonContent(url, inputBody1);
        if (postResult.contains("Bad credentials")) {
            System.out.println("Post user jwt controller API with wrong parameter see the correct warning message,test pass!");
        } else
            CommonAssert.fail("Post user jwt controller API with wrong parameter does not see the correct warning message, test fail!");
    }
}
