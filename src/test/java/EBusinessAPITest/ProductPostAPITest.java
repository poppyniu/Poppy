package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class ProductPostAPITest {

    @Test
    public void productPostAPITest() throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/product";
        StringEntity inputBody = new StringEntity("{\"name\":\"product name\",\"productCode\":\"ab102934\",\"digest\":\"example\",\"description\":\"example\",\"specification\":\"example\",\"extra\":{},\"images\":[\"test.jpg\"],\"price\":0,\"originalPrice\":0,\"totalQuantity\":0,\"categoryIds\":[\"aaaaaaaq-bbbq-cccq-dddq-eeeeeeeq\"],\"status\":2,\"family\":[{}],\"skus\":[{\"attrs\":[{\"name\":\"color\",\"value\":\"Red\"}],\"price\":0,\"quantity\":0}]}");
        String postResult = JsonUtility.postJsonContent(url, inputBody);
        if (postResult.contains("Mission accomplished")) {
            System.out.println("Post product API succeed,test pass!");
        } else
            CommonAssert.fail("Post product API get error, test fail!");
    }
}
