package com.luma;

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

    public static void waitUntilElementExist(WebDriver driver, String xpath, long seconds) {
        WebDriverWait waiter = createWebDriverWait(driver, seconds);
        waiter.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
    }

}
