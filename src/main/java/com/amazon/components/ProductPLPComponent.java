package com.amazon.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPLPComponent extends AbstractComponent {

    @FindBy(xpath = ".//strong/a")
    private WebElement title;

    public ProductPLPComponent(WebElement context, WebDriver driver) {
        super(context, driver);
    }

    public String getTitle() {
        return title.getText();
    }
}
