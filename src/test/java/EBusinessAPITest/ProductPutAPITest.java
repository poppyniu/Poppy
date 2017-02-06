package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class ProductPutAPITest {

    @Test
    public void productPutAPITest( ) throws Exception {
        //create a product
        String url = "http://54.222.195.248:8888/api/v1/burberry/product";
        StringEntity inputBody = new StringEntity("{\"name\":\"product name\",\"productCode\":\"ab102934\",\"digest\":\"example\",\"description\":\"example\",\"specification\":\"example\",\"extra\":{},\"images\":[\"test.jpg\"],\"price\":0,\"promoPrice\":0,\"inventory\":0,\"categoryIds\":[\"aaaaaaaa-bbbb-cccc-dddd-eeeeeeee\"]}");
        String postResult=JsonUtility.postJsonContent(url,inputBody);
        if(postResult.contains("Mission accomplished"))
        {
            System.out.println("Post product API succeed,test pass!");
        }
        else
            CommonAssert.fail("Post product API get error, test fail!");
        //get the new created product id
        String getProductUrl = "http://54.222.195.248:8888/api/v1/burberry/product?categoryId=aaaaaaaa-bbbb-cccc-dddd-eeeeeeee";
        String jsonStr= JsonUtility.getJsonContent(getProductUrl);
        JSONObject jsonObject=JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        String productId=entity.substring(81,117);
        if(entity.contains("productId"))
        {
            System.out.println("Get product API succeed,test pass!");
        }
        else
            CommonAssert.fail("Get product API get error, test fail!");
        //update the new created product info
        String prouctPutUrl = "http://54.222.195.248:8888/api/v1/burberry/product/"+productId;
        StringEntity prouctPutBody = new StringEntity("{\"name\":\"product name\",\"productCode\":\"ab102934\",\"digest\":\"example\",\"description\":\"example\",\"specification\":\"edit example\",\"extra\":{},\"images\":[\"test.jpg\"],\"price\":0,\"promoPrice\":0,\"inventory\":0,\"categoryIds\":[\"aaaaaaaa-bbbb-cccc-dddd-eeeeeeee\"]}");
        String putResult=JsonUtility.putJsonContent(prouctPutUrl,prouctPutBody);
        if(putResult.contains("Mission accomplished"))
        {
            System.out.println("Put product API succeed,test pass!");
        }
        else
            CommonAssert.fail("Put product API get error, test fail!");
        //delete a product
        String deleteProductUrl = "http://54.222.195.248:8888/api/v1/burberry/product/"+productId;
        String deleteResult=JsonUtility.deleteHttpRequest(deleteProductUrl);
        if(deleteResult.contains("Mission accomplished"))
        {
            System.out.println("Delete product API succeed,test pass!");
        }
        else
            CommonAssert.fail("Delete product API get error, test fail!");
    }
}