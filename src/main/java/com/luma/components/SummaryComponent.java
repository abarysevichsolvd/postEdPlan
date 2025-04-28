package com.luma.components;

import com.luma.utils.WaitUtils;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SummaryComponent extends AbstractUIObject {

    @FindBy(xpath = ".//th[contains(text(), \"Subtotal\")]/following-sibling::td") //
    private WebElement subtotalElement;

    public SummaryComponent(SearchContext searchContext, WebDriver driver) {
        super(searchContext, driver);
    }

    public String getSubtotalText() {
        WaitUtils.waitUntilElementExist(driver, subtotalElement, 3);
        return subtotalElement.getText().trim();
    }

}
