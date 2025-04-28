package com.luma.components;

import com.luma.constant.Constant;
import com.luma.utils.Commands;
import com.luma.utils.WaitUtils;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeaderComponent extends AbstractUIObject {

    @FindBy(xpath = ".//div[contains(@id,\"minicart\")]/..")
    private MiniCart miniCart;

    @FindBy(xpath = ".//div[@data-block=\"minicart\"]/a")
    private WebElement miniCartButton;

    @FindBy(xpath = ".//span[contains(@class, \"counter-number\")]")
    private WebElement amountOfProductElement;

    @FindBy(xpath = ".//input[contains(@id, \"search\")]")
    private WebElement searchInput;

    @FindBy(xpath = ".//button[contains(@title,\"Search\")]")
    private WebElement searchButton;

    public HeaderComponent(SearchContext context, WebDriver driver) {
        super(context, driver);
    }

    public void enterSearchText(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
    }

    public void clickSearchButton() {
        Commands.click(driver, searchButton);
    }

    public void searchFor(String text) {
        enterSearchText(text);
        clickSearchButton();
    }

    public void searchForRandomPhraze() {
        List<String> phrazes = new ArrayList<>(Constant.getSearchingPhrazes());
        Collections.shuffle(phrazes);
        logger.info("... Searching phraze is " + phrazes.get(0));
        searchFor(phrazes.get(0));
    }

    public MiniCart openMiniCart() {
        Commands.click(driver, miniCartButton);
        WaitUtils.waitUntilElementExist(driver, getRootElement(), 3);
        return miniCart;
    }

    public String getAmountOfProduct() {
        return amountOfProductElement.getText();
    }

    public void scrollToAmountOfProductElement() {
        Commands.scrollToElement(driver, amountOfProductElement);
    }

}
