package EBusinessAPITest;

import APICommon.CommomConstants;
import APICommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;


public class OrderGetIDAPITest {

    @Test
    public void orderGetAPITest() throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/order/273010436?memberId="+ CommomConstants.memberID;
        String jsonStr = JsonUtility.getJsonContent(url);
        JSONObject jsonObject = JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        if (entity.contains("fullAddress")) {
            System.out.println("Get order by id API works well,test pass!");
        } else
            CommonAssert.fail("Get order by id API does not work well, test fail!");
    }
}
