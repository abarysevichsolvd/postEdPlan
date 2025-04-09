package com.amazon;

import com.amazon.constant.Constant;
import com.amazon.drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class AmazonTests {

    public WebDriver driver;
    public final String HOME_URL = Constant.HOME_URL;

    public void setDriver(String browser, String browserPath) {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getDriver(browserPath);
        if (browser.equals("safari")) driver.manage().window().maximize();
    }

    @Test(dataProvider = "browsers", dataProviderClass = BrowserDataProvider.class)
    public void validateSmokeForCartTest(String browser, String browserPath) {
        setDriver(browser, browserPath);
        driver.get(HOME_URL);
        driver.quit();
    }

}
