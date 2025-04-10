package com.amazon.pages;

import com.amazon.constant.Constant;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        driver.get(Constant.HOME_URL);
    }

    public void agreeCookie() {
        getCookieModal().clickAgreeBtn();
    }

}
