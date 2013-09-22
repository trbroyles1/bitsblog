package com.bitssc.bitsblog.realm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("Hello World!");
        StringBuilder result = new StringBuilder();
        for (byte b :  MessageDigest.getInstance("SHA-1").digest("R1552861r".getBytes())) {
            result.append(String.format("%02X", b));
        }
        System.out.println(result.toString());
    }
}
