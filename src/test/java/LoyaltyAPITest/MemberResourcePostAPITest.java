package LoyaltyAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MemberResourcePostAPITest {

    @Test
    public void memberResourcePostAPITest() throws Exception {
        //authenticate first
        String idToken = JsonUtility.getIDToken();
        //request url
        String urlMembers = "http://wechat1.dextrys.com:7777/api/members";
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        String userName="test"+time;
        StringEntity inputBodyMembers = new StringEntity("{\"active\":true,\"address\":\"suzhou\",\"birthday\":\"2017-04-05\",\"brandId\":\"666e552b479447eaaff28fcb7887f018\",\"email\":\"123@163.com\",\"eshopId\":\"6a399cb1d62e4094bdc11e15c12557f6\",\"gender\":\"MALE\",\"languageTag\":\"en\",\"memberName\":\"poppy\",\"phone\":\"15250113597\",\"socialId\":\"25a7075919404d34a2db543d9dacafc2\",\"systemName\":\"wechat\",\"systemType\":\"wechat\",\"userName\":\"" + userName + "\"}");
        String postResultMembers = JsonUtility.postJsonContent(urlMembers, inputBodyMembers,idToken);
        if (postResultMembers.contains("test")) {
            System.out.println("Post member resource API succeed,test pass!");
        } else
            CommonAssert.fail("Post member resource API get error, test fail!");
    }
}
