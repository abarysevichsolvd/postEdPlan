package com.luma.pages;

import com.luma.components.CookieModalComponent;
import com.luma.components.HeaderComponent;
import com.luma.constant.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public abstract class AbstractLumaPage extends AbstractPage {

    @FindBy(xpath = "//header/div[contains(@class, \"header\")]")
    private HeaderComponent header;

    @FindBy(xpath = "//div[@aria-label=\"Privacy\"]")
    private CookieModalComponent cookieModal;

    public AbstractLumaPage(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent getHeader() {
        return header;
    }

    public CookieModalComponent getCookieModal() {
        return cookieModal;
    }

    public void validateHomePageIsOpened() {
        Assert.assertEquals(driver.getCurrentUrl(), Constant.HOME_URL, "Url are not as expected");
    }

}
