package com.luma.components;

import com.luma.annotations.WaitForComponents;
import com.luma.utils.Commands;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductCartComponent extends AbstractUIObject {

    @FindBy(xpath = ".//strong/a")
    protected WebElement titleElement;

    @FindBy(xpath = ".//td[@data-th=\"Price\"]/span")
    private WebElement priceElement;

    @FindBy(xpath = ".//dt[contains(text(),\"Size\")]/following-sibling::dd[1]")
    private WebElement sizeElement;

    @FindBy(xpath = ".//dt[contains(text(),\"Color\")]/following-sibling::dd[1]")
    private WebElement colorElement;

    @FindBy(xpath = ".//a[contains(@title, \"Remove\")]")
    private WebElement removeButton;

    public ProductCartComponent(SearchContext context, WebDriver driver) {
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

    public void deleteProduct(){
        Commands.clickElementByJS(driver,removeButton);
//        removeButton.click();
    }

}
