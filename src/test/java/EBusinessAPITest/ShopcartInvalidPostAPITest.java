package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class ShopcartInvalidPostAPITest {

    @Test
    public void shopcartPostAPITest( ) throws Exception {
        //test invalid
        //Add product to shopcart
        String url = "http://54.222.195.248:8888/api/v1/burberry/shopcart";
        StringEntity inputBody = new StringEntity("{\"memberId\":\"4a27dfdc-0343-4d45-80fc-19b017f45e76\",\"productId\":\"c21b4294-6634-4443-9e85-bcdf69f293b3\",\"name\":\"Product Name\",\"price\":0,\"image\":\"test.jpg\",\"quantity\":22}");
        String postResult=JsonUtility.postJsonContent(url,inputBody);
        if(postResult.contains("\"message\":\"Insufficient Inventory\""))
        {
            System.out.println("Post invalid quantity product to shopcart see the correct warning message,test pass!");
        }
        else
            CommonAssert.fail("Post invalid quantity product to shopcart does not see the correct warning message, test fail!");
    }
}
