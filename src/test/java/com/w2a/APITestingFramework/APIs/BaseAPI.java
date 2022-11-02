package com.w2a.APITestingFramework.APIs;

import com.github.javafaker.Faker;
import com.w2a.APITestingFramework.testCases.utilities.ProjectProperties;
import io.restassured.RestAssured;
import java.util.Properties;

public class BaseAPI {
    protected static String API_KEY;
    protected static String URL_CUSTOMER;
    protected static Faker faker;
    protected static Properties config;

    static {
        ProjectProperties projectProperties = new ProjectProperties();
        config = projectProperties.getConfig();
        faker = Faker.instance();
        URL_CUSTOMER = config.getProperty("URL_CUSTOMER");
        API_KEY = config.getProperty("API_KEY");
    }

    public BaseAPI() {
        RestAssured.baseURI = config.getProperty("URL_BASE");
        RestAssured.basePath = config.getProperty("URL_BASE_PATH");
    }
}