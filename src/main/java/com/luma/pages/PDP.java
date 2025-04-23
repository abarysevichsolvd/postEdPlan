package com.luma.pages;

import com.luma.components.ProductPDPComponent;
import com.luma.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class PDP extends AbstractLumaPage {

    @FindBy(xpath = "//div[contains(@class,\"product-info-main\")]")
    private ProductPDPComponent productPDPComponent;

    public PDP(WebDriver driver) {
        super(driver);
    }

    public ProductPDPComponent getProductPDPComponent() {
        WaitUtils.waitUntilElementExist(driver, productPDPComponent.getRootElement(), 3);
        productPDPComponent.scrollToProduct();
        return productPDPComponent;
    }

}
