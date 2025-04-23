package com.luma.pages;

import com.luma.constant.Constant;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractLumaPage {

    private HomePage(WebDriver driver) {
        super(driver);
    }

//    public void openHomePage() {
//        driver.get(Constant.HOME_URL);
//
//    }

    public static HomePage openHomePage(WebDriver driver) {
        driver.get(Constant.HOME_URL);
        return new HomePage(driver);
    }

    public void agreeCookie() {
        getCookieModal().clickAgreeBtn();
    }

}
