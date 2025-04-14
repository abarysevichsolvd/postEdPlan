package com.luma.beans;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
//@ToString
public class Product {
    private String title;
    private String price;
    private String size;
    private String color;
}

