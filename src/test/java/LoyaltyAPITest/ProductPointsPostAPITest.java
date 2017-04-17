package LoyaltyAPITest;

import APICommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class ProductPointsPostAPITest {

    @Test
    public void productPointsPostAPITest() throws Exception {
        //authenticate first
        String idToken = JsonUtility.getIDToken();
        //request url
        String urlMembers = "http://wechat1.dextrys.com:7777/api/product/points";
        StringEntity inputBodyMembers = new StringEntity("{\"category\":\"1101,220,38\",\"eshopId\":\"6a399cb1d62e4094bdc11e15c12557f6\",\"languageTag\":\"en\",\"price\":123.55,\"productId\":\"123023\",\"socialId\": \"25a7075919404d34a2db543d9dacafc2\",\"systemType\": \"wechat\"}");
        String postResultMembers = JsonUtility.postJsonContent(urlMembers, inputBodyMembers,idToken);
        if (postResultMembers.contains("points")) {
            System.out.println("Post product points API succeed,test pass!");
        } else
            CommonAssert.fail("Post product points API get error, test fail!");
    }
}
