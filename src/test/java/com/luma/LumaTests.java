package com.luma;

import com.luma.beans.Product;
import com.luma.components.*;
import com.luma.pages.CartPage;
import com.luma.pages.HomePage;
import com.luma.pages.PDP;
import com.luma.pages.PLP;
import com.luma.utils.CommonUtils;
import com.luma.utils.ServiceUtil;
import jdk.jfr.Description;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.luma.utils.WaitUtils.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LumaTests extends AbstractTest {

    public WebDriver driver;
    private final Logger logger = Logger.getLogger(LumaTests.class);

    @BeforeMethod
    public void setDriver() {
        driver = getDriver();
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void validateProductsOnPLPpage() {
        HomePage homePage = HomePage.openHomePage(driver);
        openHomePageAndCloseCookie(homePage);

        HeaderComponent header = homePage.getHeader();
        header.searchForRandomPhraze();

        PLP plp = new PLP(driver);
        List<ProductPLPComponent> products = plp.getProductPLPList();
        assertTrue(products.size() > 0, "page contains empty list of results");
        products.forEach(product -> {
            logger.info("..... " + product.getTitleText().trim());
        });
    }

    @Test
    @Description("Add product from PLP page test")
    public void validateAddProductFromPLPpage() {
        int amountOfProducts = 3;
        HomePage homePage = HomePage.openHomePage(driver);

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
        waitUntilAmountOfProductExist(driver, header, String.valueOf(productsPLP.size()), 8);
        header.openMiniCart().openCart();
        CartPage cartPage = new CartPage(driver);
        List<Product> productsCart = getProductsFromCart(cartPage);


        HashSet<Product> productPLPset = new HashSet<>(productsPLP);
        HashSet<Product> productCartSet = new HashSet<>(productsCart);
        assertEquals(productPLPset, productCartSet, "Plp product list isn't as cart product list");

        driver.quit();
    }

    @Test
    @Description("Add product from PDP page test")
    public void validateAddProductFromPDPpage() {
        HomePage homePage = HomePage.openHomePage(driver);

        //1
        openHomePageAndCloseCookie(homePage);

        //2
        HeaderComponent header = homePage.getHeader();
        header.searchForRandomPhraze();

        //3
        PLP plp = new PLP(driver);
        List<ProductPLPComponent> productPLPComponents = plp.getProductPLPList();
        ProductPLPComponent productPLP = ServiceUtil.getRandomElement(productPLPComponents);
        productPLP.scrollToProduct();
        String plpProductTitle = productPLP.getTitleText();
        String plpProductPrice = productPLP.getPriceText();
        productPLP.clickTitle();

        //4
        PDP pdp = new PDP(driver);
        ProductPDPComponent productPDPCompponent = pdp.getProductPDPComponent();
        assertEquals(plpProductTitle.trim(), productPDPCompponent.getTitleText().trim(), "Title isn't as expected");
        assertEquals(plpProductPrice.trim(), productPDPCompponent.getPriceText().trim(), "Price isn't as expected");

        Product productPDP = addProductToCartFromPDP(productPDPCompponent);
        header.scrollToAmountOfProductElement();
        waitUntilAmountOfProductExist(driver, header, String.valueOf(1), 8);

        //5
        header.openMiniCart().openCart();
        CartPage cartPage = new CartPage(driver);
        List<Product> productsCart = getProductsFromCart(cartPage);


        HashSet<Product> productPDPset = new HashSet<>();
        productPDPset.add(productPDP);
        HashSet<Product> productCartSet = new HashSet<>(productsCart);
        assertEquals(productPDPset, productCartSet, "Plp product list isn't as cart product list");
    }

    @Test
    @Description("Minicart is empty")
    public void validateMiniCartIsEmpty() {
        HomePage homePage = HomePage.openHomePage(driver);

        //1
        openHomePageAndCloseCookie(homePage);

        //2
        HeaderComponent header = homePage.getHeader();
        MiniCart minicart = header.openMiniCart();

        assertEquals(minicart.getEmptyTitleText(), "You have no items in your shopping cart.", "Empty mini cart title isn't as expected");
    }

    @Test
    @Description("Delete products in the cart")
    public void validateDeleteProductsInTheCart() {
        int amountOfProducts = 3;
        HomePage homePage = HomePage.openHomePage(driver);

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
        header = plp.getHeader();
        header.scrollToAmountOfProductElement();
        waitUntilAmountOfProductExist(driver, header, String.valueOf(productsPLP.size()), 8);
        header.openMiniCart().openCart();
        CartPage cartPage;

        //5
        for (int i = 0; i < amountOfProducts; i++) {
            waitUntilProductCartListExist(driver, 3);
            cartPage = new CartPage(driver);
            List<ProductCartComponent> productsCard = cartPage.getProductCartComponents();
            productsCard.get(0).deleteProduct();
            if (productsCard.size() - 1 > 0) waitUntilProductCartListExist(driver, productsCard.size() - 1, 3);
        }

        //6
        cartPage = new CartPage(driver);
        assertTrue(cartPage.isEmptyCardPresent(), "Cart isn't empty");
        assertEquals(cartPage.getEmptyCardTitle().trim(), "You have no items in your shopping cart.", "Cart isn't empty");
    }

    @Test
    @Description("Delete products in the cart")
    public void validateCheckOrderTotalInCart() {
        int amountOfProducts = 3;
        HomePage homePage = HomePage.openHomePage(driver);

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
        header = plp.getHeader();
        header.scrollToAmountOfProductElement();
        waitUntilAmountOfProductExist(driver, header, String.valueOf(productsPLP.size()), 8);
        header.openMiniCart().openCart();

        CartPage cartPage = new CartPage(driver);
        SummaryComponent summaryComponent = cartPage.getSummaryComponent();
        double actualTotal = CommonUtils.getParsedPrice(summaryComponent.getSubtotalText());
        List<Product> productsCart = getProductsFromCart(cartPage);

        double expectedTotal = productsCart.stream().map(a -> CommonUtils.getParsedPrice(a.getPrice())).mapToDouble(Double::doubleValue).sum();

        assertEquals(actualTotal, expectedTotal, "Total isn't as expected");
    }

    @Test
    @Description("Delete products in the cart")
    public void validateFiltersOnPLP() {
        int amountOfProducts = 3;
        HomePage homePage = HomePage.openHomePage(driver);

        //1
        openHomePageAndCloseCookie(homePage);

        //2
        HeaderComponent header = homePage.getHeader();
        header.searchForRandomPhraze();

        //3
        PLP plp = new PLP(driver);
        plp.sortBy("price");
        waitUntilPageLoaded(driver, 5);

        List<ProductPLPComponent> productPLPComponents = new PLP(driver).getProductPLPList();

        List<Double> prices = productPLPComponents.stream()
                .map(ProductPLPComponent::getPriceText)
                .map(CommonUtils::getParsedPrice)
                .collect(Collectors.toList());

        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        Collections.reverse(sorted);

        assertEquals(sorted, prices, "Products are not sorted by price (low to high)");
    }

    private void openHomePageAndCloseCookie(HomePage homePage) {
        homePage.validateHomePageIsOpened();
        homePage.agreeCookie();
    }

    private Product addRandomProductToCartFromPLP(List<ProductPLPComponent> products) {
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

    private Product addProductToCartFromPDP(ProductPDPComponent productPDPComponent) {
        List<String> sizes = productPDPComponent.getSizes();
        String randomSize = ServiceUtil.getRandomElement(sizes);
        productPDPComponent.clickSizeByTextIfNotSelected(randomSize);

        List<String> colors = productPDPComponent.getColors();
        String randomColor = ServiceUtil.getRandomElement(colors);
        productPDPComponent.clickColorByTextIfNotSelected(randomColor);

        productPDPComponent.clickAddToCartButton();
        Product product = ProductParser.parseProduct(productPDPComponent.getTitleText(), productPDPComponent.getPriceText(), randomSize, randomColor);
        return product;
    }

    private List<Product> addRandomProductsToCart(List<ProductPLPComponent> products, int amount) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            productList.add(addRandomProductToCartFromPLP(products));
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
