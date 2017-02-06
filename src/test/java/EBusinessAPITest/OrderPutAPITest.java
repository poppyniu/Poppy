package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class OrderPutAPITest {

    @Test
    public void orderPutAPITest( ) throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/order/2393303";
        StringEntity inputBody = new StringEntity("{\"memberId\":\"00000000-0000-0000-0000-000000000000\",\"consignee\":\"Consignee\",\"consigneePhone\":\"Consignee Phone\",\"fullAddress\":\"江苏省苏州市工业园区\",\"remark\":\"remark\"}");
        String putResult=JsonUtility.putJsonContent(url,inputBody);
        if(putResult.contains("Mission accomplished"))
        {
            System.out.println("Put oder API succeed,test pass!");
        }
        else
            CommonAssert.fail("Put oder API get error, test fail!");
    }
}
