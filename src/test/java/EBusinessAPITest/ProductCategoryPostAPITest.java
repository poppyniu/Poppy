package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class ProductCategoryPostAPITest {

    @Test
    public void productCategoryPostAPITest() throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/productCategory";
        StringEntity inputBody = new StringEntity("{\"name\":\"New Category Name\",\"parentCategory\":\"\",\"digest\":\"Summary infomation for this category\"}");
        String postResult = JsonUtility.postJsonContent(url, inputBody);
        if (postResult.contains("Mission accomplished")) {
            System.out.println("Post product category API succeed,test pass!");
        } else
            CommonAssert.fail("Post product category API get error, test fail!");
    }
}
