package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;


public class MemberIDGetAPITest {

    @Test
    public void memberIDGetAPITest( ) throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/member/4a27dfdc-0343-4d45-80fc-19b017f45e76";
        String jsonStr= JsonUtility.getJsonContent(url);
        JSONObject jsonObject=JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        if(entity.contains("4a27dfdc-0343-4d45-80fc-19b017f45e76"))
        {
            System.out.println("Get member Id API works well,test pass!");
        }
        else
            CommonAssert.fail("Get address Id API does not work well, test fail!");
    }
}
