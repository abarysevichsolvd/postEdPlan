package com.luma.pages;

import com.luma.components.ProductPLPComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PLP extends AbstractLumaPage {

    @FindBy(xpath = "//ol[contains(@class, 'products list')]//li")
    private List<ProductPLPComponent> productPLPList;

    public PLP(WebDriver driver) {
        super(driver);
    }

    public List<ProductPLPComponent> getProductPLPList() {
        return productPLPList;
    }

}
