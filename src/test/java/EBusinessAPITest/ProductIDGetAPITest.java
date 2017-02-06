package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;


public class ProductIDGetAPITest {

    @Test
    public void productGetAPITest( ) throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/product/c21b4294-6634-4443-9e85-bcdf69f293b3";
        String jsonStr= JsonUtility.getJsonContent(url);
        JSONObject jsonObject=JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        if(entity.contains("productId"))
        {
            System.out.println("Get product by id API works well,test pass!");
        }
        else
            CommonAssert.fail("Get product by id API does not work well,test fail!");
    }
}
