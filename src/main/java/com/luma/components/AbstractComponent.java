package com.luma.components;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class AbstractComponent {

    protected WebDriver driver;
    protected final Logger logger = Logger.getLogger(AbstractComponent.class);

    public AbstractComponent(WebElement context, WebDriver driver) {
        PageFactory.initElements(new DefaultElementLocatorFactory(context), this);
        this.driver = driver;
    }

}
