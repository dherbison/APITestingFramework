package com.w2a.APITestingFramework.testCases;

import com.w2a.APITestingFramework.APIs.CustomerAPI;
import com.w2a.APITestingFramework.listeners.ExtentListeners;
import com.w2a.APITestingFramework.testCases.setUp.BaseTest;
import com.w2a.APITestingFramework.testCases.utilities.CustomerDataProvider;
import com.w2a.APITestingFramework.testCases.utilities.DataProvidersBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class CreateCustomerTest extends BaseTest {

    @Test(dataProviderClass = CustomerDataProvider.class, dataProvider = "dataProvider")
    public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String, String> map) {
        Response response = CustomerAPI.add(map);
        // Add data to report
        // YOU HAVE TO RUN THE testng.xml file or this will fail!!!!
        ExtentListeners.testReport.get().info(map.toString());
        response.prettyPrint();
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(dataProviderClass = DataProvidersBase.class, dataProvider = "dataProvider")
    public void validateCreateCustomerAPIWithInValidSecretKey(Hashtable<String, String> map) {
        Response response = CustomerAPI.add(map, false);
        // Add data to report
        // YOU HAVE TO RUN THE testng.xml file or this will fail!!!!
        ExtentListeners.testReport.get().info(map.toString());
        response.prettyPrint();
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(), 401);
    }
}
