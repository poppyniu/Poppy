package LoyaltyAPITest;

import APICommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class PointsRuleRefreshEndpointPostAPITest {

    @Test
    public void pointsRuleRefreshPostAPITest() throws Exception {
        //authenticate first
        String idToken = JsonUtility.getIDToken();
        //request url
        String urlMembers = "http://wechat1.dextrys.com:7777/api/pointsRule/refreshEndpoint";
        StringEntity inputBodyMembers = new StringEntity("{}");
        String postResultMembers = JsonUtility.postJsonContent(urlMembers, inputBodyMembers,idToken);
        if (postResultMembers.contains("data")) {
            System.out.println("Post points rule refresh endpoint API succeed,test pass!");
        } else
            CommonAssert.fail("Post points rule refresh endpoint API get error, test fail!");
    }
}
