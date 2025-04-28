package com.luma.pages;

import com.luma.components.ProductPLPComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PLP extends AbstractLumaPage {

    @FindBy(xpath = "//ol[contains(@class, 'products list')]//li")
    private List<ProductPLPComponent> productPLPList;

    @FindBy(xpath = "//select[@id=\"sorter\"]")
    private WebElement sortSelect;

    public PLP(WebDriver driver) {
        super(driver);
    }

    public List<ProductPLPComponent> getProductPLPList() {
        return productPLPList;
    }

    public void sortBy(String text) {
        Select select = new Select(sortSelect);
        select.selectByValue(text);
    }

}
