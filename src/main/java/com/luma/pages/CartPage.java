package com.luma.pages;

import com.luma.components.ProductCartComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractLumaPage {

    @FindBy(xpath = "//tbody[contains(@class, \"item\")]")
    private List<ProductCartComponent> productCartComponents;

    @FindBy(xpath = "//div[contains(@class, \"empty\")]/p[1]")
    private WebElement emptyCardTitle;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCartComponent> getProductCartComponents() {
        return productCartComponents;
    }

    public boolean isEmptyCardPresent() {
        return emptyCardTitle.isDisplayed();
    }

    public String getEmptyCardTitle() {
        return emptyCardTitle.getText();
    }

}
