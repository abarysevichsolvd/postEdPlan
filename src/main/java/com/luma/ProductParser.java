package com.luma;

import com.luma.beans.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductParser {

    public static Product parseProduct(String title, String price, String size, String color) {
        return Product.builder()
                .title(title.trim())
                .price(price.trim())
                .size(size.trim())
                .color(color.trim())
                .build();
    }

}
