package LoyaltyAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class OrderResourcePostAPITest {

    @Test
    public void orderResourcePostAPITest() throws Exception {
        //authenticate first
        String idToken = JsonUtility.getIDToken();
        //request url
        String urlOrder = "http://wechat1.dextrys.com:7777/api/order/points";
        StringEntity inputBodyOrder = new StringEntity("{\"eshopId\":\"6a399cb1d62e4094bdc11e15c12557f6\",\"languageTag\":\"en\",\"order\":{\"amount\":123.55,\"buyNum\":1,\"productList\":[{\"categoryIds\":\"1101,220,38\",\"price\":123.55,\"productId\":\"123023\",\"quantity\":1}]},\"socialId\":\"25a7075919404d34a2db543d9dacafc2\",\"systemType\":\"wechat\"}");
        String postResultMembers = JsonUtility.postJsonContent(urlOrder, inputBodyOrder,idToken);
        if (postResultMembers.contains("points")) {
            System.out.println("Post order resource API succeed,test pass!");
        } else
            CommonAssert.fail("Post order resource API get error, test fail!");
    }
}
