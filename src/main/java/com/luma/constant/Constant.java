package com.luma.constant;

import com.luma.utils.ConfigUtils;

import java.util.List;

public class Constant {

    public static final String HOME_URL = ConfigUtils.PROPERTIES.getProperty("home_page_url");

    public static List<String> getSearchingPhrazes() {
        return List.of(
                "Hoodie",
                "Sweatshirt",
                "Jacket",
                "Tee",
                "Bra",
                "Tank",
                "Pant",
                "Short"
        );
    }

}
