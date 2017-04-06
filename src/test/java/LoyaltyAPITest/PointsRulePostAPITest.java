package LoyaltyAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PointsRulePostAPITest {

    @Test
    public void pointsRulePostAPITest() throws Exception {
        //authenticate first
        String idToken = JsonUtility.getIDToken();
        //request url
        String urlMembers = "http://wechat1.dextrys.com:7777/api/pointsRule/convertMoney";
        StringEntity inputBodyMembers = new StringEntity("{\"amount\":123.5,\"eshopId\":\"6a399cb1d62e4094bdc11e15c12557f6\",\"languageTag\":\"en\",\"socialId\":\"25a7075919404d34a2db543d9dacafc2\",\"systemType\":\"wechat\"}");
        String postResultMembers = JsonUtility.postJsonContent(urlMembers, inputBodyMembers,idToken);
        if (postResultMembers.contains("money")) {
            System.out.println("Post points rule resource API succeed,test pass!");
        } else
            CommonAssert.fail("Post points rule resource API get error, test fail!");
    }
}
