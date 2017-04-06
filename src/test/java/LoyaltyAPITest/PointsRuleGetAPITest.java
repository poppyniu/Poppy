package LoyaltyAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;


public class PointsRuleGetAPITest {

    @Test
    public void pointsRuleGetAPITest() throws Exception {
        //authenticate first
        String idToken = JsonUtility.getIDToken();
        //request url
        String url = "http://wechat1.dextrys.com:7777/api/pointsRule/rules";
        String jsonStr = JsonUtility.getJsonContent(url,idToken);
        JSONObject jsonObject = JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("data");
        if (entity.contains("rules")) {
            System.out.println("Get points rule API succeed,test pass!");
        } else
            CommonAssert.fail("Get points rule API get error, test fail!");
    }
}
