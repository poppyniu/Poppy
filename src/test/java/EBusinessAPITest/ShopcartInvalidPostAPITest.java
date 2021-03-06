package EBusinessAPITest;

import APICommon.CommomConstants;
import APICommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class ShopcartInvalidPostAPITest {

    @Test
    public void shopcartPostAPITest() throws Exception {
        //test invalid
        //Add product to shopcart
        String url = "http://54.222.195.248:8888/api/v1/burberry/shopcart";
        StringEntity inputBody = new StringEntity("{\"memberId\":\"" + CommomConstants.memberID + "\",\"productId\":\"" + CommomConstants.productID + "\",\"skuId\":\"" + CommomConstants.skuID + "\",\"skuAttrs\":{},\"name\":\"Product Name\",\"price\":0,\"image\":\"test.jpg\",\"quantity\":22}");
        String postResult = JsonUtility.postJsonContent(url, inputBody);
        if (postResult.contains("\"msg\":\"Insufficient Inventory\"")) {
            System.out.println("Post invalid quantity product to shopcart see the correct warning message,test pass!");
        } else
            CommonAssert.fail("Post invalid quantity product to shopcart does not see the correct warning message, test fail!");
    }
}
