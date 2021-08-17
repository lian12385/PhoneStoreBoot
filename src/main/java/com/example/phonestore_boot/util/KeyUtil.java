package com.example.phonestore_boot.util;

import java.util.Random;

public class KeyUtil {
    public static synchronized String createUniqueKey(){
        Random random = new Random();
        Integer key = random.nextInt(900000) + 10000;
        return System.currentTimeMillis() + String.valueOf(key);
    }
}
