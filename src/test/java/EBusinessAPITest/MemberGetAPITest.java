package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;


public class MemberGetAPITest {

    @Test
    public void memberGetAPITest() throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/member";
        String jsonStr = JsonUtility.getJsonContent(url);
        JSONObject jsonObject = JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        if (entity.contains("memberId")) {
            System.out.println("Get member API works well,test pass!");
        } else
            CommonAssert.fail("Get member API does not work well, test fail!");
    }
}
