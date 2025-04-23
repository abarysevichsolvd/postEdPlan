package com.luma.components;

import com.luma.utils.Commands;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MiniCart extends AbstractUIObject {

    @FindBy(xpath = ".//span[contains(text(),\"View and Edit Cart\")]/..")
    private WebElement viewAndEditCartElement;

    @FindBy(xpath = ".//strong[contains(@class, \"subtitle\")]")
    private WebElement emptyTitle;

    public MiniCart(SearchContext context, WebDriver driver) {
        super(context, driver);
    }

    public void openCart() {
        Commands.click(driver, viewAndEditCartElement);
    }

    public String getEmptyTitleText() {
        return emptyTitle.getText();
    }

}
