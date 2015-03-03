package com.purchasing.support.user;

import java.util.Random;

/**
 * @author vanessa
 */
public class PasswordGenerator {

    public static String generate() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";
        Random random = new Random();
        String key = "";
        int index = -1;
        int valueNumeric= 0;
        for (int i = 0; i < 4; i++) {
            index = random.nextInt(letters.length());
            valueNumeric = random.nextInt(10);
            key += letters.substring(index, index + 1);
            key += valueNumeric;
        }
        return key;
    }
}
