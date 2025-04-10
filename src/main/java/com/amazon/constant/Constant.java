package com.amazon.constant;

import com.amazon.drivers.WebDriverFactory;

import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static final String HOME_URL = new WebDriverFactory().loadPropertiesFromFile().getProperty("home_page_url");

    public static List<String> getSearchingPhrazes() {
        List<String> listOfPhrazes = new ArrayList<>();
        listOfPhrazes.addAll(List.of(
                "Hoodies",
                "Sweatshirt",
                "Jacket",
                "Tee",
                "Bra",
                "Tank",
                "Pant",
                "Short",
                "Bag"
        ));
        return listOfPhrazes;
    }

}
