package com.amazon;

import com.amazon.drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Properties;

public class AmazonTests {

    public WebDriver driver;
    public String url;

    @BeforeTest
    public void setupBeforeTest(){
        WebDriverFactory factory = new WebDriverFactory();
        url = factory.loadPropertiesFromFile().getProperty("home_page_url");
        driver = factory.getDriver();
    }

    @AfterTest
    public void setupAfterTest(){
        driver.quit();
    }

    @Test
    public void validateSmokeForCartTest() {
        driver.get(url);
    }

}
