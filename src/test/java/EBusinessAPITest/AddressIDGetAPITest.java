package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;


public class AddressIDGetAPITest {

    @Test
    public void addressGetAPITest( ) throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/address/a9f0815e-1ead-4498-8b95-d4bb65e4aba2";
        String jsonStr= JsonUtility.getJsonContent(url);
        JSONObject jsonObject=JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        if(entity.contains("金鸡湖大道1355号"))
        {
            System.out.println("Get address id API succeed,test pass!");
        }
        else
            CommonAssert.fail("Get address id API get error, test fail!");
    }
}
