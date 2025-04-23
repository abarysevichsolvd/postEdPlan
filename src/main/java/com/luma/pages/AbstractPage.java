package com.luma.pages;

import com.luma.components.AbstractUIObject;
import com.luma.decorator.ExtendedFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class AbstractPage extends AbstractUIObject {

    public AbstractPage(WebDriver driver) {
        super(driver, driver);
        PageFactory.initElements(
                new ExtendedFieldDecorator(new DefaultElementLocatorFactory(driver), driver), this);
    }

}
