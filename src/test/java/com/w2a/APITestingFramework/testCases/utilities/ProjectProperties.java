package com.w2a.APITestingFramework.testCases.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
    private Properties config;

    public ProjectProperties() {
        config = new Properties();
        try (FileInputStream fis = new FileInputStream("./src/test/resources/config.properties")) {
            config.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Properties getConfig() {
        return config;
    }
}
