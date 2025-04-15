package com.luma.components;

import com.luma.utils.Commands;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductPLPComponent extends AbstractComponent {

    @FindBy(xpath = ".//strong/a")
    protected WebElement titleElement;

    @FindBy(xpath = ".//span[contains(@id,\"product-price\")]")
    private WebElement priceElement;

    @FindBy(xpath = ".//div[contains(@id,\"size\")]")
    private List<WebElement> sizeElements;

    @FindBy(xpath = ".//div[contains(@id,\"color\")]")
    private List<WebElement> colorElements;

    @FindBy(xpath = ".//span[contains(text(),\"Add to Cart\")]/..")
    private WebElement addToCartButtonElement;


    public ProductPLPComponent(WebElement context, WebDriver driver) {
        super(context, driver);
    }

    public void scrollToProduct(){
        Commands.scrollToElement(driver, titleElement);
    }

    public String getTitleText() {
        return titleElement.getText();
    }

    public void hoverTitle() {
        Commands.hoverOverElement(driver, titleElement);
    }

    public String getPriceText() {
        return priceElement.getText();
    }

    public boolean allElementsPresent() {
        return titleElement.isDisplayed()
                && priceElement.isDisplayed()
                && !colorElements.isEmpty()
                && !sizeElements.isEmpty();
    }

    public boolean isSizeSelected(WebElement sizeElement) {
        return sizeElement.getAttribute("class").contains("selected");
    }

    public boolean isColorSelected(WebElement colorElement) {
        return colorElement.getAttribute("class").contains("selected");
    }

    public String clickSizeByTextIfNotSelected(String sizeText) {
        WebElement size = findSize(sizeElements, sizeText);
        if(!isSizeSelected(size)) return  Commands.clickElementByText(sizeElements, sizeText);
        return sizeText;
    }

    public String clickColorByTextIfNotSelected(String colorText) {
        WebElement color = findColor(colorElements, colorText);
        if(!isColorSelected(color)) return  Commands.clickElementByAttributeOptionLabel(colorElements, colorText);
        return colorText;

    }

    public WebElement findSize(List<WebElement> sizeElements, String text){
        return sizeElements.stream().filter(size->size.getText().equals(text)).findFirst().get();
    }

    public WebElement findColor(List<WebElement> sizeElements, String text){
        return colorElements.stream().filter(color->color.getAttribute("option-label").equals(text)).findFirst().get();
    }

    public List<String> getSizes() {
        return sizeElements.stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }

    public List<String> getColors() {
        return colorElements.stream()
                .map(e -> e.getAttribute("option-label"))
                .filter(Objects::nonNull)  // Фильтруем null значения
                .map(String::trim)         // Убираем лишние пробелы
                .filter(s -> !s.isEmpty()) // Фильтруем пустые строки
                .collect(Collectors.toList());
    }

    public void clickAddToCartButton(){
        hoverTitle();
        Commands.click(driver, addToCartButtonElement, 7);
    }

}

