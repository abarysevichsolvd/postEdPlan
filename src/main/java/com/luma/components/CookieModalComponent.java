package com.luma.components;

import com.luma.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CookieModalComponent extends AbstractComponent {

    @FindBy(xpath = ".//button[@id=\"accept-btn\"]")
    private WebElement agreeBtn;

    public CookieModalComponent(WebElement context, WebDriver driver) {
        super(context, driver);
    }

    public void clickAgreeBtn() {
        WaitUtils.waitUntilClickable(driver, agreeBtn, 3);
        agreeBtn.click();
    }

}
