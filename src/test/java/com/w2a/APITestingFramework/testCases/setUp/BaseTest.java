package com.w2a.APITestingFramework.testCases.setUp;

import com.github.javafaker.Faker;
import com.w2a.APITestingFramework.testCases.utilities.ExcelReader;
import com.w2a.APITestingFramework.testCases.utilities.ProjectProperties;
import io.restassured.RestAssured;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.util.Properties;

public class BaseTest {
    protected Faker faker;
    protected String currentSheetName;
    protected ExcelReader excelReader;
    protected Properties config;
    protected ExcelReader excel;

    public BaseTest() {
        currentSheetName = "testData";
        excel = new ExcelReader("./src/test/resources/CreateCustomerTest.xlsx");
    }

    @BeforeTest
    public void setUp() {
        ProjectProperties projectProperties = new ProjectProperties();
        config = projectProperties.getConfig();
        faker = Faker.instance();
        RestAssured.baseURI = config.getProperty("URL_BASE");
        RestAssured.basePath = config.getProperty("URL_BASE_PATH");
    }

    @AfterTest
    public void tearDown() {

    }

    protected void badDataError(String fieldData) {
        System.out.println("Bad data (" + fieldData + ") in excel sheet: " + excelReader.path + " sheet " + currentSheetName);
    }
}
