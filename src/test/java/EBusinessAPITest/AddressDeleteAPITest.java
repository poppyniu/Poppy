package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class AddressDeleteAPITest {

    @Test
    public void addressDeleteAPITest( ) throws Exception {
        //request url
        String url = "http://54.222.195.248:8888/api/v1/burberry/address/714d116a-6a7a-4fff-8217-e297616d5fd2";
        String deleteResult=JsonUtility.deleteHttpRequest(url);
        if(deleteResult.contains("Mission accomplished"))
        {
            System.out.println("Delete address API succeed,test pass!");
        }
        else
            CommonAssert.fail("Delete address API get error, test fail!");
    }
}
