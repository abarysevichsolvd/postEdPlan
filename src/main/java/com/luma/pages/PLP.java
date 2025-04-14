package com.luma.pages;

import com.luma.utils.WaitUtils;
import com.luma.components.ProductPLPComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class PLP extends AbstractPage {

    private final String PRODUCT_XPATH = "//ol[contains(@class, 'products list')]//li";
    @FindBy(xpath = PRODUCT_XPATH)
    private List<WebElement> products;
    private List<ProductPLPComponent> productPLPList;

    public PLP(WebDriver driver) {
        super(driver);
    }

    public List<ProductPLPComponent> getProductPLPList() {
        WaitUtils.waitUntilElementExist(driver, PRODUCT_XPATH, 3);
        this.productPLPList = products.stream().map(webElement -> {
            return new ProductPLPComponent(webElement, driver);
        }).collect(Collectors.toList());
        return productPLPList;
    }

}
