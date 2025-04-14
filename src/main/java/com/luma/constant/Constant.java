package com.luma.constant;

import com.luma.drivers.WebDriverFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Constant {

    public static final Properties PROPERTIES;
    public static final String HOME_URL;

    static {
        PROPERTIES = new WebDriverFactory().loadPropertiesFromFile();
        HOME_URL = PROPERTIES.getProperty("home_page_url");
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

}
