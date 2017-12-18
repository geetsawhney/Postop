package com.postop.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * To generate a hash value from string
 * @author Geet Sawhney, Rohit Aakash
 */
public class HashGenerator {
    /**
     * @param string - to be hashed
     * @return hashed string
     * @throws NoSuchAlgorithmException invalid algorithm used
     */
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
