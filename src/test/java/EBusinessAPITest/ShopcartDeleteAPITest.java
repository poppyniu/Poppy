package EBusinessAPITest;

import EBusinessCommon.JsonUtility;
import WB2CCommon.CommonAssert;
import org.testng.annotations.Test;


public class ShopcartDeleteAPITest {

    @Test
    public void shopcartDeleteAPITest() throws Exception {
        //Delete product in the shopcart
        String deleteShopcartUrl = "http://54.222.195.248:8888/api/v1/burberry/shopcart/4a27dfdc-0343-4d45-80fc-19b017f45e76";
        String deleteResult = JsonUtility.deleteHttpRequest(deleteShopcartUrl);
        if (deleteResult.contains("Mission accomplished")) {
            System.out.println("Delete shopcart API succeed,test pass!");
        } else
            CommonAssert.fail("Delete shopcart API get error, test fail!");
    }
}
