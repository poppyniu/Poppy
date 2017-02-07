package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class OrderDeleteAPITest {

    @Test
    public void orderGetInfoAPITest() throws Exception {
        //add data to shopcart to prepare test data
        String url = "http://54.222.195.248:8888/api/v1/burberry/shopcart";
        StringEntity inputBody = new StringEntity("{\"memberId\":\"4a27dfdc-0343-4d45-80fc-19b017f45e76\",\"productId\":\"c21b4294-6634-4443-9e85-bcdf69f293b3\",\"name\":\"Product Name\",\"price\":0,\"image\":\"test.jpg\",\"quantity\":1}");
        String postResult = JsonUtility.postJsonContent(url, inputBody);
        if (postResult.contains("entity")) {
            System.out.println("Post shopcart API succeed,test pass!");
        } else
            CommonAssert.fail("Post shopcart API get error, test fail!");
        //post order (create new order) when the shopcart has data
        String orderPostUrl = "http://54.222.195.248:8888/api/v1/burberry/order/";
        StringEntity postOrderBody = new StringEntity("{\"memberId\":\"4a27dfdc-0343-4d45-80fc-19b017f45e76\",\"consignee\":\"Consignee\",\"consigneePhone\":\"Consignee Phone\",\"fullAddress\":\"江苏省苏州市工业园区\",\"remark\":\"Remark\"}");
        String postOrderResult = JsonUtility.postJsonContent(orderPostUrl, postOrderBody);
        String oderId = postOrderResult.substring(21, 29);
        if (postOrderResult.contains("orderId")) {
            System.out.println("Post order API succeed,test pass!");
        } else
            CommonAssert.fail("Post order API get error, test fail!");
        //delete the new order
        String deleteOrderUrl = "http://54.222.195.248:8888/api/v1/burberry/order/" + oderId;
        String deleteResult = JsonUtility.deleteHttpRequest(deleteOrderUrl);
        if (deleteResult.contains("Mission accomplished")) {
            System.out.println("Delete order API succeed,test pass!");
        } else
            CommonAssert.fail("Delete order API get error, test fail!");
        //put product to recover the inventory of the product
        String productPutUrl = "http://54.222.195.248:8888/api/v1/burberry/product/c21b4294-6634-4443-9e85-bcdf69f293b3";
        StringEntity productPutBody = new StringEntity("{\"name\":\"new poppy product\",\"productCode\":\"ab102934\",\"digest\":\"digest\",\"description\":\"description\",\"specification\":\"specification\",\"extra\":{},\"images\":[\"test.jpg \"],\"price\":0,\"inventory\":11,\"categoryIds\":[\"4ca84746-7c7e-49ea-ad09-89ac35e9077d\"]}");
        String putResult = JsonUtility.putJsonContent(productPutUrl, productPutBody);
        if (putResult.contains("Mission accomplished")) {
            System.out.println("Put product API succeed,test pass!");
        } else
            CommonAssert.fail("Put product API get error, test fail!");
    }
}
