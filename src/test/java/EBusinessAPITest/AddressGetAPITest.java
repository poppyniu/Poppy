package EBusinessAPITest;

import APICommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

import static APICommon.CommomConstants.memberID;


public class AddressGetAPITest {

    @Test
    public void addressGetAPITest() throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/address?memberId="+memberID;
        String jsonStr = JsonUtility.getJsonContent(url);
        JSONObject jsonObject = JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        if (entity.contains("consignee")) {
            System.out.println("Get address API succeed,test pass!");
        } else
            CommonAssert.fail("Get address API get error, test fail!");
    }
}
