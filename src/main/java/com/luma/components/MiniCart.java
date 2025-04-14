package com.luma.components;

import com.luma.utils.Commands;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MiniCart extends AbstractComponent{

    @FindBy(xpath = ".//span[contains(text(),\"View and Edit Cart\")]/..")
    private WebElement viewAndEditCartElement;

    public MiniCart(WebElement context, WebDriver driver) {
        super(context, driver);
    }

    public void openCart(){
        Commands.click(driver, viewAndEditCartElement);
    }

}
