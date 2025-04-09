package com.amazon.constant;

import com.amazon.drivers.WebDriverFactory;

public class Constant {
    public static final String HOME_URL = new WebDriverFactory().loadPropertiesFromFile().getProperty("home_page_url");
}
