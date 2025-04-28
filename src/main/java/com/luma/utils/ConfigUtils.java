package com.luma.utils;

import com.luma.drivers.WebDriverFactory;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {

    private static final Logger logger = Logger.getLogger(WebDriverFactory.class);
    private static final String DEFAULT_PATH = "src/main/resources/config.properties";
    public static final Properties PROPERTIES;

    static {
        PROPERTIES = loadPropertiesFromFile(getPath());
    }

    private ConfigUtils() {
    }

    private static String getPath() {
        String configPath = System.getProperty("config.path");
        if (configPath == null || configPath.isEmpty()) {
            configPath = DEFAULT_PATH;
        }
        return configPath;
    }

    private static Properties loadPropertiesFromFile(String configFilePath) {
        logger.info("...Starting read config file located by path = " + configFilePath);
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(configFilePath);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        properties.forEach((key, value) -> {
            logger.info(String.format("...Capability has been loaded %s=%s", key, value));
        });
        return properties;
    }

}
