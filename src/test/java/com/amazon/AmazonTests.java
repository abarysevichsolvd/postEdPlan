package com.amazon;

import com.amazon.constant.Constant;
import com.amazon.drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Properties;

public class AmazonTests {

    public WebDriver driver;
    public final String HOME_URL = Constant.HOME_URL;

    public void setDriver(String browser, String browserPath){
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getDriver(browserPath);
        if(browser.equals("safari")) driver.manage().window().maximize();
    }

    @Test(dataProvider = "browsers", dataProviderClass = BrowserDataProvider.class)
    public void validateSmokeForCartTest(String browser, String browserPath) {
        setDriver(browser, browserPath);
        driver.get(HOME_URL);
        driver.quit();
    }

}
