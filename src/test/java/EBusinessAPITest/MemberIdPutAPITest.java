package EBusinessAPITest;

import EBusinessCommon.CommomConstants;
import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class MemberIdPutAPITest {

    @Test
    public void memberIdPutAPITest() throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/member/"+ CommomConstants.memberID;
        StringEntity inputBody = new StringEntity("{\"openid\":\"oGFtdvwr7ySn4rzvTqb9cygd7FDE\",\"telephone\":\"18616915369\",\"name\":\"poppy\",\"nickname\":\"poppyniu\",\"gender\":\"1\",\"birthdate\":\"2016-01-01\",\"email\":\"example@example.com\"}");
        String putResult = JsonUtility.putJsonContent(url, inputBody);
        if (putResult.contains("Mission accomplished")) {
            System.out.println("Put member API succeed,test pass!");
        } else
            CommonAssert.fail("Put member API get error, test fail!");
    }
}
