package com.w2a.APITestingFramework.testCases.exception;

public class MissingTestException extends Exception {
    public MissingTestException(String testName) {
        super("Missing test case: " + testName);
    }
}
