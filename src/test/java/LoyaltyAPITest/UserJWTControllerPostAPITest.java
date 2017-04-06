package LoyaltyAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class UserJWTControllerPostAPITest {

    @Test
    public void  userJWTControllerPostAPITest() throws Exception {
        //request url
        String url = "http://wechat1.dextrys.com:7777/api/authenticate";
        StringEntity inputBody = new StringEntity("{\"password\":\"admin\",\"rememberMe\":true,\"username\":\"admin\"}");
        String postResult = JsonUtility.postJsonContent(url, inputBody);
        if (postResult.contains("id_token")) {
            System.out.println("Post user jwt controller API succeed,test pass!");
        } else
            CommonAssert.fail("Post user jwt controller API get error, test fail!");
    }
}
