package com.luma.beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private String title;
    private String price;
    private String size;
    private String color;

    public String getPrice() {
        return price;
    }
}

