package com.luma.drivers;

import com.luma.utils.ConfigUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Optional;
import java.util.Properties;

public class WebDriverFactory {

    private final Logger logger = Logger.getLogger(WebDriverFactory.class);

    private void setCapabilities(Properties prop, AbstractDriverOptions options) {
        prop.forEach((key, value) -> {
            if (String.valueOf(key).contains(".")) {
                if (String.valueOf(key).split("\\.")[0].equals("capabilities")) {
                    options.setCapability((String) String.valueOf(key).split("\\.")[1], value);
                }
            }
        });
    }

    private void setChromeArguments(Properties prop, ChromeOptions options) {
        prop.forEach((key, value) -> {
            if (String.valueOf(key).contains(".")) {
                if (String.valueOf(key).split("\\.")[0].equals("arguments")) {
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
        WebDriver driver = findDriver(ConfigUtils.PROPERTIES);
        return driver;
    }

    public WebDriver getDriver(Properties prop) {
        WebDriver driver = findDriver(prop);
        return driver;
    }

}
