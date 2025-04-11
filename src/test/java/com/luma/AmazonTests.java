package com.luma;

import com.luma.components.HeaderComponent;
import com.luma.components.ProductPLPComponent;
import com.luma.drivers.WebDriverFactory;
import com.luma.pages.HomePage;
import com.luma.pages.PLP;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class AmazonTests {

    public WebDriver driver;
    private final Logger logger = Logger.getLogger(AmazonTests.class);

    public void setDriver(String browser, String browserPath) {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getDriver(browserPath);
        if (browser.equals("safari")) driver.manage().window().maximize();
    }

    @Test(dataProvider = "browsers", dataProviderClass = BrowserDataProvider.class)
    public void validateSmokeForCartTest(String browser, String browserPath) {
        setDriver(browser, browserPath);
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.agreeCookie();

        HeaderComponent header = homePage.getHeader();
        header.searchForRandomPhraze();

        PLP plp = new PLP(driver);
        List<ProductPLPComponent> products = plp.getProductPLPList();
        assertTrue(products.size() > 0, "page contains empty list of results");
        products.forEach(product -> {
            logger.info("..... " + product.getTitle().trim());
        });

        driver.quit();
    }

}
