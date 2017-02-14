package EBusinessAPITest;

import EBusinessCommon.CommomConstants;
import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;


public class MemberIDGetAPITest {

    @Test
    public void memberIDGetAPITest() throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/member/"+ CommomConstants.memberID;
        String jsonStr = JsonUtility.getJsonContent(url);
        JSONObject jsonObject = JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        if (entity.contains(CommomConstants.memberID)) {
            System.out.println("Get member Id API works well,test pass!");
        } else
            CommonAssert.fail("Get address Id API does not work well, test fail!");
    }
}
