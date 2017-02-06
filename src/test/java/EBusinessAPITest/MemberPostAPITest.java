package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class MemberPostAPITest {

    @Test
    public void memberPostAPITest( ) throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/member";
        StringEntity inputBody = new StringEntity("{\"openid\":\"oBY6UuOPXMneh4J1XZfFUlBd2RJB\",\"telephone\":\"15850230000\",\"name\":\"Member name\",\"nickname\":\"Member nickname\",\"gender\":\"1\",\"birthdate\":\"2016-01-01\",\"email\":\"example@example.com\"}");
        String postResult=JsonUtility.postJsonContent(url,inputBody);
        String newMemberId=postResult.substring(23,59);
        if(postResult.contains("memberId"))
        {
            System.out.println("Post member API works well,test pass!");
        }
        else
            CommonAssert.fail("Post member API does not work well, test fail!");

        //delete test data
        String deleteMemberUrl = "http://54.222.195.248:8888/api/v1/burberry/member/"+newMemberId;
        String deleteResult=JsonUtility.deleteHttpRequest(deleteMemberUrl);
        if(deleteResult.contains("Mission accomplished"))
        {
            System.out.println("Delete address API succeed,test pass!");
        }
        else
            CommonAssert.fail("Delete address API get error, test fail!");
    }
}
