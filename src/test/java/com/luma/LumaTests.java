package com.luma;

import com.luma.beans.Product;
import com.luma.components.HeaderComponent;
import com.luma.components.ProductCartComponent;
import com.luma.components.ProductPLPComponent;
import com.luma.drivers.WebDriverFactory;
import com.luma.pages.CartPage;
import com.luma.pages.HomePage;
import com.luma.pages.PLP;
import com.luma.utils.ServiceUtil;
import com.luma.utils.WaitUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class LumaTests {

    public WebDriver driver;
    private final Logger logger = Logger.getLogger(LumaTests.class);

    public void setDriver(String browser, String browserPath) {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getDriver(browserPath);
        if (browser.equals("safari")) driver.manage().window().maximize();
    }

    @Test(dataProvider = "browsers", dataProviderClass = BrowserDataProvider.class)
    public void validateProductsOnPLPpage(String browser, String browserPath) {
        setDriver(browser, browserPath);
        HomePage homePage = new HomePage(driver);
        openHomePageAndCloseCookie(homePage);

        HeaderComponent header = homePage.getHeader();
        header.searchForRandomPhraze();

        PLP plp = new PLP(driver);
        List<ProductPLPComponent> products = plp.getProductPLPList();
        assertTrue(products.size() > 0, "page contains empty list of results");
        products.forEach(product -> {
            logger.info("..... " + product.getTitleText().trim());
        });

        driver.quit();
    }

    @Test(dataProvider = "browsers", dataProviderClass = BrowserDataProvider.class)
    public void validateProductsInTheCart(String browser, String browserPath) {
        setDriver(browser, browserPath);
        int amountOfProducts = 3;
        HomePage homePage = new HomePage(driver);

        //1
        openHomePageAndCloseCookie(homePage);

        //2
        HeaderComponent header = homePage.getHeader();
        header.searchForRandomPhraze();

        //3
        PLP plp = new PLP(driver);
        List<ProductPLPComponent> productPLPComponents = plp.getProductPLPList();
        List<Product> productsPLP = addRandomProductsToCart(productPLPComponents, amountOfProducts);

        //4
        header.scrollToAmountOfProductElement();
        WaitUtils.waitUntilAmountOfProductExist(driver, header, String.valueOf(productsPLP.size()), 8);
        header.openMiniCart().openCart();
        CartPage cartPage = new CartPage(driver);
        List<Product> productsCart = getProductsFromCart(cartPage);


        HashSet<Product> productPLPset = new HashSet<>(productsPLP);
        HashSet<Product> productCartSet = new HashSet<>(productsCart);
        Assert.assertEquals(productPLPset, productCartSet, "Plp product list isn't as cart product list");

        driver.quit();
    }

    private void openHomePageAndCloseCookie(HomePage homePage) {
        homePage.openHomePage();
        homePage.validateHomePageIsOpened();
        homePage.agreeCookie();
    }

    private Product addRandomProductToCart(List<ProductPLPComponent> products) {
        ProductPLPComponent randomProduct = ServiceUtil.getRandomElement(products);
        randomProduct.scrollToProduct();

        List<String> sizes = randomProduct.getSizes();
        String randomSize = ServiceUtil.getRandomElement(sizes);
        randomProduct.clickSizeByTextIfNotSelected(randomSize);

        List<String> colors = randomProduct.getColors();
        String randomColor = ServiceUtil.getRandomElement(colors);
        randomProduct.clickColorByTextIfNotSelected(randomColor);

        randomProduct.clickAddToCartButton();
        Product product = ProductParser.parseProduct(randomProduct.getTitleText(), randomProduct.getPriceText(), randomSize, randomColor);
        return product;
    }

    private List<Product> addRandomProductsToCart(List<ProductPLPComponent> products, int amount) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            productList.add(addRandomProductToCart(products));
        }
        return productList;
    }

    private List<Product> getProductsFromCart(CartPage cartPage) {
        List<ProductCartComponent> products = cartPage.getProductCartComponents();

        return products.stream()
                .map(productCartComponent -> ProductParser.parseProduct(productCartComponent.getTitleText(), productCartComponent.getPriceText(), productCartComponent.getSizeText(), productCartComponent.getColorText()))
                .collect(Collectors.toList());
    }
}
