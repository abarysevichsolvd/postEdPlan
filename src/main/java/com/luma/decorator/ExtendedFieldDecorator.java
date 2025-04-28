package com.luma.decorator;

import com.luma.components.AbstractUIObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExtendedFieldDecorator extends DefaultFieldDecorator {

    private final Logger LOGGER = LogManager.getLogger(ExtendedFieldDecorator.class);

    private WebDriver driver;

    public ExtendedFieldDecorator(ElementLocatorFactory factory, WebDriver driver) {
        super(factory);
        this.driver = driver;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (AbstractUIObject.class.isAssignableFrom(field.getType())) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }
            try {
                return field.getType().getConstructor(new Class[]{SearchContext.class, WebDriver.class}).
                        newInstance(proxyForLocator(loader, locator), driver);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        if (List.class.isAssignableFrom(field.getType())) {
            Type type = field.getGenericType();
            if (type instanceof ParameterizedType) {
                Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];

                if (AbstractUIObject.class.isAssignableFrom((Class) actualTypeArgument)) {
                    Class uiObject = (Class) actualTypeArgument;
                    List<AbstractUIObject> instances = new ArrayList<>();
                    ElementLocator locator = factory.createLocator(field);
                    if (locator == null) {
                        return null;
                    }

                    List<WebElement> elements = proxyForListLocator(loader, locator);
                    if (elements.isEmpty() || elements == null) {
                        return null;
                    }

                    for (WebElement element : elements) {
                        try {
                            instances.add((AbstractUIObject) uiObject.getDeclaredConstructor(SearchContext.class, WebDriver.class)
                                    .newInstance(element, driver));
                        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                                 InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return instances;
                }

            }
        }
        return super.decorate(loader, field);
    }
}
