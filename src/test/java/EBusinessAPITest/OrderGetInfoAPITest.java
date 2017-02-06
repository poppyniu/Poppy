package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class OrderGetInfoAPITest {

    @Test
    public void orderGetInfoAPITest( ) throws Exception {
        //add data to shopcart to prepare test data
        String url = "http://54.222.195.248:8888/api/v1/burberry/shopcart";
        StringEntity inputBody = new StringEntity("{\"memberId\":\"4a27dfdc-0343-4d45-80fc-19b017f45e76\",\"productId\":\"c21b4294-6634-4443-9e85-bcdf69f293b3\",\"name\":\"Product Name\",\"price\":0,\"image\":\"test.jpg\",\"quantity\":1}");
        String postResult=JsonUtility.postJsonContent(url,inputBody);
        if(postResult.contains("entity"))
        {
            System.out.println("Post shopcart API succeed,test pass!");
        }
        else
            CommonAssert.fail("Post shopcart API get error, test fail!");
        //get order info when the shopcart has data
        String orderGetInfoUrl = "http://54.222.195.248:8888/api/v1/burberry/order/info?memberId=4a27dfdc-0343-4d45-80fc-19b017f45e76";
        String jsonStr= JsonUtility.getJsonContent(orderGetInfoUrl);
        JSONObject jsonObject=JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        if(entity.contains("\"quantity\":1"))
        {
            System.out.println("Get order info API works well when the shopcart has data,test pass!");
        }
        else
            CommonAssert.fail("Get order info API does not works well when the shopcart has data, test fail!");
        //Delete product in the shopcart
        String deleteShopcartUrl = "http://54.222.195.248:8888/api/v1/burberry/shopcart/4a27dfdc-0343-4d45-80fc-19b017f45e76";
        String deleteResult=JsonUtility.deleteHttpRequest(deleteShopcartUrl);
        if(deleteResult.contains("Mission accomplished"))
        {
            System.out.println("Delete shopcart API succeed,test pass!");
        }
        else
            CommonAssert.fail("Delete shopcart API get error, test fail!");
    }
}
