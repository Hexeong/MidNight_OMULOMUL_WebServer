package com.ohgiraffers.midnight.config;

import java.util.Base64;

public class Base64Util {
    public static byte[] decodeBase64(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }
}
