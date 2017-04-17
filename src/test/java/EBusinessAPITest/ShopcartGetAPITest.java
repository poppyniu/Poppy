package EBusinessAPITest;

import APICommon.CommomConstants;
import APICommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class ShopcartGetAPITest {
    @Test
    public void shopcartGetAPITest() throws Exception {
        //Add product to shopcart to prepare testdata
        String url = "http://54.222.195.248:8888/api/v1/burberry/shopcart";
        StringEntity inputBody = new StringEntity("{\"memberId\":\"" + CommomConstants.memberID + "\",\"productId\":\"" + CommomConstants.productID + "\",\"skuId\":\"" + CommomConstants.skuID + "\",\"skuAttrs\":{},\"name\":\"Product Name\",\"price\":0,\"image\":\"test.jpg\",\"quantity\":1}");
        String postResult = JsonUtility.postJsonContent(url, inputBody);
        if (postResult.contains("entity")) {
            System.out.println("Post shopcart API succeed,test pass!");
        } else
            CommonAssert.fail("Post shopcart API get error, test fail!");
        //Get detail data for single category
        String getShopcartUrl = "http://54.222.195.248:8888/api/v1/burberry/shopcart/"+CommomConstants.memberID ;
        String jsonStr = JsonUtility.getJsonContent(getShopcartUrl);
        JSONObject jsonObject = JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        if (entity.contains("quantity")) {
            System.out.println("Get shopcart API works well,test pass!");
        } else
            CommonAssert.fail("Get shopcart API does not work well, test fail!");
        //empty the shopcart
        String deleteShopcartUrl = "http://54.222.195.248:8888/api/v1/burberry/shopcart/"+CommomConstants.memberID ;
        String deleteResult = JsonUtility.deleteHttpRequest(deleteShopcartUrl);
        if (deleteResult.contains("Mission accomplished")) {
            System.out.println("Delete shopcart API succeed,test pass!");
        } else
            CommonAssert.fail("Delete shopcart API get error, test fail!");
    }
}
