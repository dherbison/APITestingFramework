package com.w2a.APITestingFramework.testCases.utilities;

import com.w2a.APITestingFramework.listeners.ExtentListeners;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Listeners implements ITestListener {
    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        Reporter.log("Test passed: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {

        ExtentListeners.testReport.get().info("Test failed: " + result.getName() + ", status " + result.getStatus());
        Reporter.log("Test failed: " + result.getName());
        Reporter.log("<a href=\"error.jpg\" target=\"_blank\">Screen Shot</a>");
        Reporter.log("<br/>");
        Reporter.log("<a href=\"error.jpg\" target=\"_blank\"><img src=\"C:\\documents\\Icons\\homer2.jpg\"/></a>");
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
    }

}
