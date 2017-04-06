package LoyaltyAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;


public class MemberResourceGetPointsAPITest {

    @Test
    public void memberResourceGetPointsAPITest() throws Exception {
        //authenticate first
        String idToken = JsonUtility.getIDToken();
        //request url
        String url = "http://wechat1.dextrys.com:7777/api/members/points?systemType=wechat&socialId=25a7075919404d34a2db543d9dacafc2&eshopId=6a399cb1d62e4094bdc11e15c12557f6&languageTag=en";
        String jsonStr = JsonUtility.getJsonContent(url,idToken);
        JSONObject jsonObject = JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("data");
        if (entity.contains("points")) {
            System.out.println("Get member resource points API succeed,test pass!");
        } else
            CommonAssert.fail("Get member resource points API get error, test fail!");
    }
}
