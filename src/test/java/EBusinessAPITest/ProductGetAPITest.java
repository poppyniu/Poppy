package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;


public class ProductGetAPITest {

    @Test
    public void productGetAPITest( ) throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/product?categoryId=4ca84746-7c7e-49ea-ad09-89ac35e9077d";
        String jsonStr= JsonUtility.getJsonContent(url);
        JSONObject jsonObject=JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        if(entity.contains("productId"))
        {
            System.out.println("Get product API succeed,test pass!");
        }
        else
            CommonAssert.fail("Get product API get error, test fail!");
    }
}
