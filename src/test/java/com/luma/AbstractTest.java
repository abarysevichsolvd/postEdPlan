package com.luma;

import com.luma.drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class AbstractTest {

    public WebDriver getDriver() {
        WebDriverFactory factory = new WebDriverFactory();
        return factory.getDriver();
    }

    public WebDriver getDriver(Properties prop) {
        WebDriverFactory factory = new WebDriverFactory();
        return factory.getDriver(prop);
    }

}
