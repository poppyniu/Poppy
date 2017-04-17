package EBusinessAPITest;

import APICommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;


public class ProductCategoryGetAPITest {

    @Test
    public void productCategoryGetAPITest() throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/productCategory";
        String jsonStr = JsonUtility.getJsonContent(url);
        JSONObject jsonObject = JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        if (entity.contains("categoryId")) {
            System.out.println("Get product category API succeed,test pass!");
        } else
            CommonAssert.fail("Get product category API get error, test fail!");
    }
}
