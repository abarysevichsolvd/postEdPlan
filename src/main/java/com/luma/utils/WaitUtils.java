package com.luma.utils;

import com.luma.components.AbstractUIObject;
import com.luma.components.HeaderComponent;
import com.luma.constant.Constant;
import com.luma.pages.CartPage;
import com.luma.pages.PLP;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

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
        waiter.until(a -> {
            return element.isDisplayed();
        });
    }

    public static void waitUntilElementExist(WebDriver driver, String xpath, long seconds) {
        WebDriverWait waiter = createWebDriverWait(driver, seconds);
        waiter.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    public static void waitUntilAmountOfProductExist(WebDriver driver, HeaderComponent header, String text, long seconds) {
        WebDriverWait waiter = createWebDriverWait(driver, seconds);
        waiter.until(a -> {
            return header.getAmountOfProduct().contains(text);
        });
    }

    public static void waitUntilProductPLPListExist(WebDriver driver, long seconds) {
        WebDriverWait waiter = createWebDriverWait(driver, seconds);
        waiter.withMessage("Product PLP List Components aren't exists");
        waiter.until(a -> {
            PLP plp = new PLP(driver);
            return plp.getProductPLPList() != null && !plp.getProductPLPList().isEmpty();
        });
    }

    public static void waitUntilProductCartListExist(WebDriver driver, long seconds) {
        WebDriverWait waiter = createWebDriverWait(driver, seconds);
        waiter.withMessage("Product PLP List Components aren't exists");
        waiter.until(a -> {
            CartPage cardPage = new CartPage(driver);
            return cardPage.getProductCartComponents() != null && !cardPage.getProductCartComponents().isEmpty();
        });
    }

    public static void waitUntilProductCartListExist(WebDriver driver, int sizeOfList, long seconds) {
        WebDriverWait waiter = createWebDriverWait(driver, seconds);
        waiter.withMessage("Product PLP List Components aren't exists");
        waiter.until(a -> {
            CartPage cardPage = new CartPage(driver);
            return cardPage.getProductCartComponents() != null && cardPage.getProductCartComponents().size() == sizeOfList;
        });
    }

    public static void waitUntilComponentExist(WebDriver driver, AbstractUIObject component, long seconds) {
        WebDriverWait waiter = createWebDriverWait(driver, seconds);
        waiter.until(a -> {
            return component != null && component.getRootElement().isDisplayed();
        });
    }

    public static void waitUntilComponentExist(WebDriver driver, AbstractUIObject component) {
        waitUntilComponentExist(driver, component, Long.parseLong(Constant.PROPERTIES.getProperty("explicit_timeout")));
    }

//    public static void waitUntilExist(WebDriver driver, Function<? super WebDriver, T> condition  , long seconds) {
//        WebDriverWait waiter = createWebDriverWait(driver, seconds);
//        waiter.withMessage("Product PLP List Components aren't exists");
//        waiter.until(condition);
//    }

}
