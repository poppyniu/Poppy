package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import com.github.kevinsawicki.http.HttpRequest;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AddressGetAPITest {

    @Test
    public void addressGetAPITest( ) throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/address?memberId=4a27dfdc-0343-4d45-80fc-19b017f45e76";
        String jsonStr= JsonUtility.getJsonContent(url);
        JSONObject jsonObject=JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        if(entity.contains("张曼"))
        {
            System.out.println("Get address API succeed,test pass!");
        }
        else
            CommonAssert.fail("Get address API get error, test fail!");
    }
}
