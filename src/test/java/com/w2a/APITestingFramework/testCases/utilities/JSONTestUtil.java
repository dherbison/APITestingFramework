package com.w2a.APITestingFramework.testCases.utilities;

import com.w2a.APITestingFramework.listeners.ExtentListeners;
import org.json.JSONObject;
import org.testng.Assert;

public class JSONTestUtil {

    public static boolean hasKey(String json, String key) {
        ExtentListeners.testReport.get().info("Validating the presence of key \"" + key + "\"");
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.has(key);
    }

    public static String get(String json, String key) {
        Assert.assertTrue(hasKey(json, key), "Missing key \"" + key + "\" in: " + json);
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.get(key).toString();
    }

}
