package com.amazon;

import org.testng.annotations.DataProvider;

public class BrowserDataProvider {

    @DataProvider(name = "browsers")
    public static Object[][] provideBrowserConfigPath(){
        return new Object[][]{
                {"chrome", "src/main/resources/chrome_config.properties"},
                {"safari","src/main/resources/safari_config.properties"}
        };
    }
}
