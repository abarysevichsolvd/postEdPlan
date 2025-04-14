package com.luma.utils;

import java.util.List;
import java.util.Random;

public class ServiceUtil {

    public static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List is null or empty");
        }
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

}
