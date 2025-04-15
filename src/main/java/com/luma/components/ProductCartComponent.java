package com.luma.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductCartComponent extends AbstractComponent{

    @FindBy(xpath = ".//strong/a")
    protected WebElement titleElement;

    @FindBy(xpath = ".//td[@data-th=\"Price\"]/span")
    private WebElement priceElement;

    @FindBy(xpath = ".//dt[contains(text(),\"Size\")]/following-sibling::dd[1]")
    private WebElement sizeElement;

    @FindBy(xpath = ".//dt[contains(text(),\"Color\")]/following-sibling::dd[1]")
    private WebElement colorElement;

    public ProductCartComponent(WebElement context, WebDriver driver) {
        super(context, driver);
    }

    public String getTitleText() {
        return titleElement.getText();
    }

    public String getPriceText() {
        return priceElement.getText();
    }

    public String getSizeText() {
        return sizeElement.getText();
    }

    public String getColorText() {
        return colorElement.getText();
    }

}
