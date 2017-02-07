package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class AddressPutAPITest {

    @Test
    public void addressPutAPITest() throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/address/c6cabf48-ad64-4386-adf6-ac7463b79148";
        StringEntity inputBody = new StringEntity("{\"memberId\":\"00000000-0000-0000-0000-000000000000\",\"consignee\":\"Consignee\",\"consigneePhone\":\"Consignee Phone\",\"country\":\"中国\",\"province\":\"江苏省\",\"city\":\"苏州市\",\"area\":\"工业园区 \",\"address\":\"xxxxxx\",\"postalCode\":\"215000\",\"default\":0}");
        String putResult = JsonUtility.putJsonContent(url, inputBody);
        if (putResult.contains("Mission accomplished")) {
            System.out.println("Put address API succeed,test pass!");
        } else
            CommonAssert.fail("Put address API get error, test fail!");
    }
}
