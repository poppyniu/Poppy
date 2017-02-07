package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class ShopcartPostAPITest {

    @Test
    public void shopcartPostAPITest() throws Exception {
        //Add product to shopcart
        String url = "http://54.222.195.248:8888/api/v1/burberry/shopcart";
        StringEntity inputBody = new StringEntity("{\"memberId\":\"4a27dfdc-0343-4d45-80fc-19b017f45e76\",\"productId\":\"c21b4294-6634-4443-9e85-bcdf69f293b3\",\"name\":\"Product Name\",\"price\":0,\"image\":\"test.jpg\",\"quantity\":1}");
        String postResult = JsonUtility.postJsonContent(url, inputBody);
        if (postResult.contains("entity")) {
            System.out.println("Post shopcart API succeed,test pass!");
        } else
            CommonAssert.fail("Post shopcart API get error, test fail!");
        //Delete product in the shopcart
        String deleteShopcartUrl = "http://54.222.195.248:8888/api/v1/burberry/shopcart/4a27dfdc-0343-4d45-80fc-19b017f45e76";
        String deleteResult = JsonUtility.deleteHttpRequest(deleteShopcartUrl);
        if (deleteResult.contains("Mission accomplished")) {
            System.out.println("Delete shopcart API succeed,test pass!");
        } else
            CommonAssert.fail("Delete shopcart API get error, test fail!");

    }
}
