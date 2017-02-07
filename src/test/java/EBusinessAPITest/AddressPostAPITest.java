package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class AddressPostAPITest {

    @Test
    public void addressPostAPITest() throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/address";
        StringEntity inputBody = new StringEntity("{\"memberId\":\"00000000-0000-0000-0000-000000000000\",\"consignee\":\"Consignee\",\"consigneePhone\":\"Consignee Phone\",\"country\":\"中国\",\"province\":\"江苏省\",\"city\":\"苏州市\",\"area\":\"工业园区 \",\"address\":\"xxxxxx\",\"postalCode\":\"215000\",\"default\":0}");
        String postResult = JsonUtility.postJsonContent(url, inputBody);
        if (postResult.contains("Mission accomplished")) {
            System.out.println("Post address API succeed,test pass!");
        } else
            CommonAssert.fail("Post address API get error, test fail!");
    }
}
