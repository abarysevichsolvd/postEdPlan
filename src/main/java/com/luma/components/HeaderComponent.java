package com.luma.components;

import com.luma.Commands;
import com.luma.constant.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Collections;
import java.util.List;

public class HeaderComponent extends AbstractComponent {

    @FindBy(xpath = ".//input[@id = \"search\"]")
    private WebElement searchInput;

    @FindBy(xpath = ".//button[@title=\"Search\"]")
    private WebElement searchButton;

    public HeaderComponent(WebElement context, WebDriver driver) {
        super(context, driver);
    }

    public void enterSearchText(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
    }

    public void clickSearchButton() {
        Commands.waitUntilClickable(driver, searchButton, 3);
        searchButton.click();
    }

    public void searchFor(String text) {
        enterSearchText(text);
        clickSearchButton();
    }

    public void searchForRandomPhraze() {
        List<String> phrazes = Constant.getSearchingPhrazes();
        Collections.shuffle(phrazes);
        logger.info("... Searching phraze is " + phrazes.get(0));
        searchFor(phrazes.get(0));
    }

}
