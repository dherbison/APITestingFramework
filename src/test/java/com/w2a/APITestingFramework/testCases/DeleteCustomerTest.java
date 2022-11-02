package com.w2a.APITestingFramework.testCases;

import com.w2a.APITestingFramework.APIs.CustomerAPI;
import com.w2a.APITestingFramework.listeners.ExtentListeners;
import com.w2a.APITestingFramework.testCases.setUp.BaseTest;
import com.w2a.APITestingFramework.testCases.utilities.CustomerDataProvider;
import com.w2a.APITestingFramework.testCases.utilities.DataProvidersBase;
import com.w2a.APITestingFramework.testCases.utilities.JSONTestUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class DeleteCustomerTest extends BaseTest {

    @Test(dataProviderClass = CustomerDataProvider.class, dataProvider = "dataProvider")
    public void validateDeleteCustomerAPIWithValidSecretKey(Hashtable<String, String> map) {
        Response response = CustomerAPI.add(map);
        String json = response.asString();
        Hashtable<String, String> mapToDelete = new Hashtable<String, String>();
        mapToDelete.put("id",JSONTestUtil.get(json,"id"));
        ExtentListeners.testReport.get().info(map.toString());
        Assert.assertEquals(response.statusCode(), 200);

        Response response2 = CustomerAPI.get(mapToDelete);
        String json2 = response2.asString();
        Assert.assertEquals(JSONTestUtil.get(json,"id"), JSONTestUtil.get(json2,"id"));

        Response response3 = CustomerAPI.delete(mapToDelete);
        String json3 = response3.asString();
        Assert.assertEquals(JSONTestUtil.get(json,"id"), JSONTestUtil.get(json3,"id"));

//        // another way...
//        JSONObject jsonObject = new JSONObject(response.asString());
//        jsonObject.has("id");

    }

    @Test(dataProviderClass = DataProvidersBase.class, dataProvider = "dataProvider")
    public void validateDeleteCustomerAPIWithInValidSecretKey(Hashtable<String, String> map) {
        Response response = CustomerAPI.add(map);
        JsonPath body = response.body().jsonPath();
        Hashtable<String, String> mapToDelete = new Hashtable<String, String>();
        mapToDelete.put("id", body.getString("id"));
        Response response2 = CustomerAPI.get(mapToDelete);
        Response response3 = CustomerAPI.delete(mapToDelete, false);
        // Add data to report
        // YOU HAVE TO RUN THE testng.xml file or this will fail!!!!
        ExtentListeners.testReport.get().info(map.toString());
        response.prettyPrint();
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(), 401);
    }

}
