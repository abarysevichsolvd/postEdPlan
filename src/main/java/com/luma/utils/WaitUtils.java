package com.luma.utils;

import com.luma.components.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static WebDriverWait createWebDriverWait(WebDriver driver, long seconds) {
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return waiter;
    }

    public static void waitUntilClickable(WebDriver driver, WebElement element, long seconds) {
        WebDriverWait waiter = createWebDriverWait(driver, seconds);
        waiter.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementExist(WebDriver driver, WebElement element, long seconds) {
        WebDriverWait waiter = createWebDriverWait(driver, seconds);
        waiter.until(a->{return element.isDisplayed();});
    }

    public static void waitUntilElementExist(WebDriver driver, String xpath, long seconds) {
        WebDriverWait waiter = createWebDriverWait(driver, seconds);
        waiter.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    public static void waitUntilAmountOfProductExist(WebDriver driver, HeaderComponent header, String text, long seconds){
        WebDriverWait waiter = createWebDriverWait(driver, seconds);
        waiter.until(a->{return header.getAmountOfProduct().contains(text);});
    }

}
