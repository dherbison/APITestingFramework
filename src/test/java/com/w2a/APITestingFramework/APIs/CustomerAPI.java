package com.w2a.APITestingFramework.APIs;

import io.restassured.response.Response;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class CustomerAPI extends BaseAPI {
    public static Response add(Hashtable<String, String> map) {
        return _add(map, true);
    }

    public static Response add(Hashtable<String, String> map, boolean validAuthKey) {
        return _add(map, validAuthKey);
    }

    private static Response _add(Hashtable<String, String> map, boolean validAuthKey) {
        Response response = given().auth().basic(validAuthKey ? API_KEY : faker.name().lastName(), "")
                .formParam("email", faker.internet().emailAddress())
                .formParam("description", map.get("description"))
                .post(URL_CUSTOMER);
        return response;
    }

    public static Response delete(Hashtable<String, String> map) {
        return _delete(map, true);
    }

    public static Response delete(Hashtable<String, String> map, boolean validAuthKey) {
        return _delete(map, validAuthKey);
    }

    private static Response _delete(Hashtable<String, String> map, boolean validAuthKey) {
        Response response = given().auth().basic(validAuthKey ? API_KEY : faker.name().lastName(), "")
                .delete(URL_CUSTOMER + "/" + map.get("id"));
        return response;
    }
    public static Response get(Hashtable<String, String> map) {
        return _get(map, true);
    }

    public static Response get(Hashtable<String, String> map, boolean validAuthKey) {
        return _get(map, validAuthKey);
    }

    private static Response _get(Hashtable<String, String> map, boolean validAuthKey) {
        Response response = given().auth().basic(validAuthKey ? API_KEY : faker.name().lastName(), "")
                .get(URL_CUSTOMER + "/" + map.get("id"));
        while (response.statusCode() != 200) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            response = given().auth().basic(validAuthKey ? API_KEY : faker.name().lastName(), "")
                    .get(URL_CUSTOMER + "/" + map.get("id"));

        }
        return response;
    }

}
