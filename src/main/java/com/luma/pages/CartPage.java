package com.luma.pages;

import com.luma.components.ProductCartComponent;
import com.luma.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends AbstractPage {

    private final String PRODUCT_CART_XPATH = "//tbody[contains(@class, \"item\")]";
    @FindBy(xpath = PRODUCT_CART_XPATH)
    private List<WebElement> productCartElements;
    private List<ProductCartComponent> productCartComponents;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCartComponent> getProductCartComponents() {
        WaitUtils.waitUntilElementExist(driver, PRODUCT_CART_XPATH, 3);
        return productCartElements.stream()
                .map(element -> new ProductCartComponent(element, driver))
                .collect(Collectors.toList());
    }

}
