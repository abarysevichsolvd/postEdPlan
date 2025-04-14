package com.luma.pages;

import com.luma.components.CookieModalComponent;
import com.luma.components.HeaderComponent;
import com.luma.constant.Constant;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected final Logger logger = Logger.getLogger(AbstractPage.class);

    @FindBy(xpath = "//header/div[contains(@class, \"header\")]")
    private WebElement headerElem;
    private HeaderComponent header;

    @FindBy(xpath = "//div[@aria-label=\"Privacy\"]")
    private WebElement cookieModalElement;
    private CookieModalComponent cookieModal;


    public AbstractPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.header = new HeaderComponent(headerElem, driver);
        this.cookieModal = new CookieModalComponent(cookieModalElement, driver);
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
