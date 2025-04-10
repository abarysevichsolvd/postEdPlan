package com.amazon.pages;

import com.amazon.components.ProductPLPComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class PLP extends AbstractPage {

    @FindBy(xpath = "//ol[contains(@class, 'products list')]//li")
    private List<WebElement> products;
    private List<ProductPLPComponent> productPLPList;

    public PLP(WebDriver driver) {
        super(driver);
        this.productPLPList = products.stream().map(webElement -> {
            return new ProductPLPComponent(webElement, driver);
        }).collect(Collectors.toList());
    }

    public List<ProductPLPComponent> getProductPLPList() {
        return productPLPList;
    }

}
