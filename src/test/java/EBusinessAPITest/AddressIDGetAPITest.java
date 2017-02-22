package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

import static EBusinessCommon.CommomConstants.addressID;


public class AddressIDGetAPITest {

    @Test
    public void addressGetAPITest() throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/address/"+addressID;
        String jsonStr = JsonUtility.getJsonContent(url);
        JSONObject jsonObject = JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        if (entity.contains("addressId")) {
            System.out.println("Get address id API succeed,test pass!");
        } else
            CommonAssert.fail("Get address id API get error, test fail!");
    }
}
