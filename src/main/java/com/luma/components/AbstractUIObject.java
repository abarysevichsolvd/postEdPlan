package com.luma.components;

import com.luma.decorator.ExtendedFieldDecorator;
import org.apache.log4j.Logger;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class AbstractUIObject {

    @FindBy(xpath = ".")
    protected WebElement root;

    protected WebDriver driver;
    private final SearchContext searchContext;
    protected final Logger logger = Logger.getLogger(AbstractUIObject.class);

    public AbstractUIObject(SearchContext searchContext, WebDriver driver) {
        this.driver = driver;
        this.searchContext = searchContext;
        PageFactory.initElements(new ExtendedFieldDecorator(new DefaultElementLocatorFactory(searchContext), driver), this);
    }

    public SearchContext getSearchContext() {
        return searchContext;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public boolean isRootElementPresent() {
        return root.isDisplayed();
    }

    public WebElement getRootElement() {
        return root;
    }

}
