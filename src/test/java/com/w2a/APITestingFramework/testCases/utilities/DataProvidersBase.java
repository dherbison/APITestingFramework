package com.w2a.APITestingFramework.testCases.utilities;

import com.w2a.APITestingFramework.testCases.exception.MissingTestException;
import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

public class DataProvidersBase {
    protected Properties config;
    protected ExcelReader excel;
    protected String currentSheetName;
    protected String currentWorkBook;

    public DataProvidersBase() {
        ProjectProperties projectProperties = new ProjectProperties();
        config = projectProperties.getConfig();
        currentSheetName = config.getProperty("sheetName");
        currentWorkBook = config.getProperty("workBookPath");
        excel = new ExcelReader(this.currentWorkBook);
    }

    public DataProvidersBase(String currentWorkBook) {
        ProjectProperties projectProperties = new ProjectProperties();
        config = projectProperties.getConfig();
        this.currentWorkBook = currentWorkBook;
        currentSheetName = config.getProperty("sheetName");
        excel = new ExcelReader(this.currentWorkBook);
    }

    public DataProvidersBase(String currentWorkBook, String currentSheetName) {
        ProjectProperties projectProperties = new ProjectProperties();
        config = projectProperties.getConfig();
        this.currentWorkBook = currentWorkBook;
        this.currentSheetName = currentSheetName;
        excel = new ExcelReader(this.currentWorkBook);
    }

    @DataProvider(name = "dataProvider", parallel = true)
    public Object[][] getData(Method m) throws MissingTestException {
        System.out.println("Inside dataProvider");
        System.out.println("SheetName-->" + currentSheetName);
        int rows = excel.getRowCount(currentSheetName);
        String testName = m.getName();
        int testCaseRowNum = 1;
        boolean foundTest = false;
        for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {
            String testCaseName = excel.getCellData(currentSheetName, 0, testCaseRowNum);
            if (testCaseName.equalsIgnoreCase(testName)) {
                foundTest = true;
                break;
            }
        }// Checking total rows in test case
        if (!foundTest) {
            throw new MissingTestException(testName);
        }
        int dataStartRowNum = testCaseRowNum + 2;//dataStartRowNum=8
        int testRows = 0;
        while (!excel.getCellData(currentSheetName, 0, dataStartRowNum + testRows).equalsIgnoreCase("endOfTestData")) {
            testRows++;
        }
        // Checking total cols in test case
        int colStartColNum = testCaseRowNum + 1;//7
        int testCols = 0;
        while (!excel.getCellData(currentSheetName, testCols, colStartColNum).equals("")) {
            testCols++;
        }
        Object[][] data = new Object[testRows][1];
        int i = 0;
        for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {
            Hashtable<String, String> table = new Hashtable<String, String>();
            for (int cNum = 0; cNum < testCols; cNum++) {
                String colName = excel.getCellData(currentSheetName, cNum, colStartColNum);
                String testData = excel.getCellData(currentSheetName, cNum, rNum);
                table.put(colName, testData);
            }
            data[i][0] = table;
            i++;
        }
        return data;
    }
}
