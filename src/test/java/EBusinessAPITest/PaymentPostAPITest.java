package EBusinessAPITest;

import EBusinessCommon.CommomConstants;
import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class PaymentPostAPITest {

    @Test
    public void paymentPostAPITest() throws Exception {
        //add data to shopcart to prepare test data
        String url = "http://54.222.195.248:8888/api/v1/burberry/shopcart";
        StringEntity inputBody = new StringEntity("{\"memberId\":\"" + CommomConstants.memberID + "\",\"productId\":\"" + CommomConstants.productID + "\",\"skuId\":\"" + CommomConstants.skuID + "\",\"skuAttrs\":{},\"name\":\"Product Name\",\"price\":0,\"image\":\"test.jpg\",\"quantity\":1}");
        String postResult = JsonUtility.postJsonContent(url, inputBody);
        if (postResult.contains("entity")) {
            System.out.println("Post shopcart API succeed,test pass!");
        } else
            CommonAssert.fail("Post shopcart API get error, test fail!");
        //post order (create new order) when the shopcart has data
        String orderPostUrl = "http://54.222.195.248:8888/api/v1/burberry/order/";
        StringEntity postOrderBody = new StringEntity("{\"memberId\":\"" + CommomConstants.memberID + "\",\"consignee\":\"Consignee\",\"consigneePhone\":\"Consignee Phone\",\"fullAddress\":\"江苏省苏州市工业园区\",\"remark\":\"Remark\"}");
        String postOrderResult = JsonUtility.postJsonContent(orderPostUrl, postOrderBody);
        String oderId = postOrderResult.substring(21, 29);
        if (postOrderResult.contains("orderId")) {
            System.out.println("Post order API succeed,test pass!");
        } else
            CommonAssert.fail("Post order API get error, test fail!");
        //payment post
        String paymentPostUrl = "http://54.222.195.248:8888/api/v1/burberry/payment";
        StringEntity paymentPostBody = new StringEntity("{\"orderId\":\"" + oderId + "\",\"memberId\":\"" + CommomConstants.memberID + "\",\"type\":\"wechatpay\"}");
        String paymentPostResult = JsonUtility.postJsonContent(paymentPostUrl, paymentPostBody);
        if (paymentPostResult.contains("Mission accomplished")) {
            System.out.println("Post payment API succeed,test pass!");
        } else
            CommonAssert.fail("Post payment API get error, test fail!");
        //put product to recover the inventory of the product
        String productPutUrl = "http://54.222.195.248:8888/api/v1/burberry/product/"+CommomConstants.productID;
        StringEntity productPutBody = new StringEntity("{\"name\":\"product name\",\"productCode\":\"ab102934\",\"digest\":\"edit example\",\"description\":\"example\",\"specification\":\"example\",\"extra\":{},\"images\":[\"test.jpg\"],\"price\":0,\"originalPrice\":0,\"totalQuantity\":30,\"categoryIds\":[\"4ca84746-7c7e-49ea-ad09-89ac35e9077d\"],\"status\":2,\"family\":[{}],\"skus\":[{\"attrs\":[{\"name\":\"color\",\"value\":\"Red\"}],\"price\":0,\"quantity\":10}]}");
        String putResult = JsonUtility.putJsonContent(productPutUrl, productPutBody);
        if (putResult.contains("Mission accomplished")) {
            System.out.println("Put product API succeed,test pass!");
        } else
            CommonAssert.fail("Put product API get error, test fail!");
    }
}
