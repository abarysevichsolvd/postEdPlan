package com.luma.utils;

import com.luma.constant.Constant;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Commands {

    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void click(WebDriver driver, WebElement element, long seconds) {
        WaitUtils.waitUntilElementExist(driver, element, seconds);
        WaitUtils.waitUntilClickable(driver, element, seconds);
        element.click();
    }

    public static void clickWithScroll(WebDriver driver, WebElement element, long seconds) {
        WaitUtils.waitUntilElementExist(driver, element, seconds);
        WaitUtils.waitUntilClickable(driver, element, seconds);
        scrollToElement(driver, element);
        element.click();
    }

    public static void clickWithScroll(WebDriver driver, WebElement element) {
        long explicit_timeout = Long.parseLong(Constant.PROPERTIES.getProperty("explicit_timeout"));
        clickWithScroll(driver, element, explicit_timeout);
    }

    public static void click(WebDriver driver, WebElement element) {
        long explicit_timeout = Long.parseLong(Constant.PROPERTIES.getProperty("explicit_timeout"));
        click(driver, element, explicit_timeout);
    }

    public static String clickElementByText(List<WebElement> elements, String text) {
        return elements.stream()
                .filter(e -> e.getText().equalsIgnoreCase(text))
                .findFirst()
                .map(e -> {
                    e.click();
                    return e.getText();
                })
                .orElseThrow(() -> new RuntimeException("Element with text '" + text + "' isn't present."));
    }

    public static String clickElementByAttribute(List<WebElement> elements, String attributeName, String attributeValue) {
        return elements.stream()
                .filter(e -> {
                    String attr = e.getAttribute(attributeName);
                    return attr != null && attr.equalsIgnoreCase(attributeValue);
                })
                .findFirst()
                .map(e -> {
                    e.click();
                    return e.getAttribute(attributeName);
                })
                .orElseThrow(() -> new RuntimeException("Element with attribute '" + attributeName + "'='" + attributeValue + "' isn't present."));
    }

    public static String clickElementByAttributeOptionLabel(List<WebElement> elements, String attributeValue) {
        return clickElementByAttribute(elements, "option-label", attributeValue);
    }

    public static void hoverOverElement(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public static void clickElementByJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
}
