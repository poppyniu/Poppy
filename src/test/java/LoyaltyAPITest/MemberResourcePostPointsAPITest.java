package LoyaltyAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MemberResourcePostPointsAPITest {

    @Test
    public void memberResourcePostPointsAPITest() throws Exception {
        //authenticate first
        String idToken = JsonUtility.getIDToken();
        //get current points count
        String url = "http://wechat1.dextrys.com:7777/api/members/points?systemType=wechat&socialId=25a7075919404d34a2db543d9dacafc2&eshopId=6a399cb1d62e4094bdc11e15c12557f6&languageTag=en";
        String jsonStr = JsonUtility.getJsonContent(url,idToken);
        JSONObject jsonObject = JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("data");
        int oldPointsCount=Integer.parseInt(entity.substring(10,entity.length()-1));
        if (entity.contains("points")) {
            System.out.println("Get member resource points API succeed,test pass!");
        } else
            CommonAssert.fail("Get member resource points API get error, test fail!");
        //update old points count (to add 10)
        String urlPostMembersPoints = "http://wechat1.dextrys.com:7777/api/members/points";
        StringEntity inputBodyMembers = new StringEntity("{\"eshopId\":\"6a399cb1d62e4094bdc11e15c12557f6\",\"languageTag\":\"en\",\"note\":\"shopping\",\"pointsNumber\":10,\"socialId\":\"25a7075919404d34a2db543d9dacafc2\",\"systemType\":\"wechat\",\"type\":\"SHOPPING\"}");
        String postResultMembers = JsonUtility.postJsonContent(urlPostMembersPoints, inputBodyMembers,idToken);
        if (postResultMembers.contains("userName")) {
            System.out.println("Post member resource point API succeed,test pass!");
        } else
            CommonAssert.fail("Post member resource point API get error, test fail!");
        //check if new points count number is 10 more than old one
        String urlNew = "http://wechat1.dextrys.com:7777/api/members/points?systemType=wechat&socialId=25a7075919404d34a2db543d9dacafc2&eshopId=6a399cb1d62e4094bdc11e15c12557f6&languageTag=en";
        String jsonStrNew  = JsonUtility.getJsonContent(urlNew,idToken);
        JSONObject jsonObjectNew  = JsonUtility.jsonStrToJsonObject(jsonStrNew);
        String entityNew  = jsonObjectNew.getString("data");
        int newPointsCount=Integer.parseInt(entityNew.substring(10,entityNew.length()-1));
        if (entity.contains("points")) {
            System.out.println("Get member resource points API succeed,test pass!");
        } else
            CommonAssert.fail("Get member resource points API get error, test fail!");
        if(newPointsCount-oldPointsCount==10){
            System.out.println("Post member resource points API succeed,test pass!");
        }
        else
        {
            CommonAssert.fail("Post member resource points API get error, test fail!");
        }
    }
}
