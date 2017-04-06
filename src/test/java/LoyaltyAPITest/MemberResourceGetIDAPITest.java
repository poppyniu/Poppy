package LoyaltyAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;


public class MemberResourceGetIDAPITest {

    @Test
    public void memberResourceGetIDAPITest() throws Exception {
        //authenticate first
        String idToken = JsonUtility.getIDToken();
        //request url
        String url = "http://wechat1.dextrys.com:7777/api/members/3";
        String jsonStr = JsonUtility.getJsonContent(url,idToken);
        JSONObject jsonObject = JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("data");
        if (entity.contains("poppy")) {
            System.out.println("Get member resource by id API succeed,test pass!");
        } else
            CommonAssert.fail("Get member resource by id API get error, test fail!");
    }
}
