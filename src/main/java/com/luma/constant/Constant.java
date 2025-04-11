package com.luma.constant;

import com.luma.drivers.WebDriverFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Constant {

    public static final Properties PROPERTIES;
    public static final String HOME_URL = getHomeUrl();

    static {
        PROPERTIES = new WebDriverFactory().loadPropertiesFromFile();
    }

    public static List<String> getSearchingPhrazes() {
        List<String> listOfPhrazes = new ArrayList<>();
        listOfPhrazes.addAll(List.of(
                "Hoodie",
                "Sweatshirt",
                "Jacket",
                "Tee",
                "Bra",
                "Tank",
                "Pant",
                "Short"
        ));
        return listOfPhrazes;
    }

    public static String getHomeUrl() {
        return PROPERTIES.getProperty("home_page_url");
    }

}
