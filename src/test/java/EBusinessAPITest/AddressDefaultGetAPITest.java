package EBusinessAPITest;

import APICommon.CommomConstants;
import APICommon.JsonUtility;
import WB2CCommon.CommonAssert;
import net.sf.json.JSONObject;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;


public class AddressDefaultGetAPITest {

    @Test
    public void addressDefaultGetAPITest() throws Exception {
        //prepare test data add a new address which is not default
        String postUrl = "http://54.222.195.248:8888/api/v1/burberry/address";
        StringEntity inputBody = new StringEntity("{\"memberId\":\"" + CommomConstants.memberID + "\",\"consignee\":\"Consignee\",\"consigneePhone\":\"Consignee Phone\",\"country\":\"中国\",\"province\":\"江苏省\",\"city\":\"苏州市\",\"area\":\"工业园区 \",\"address\":\"xxxxxx\",\"postalCode\":\"215000\",\"default\":111}");
        String postResult = JsonUtility.postJsonContent(postUrl, inputBody);
        if (postResult.contains("Mission accomplished")) {
            System.out.println("Post address API succeed,test pass!");
        } else
            CommonAssert.fail("Post address API get error, test fail!");
        //get new address id
        String getUrl = "http://54.222.195.248:8888/api/v1/burberry/address?memberId="+CommomConstants.memberID;
        String jsonStr = JsonUtility.getJsonContent(getUrl);
        JSONObject jsonObject = JsonUtility.jsonStrToJsonObject(jsonStr);
        String entity = jsonObject.getString("entity");
        String newAddressIdStr = entity.substring(406, 442);

        //patch default address to set the new address to default address
        String patchUrl = "http://54.222.195.248:8888/api/v1/burberry/address/default/" + newAddressIdStr;
        StringEntity patchInputBody = new StringEntity("{\"memberId\":\"" + CommomConstants.memberID + "\"}");
        String patchResult = JsonUtility.patchJsonContent(patchUrl, patchInputBody);
        if (patchResult.contains("Mission accomplished")) {
            System.out.println("Patch address API succeed,test pass!");
        } else
            CommonAssert.fail("Patch address API get error,test fail!");
        //request url
        String getDefaulturl = "http://54.222.195.248:8888/api/v1/burberry/address/default?memberId="+ CommomConstants.memberID ;
        String getDefaultJsonStr = JsonUtility.getJsonContent(getDefaulturl);
        JSONObject getDefaultJsonObject = JsonUtility.jsonStrToJsonObject(getDefaultJsonStr);
        String getDefaultEntity = getDefaultJsonObject.getString("entity");
        if (getDefaultEntity.contains("\"default\":1")) {
            System.out.println("Get default address API succeed,test pass!");
        } else
            CommonAssert.fail("Get default address API get error, test fail!");

        //delete new address
        String deleteUrl = "http://54.222.195.248:8888/api/v1/burberry/address/" + newAddressIdStr;
        String deleteResult = JsonUtility.deleteHttpRequest(deleteUrl);
        if (deleteResult.contains("Mission accomplished")) {
            System.out.println("Delete address API succeed,test pass!");
        } else
            CommonAssert.fail("Delete address API get error, test fail!");
    }
}
