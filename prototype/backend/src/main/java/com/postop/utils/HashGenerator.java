package com.postop.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    public static String generateHash(String string) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(string.getBytes());

        byte[] digest = messageDigest.digest();
        StringBuffer stringHash = new StringBuffer();

        for(byte b: digest){
            stringHash.append(String.format("%02x", b & 0xff));
        }

        return stringHash.toString();
    }
}
