package com.amazon.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class WebDriverFactory {

    public Properties loadPropertiesFromFile(String configFilePath) {
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
        return properties;
    }

    public Properties loadPropertiesFromFile() {
        return loadPropertiesFromFile("src/main/resources/browser_config.properties");
    }


    public void setCapabilities(Properties prop, AbstractDriverOptions options) {
        prop.forEach((key, value) -> {
            if(String.valueOf(key).contains(".")){
                if(String.valueOf(key).split("\\.")[0].equals("capabilities")){
                    options.setCapability((String) String.valueOf(key).split("\\.")[1], value);
                }
            }
        });
    }

    public void setChromeArguments(Properties prop, ChromeOptions options) {
        prop.forEach((key, value) -> {
            if(String.valueOf(key).contains(".")){
                if(String.valueOf(key).split("\\.")[0].equals("arguments")){
                    options.addArguments((String) value);
                }
            }
        });
    }

    public WebDriver findDriver(Properties prop) {
        WebDriver webDriver = null;
        Optional<String> browserNameOpt = Optional.of(prop.getProperty("capabilities.browserName"));
        String browser = browserNameOpt.get().toLowerCase();

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                setCapabilities(prop, chromeOptions);
                setChromeArguments(prop, chromeOptions);
                webDriver = new ChromeDriver(chromeOptions);
                break;
            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                setCapabilities(prop, safariOptions);
                webDriver = new SafariDriver(safariOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browser);
        }
        return webDriver;
    }

    public WebDriver getDriver() {
        Properties properties = loadPropertiesFromFile();
        WebDriver driver = findDriver(properties);
        return driver;
    }

    public WebDriver getDriver(String path) {
        Properties properties = loadPropertiesFromFile(path);
        WebDriver driver = findDriver(properties);
        return driver;
    }
}
