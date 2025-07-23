package com.mypackage.cryptographytool.ciphers;

import java.util.Map;
import java.util.HashMap;

public class MonoalphabeticCipher {

    public static String encrypt(String input, String key) {
        if(key.length() != 26) return "";
        String original = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Map<Character, Character> encryptMap = new HashMap<>();
        for(int i = 0; i < 26; i++) {
            encryptMap.put(original.charAt(i), key.charAt(i));
        }
        StringBuilder encrypted = new StringBuilder();
        for(char ch : input.toCharArray()) {
            boolean isLower = false;
            if(Character.isLowerCase(ch)) {
                isLower = true;
                ch = Character.toUpperCase(ch);
            }
            if(Character.isLetter(ch)) {
                if(isLower) encrypted.append(Character.toLowerCase(encryptMap.get(ch)));
                else encrypted.append(encryptMap.get(ch));
            } else {
                encrypted.append(ch);
            }
        }
        System.out.println(encryptMap);
        return encrypted.toString();
    }

    public static String decrypt(String input, String key) {
        if(key.length() != 26) return "";
        String original = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Map<Character, Character> decryptMap = new HashMap<>();
        for(int i = 0; i < 26; i++) {
            decryptMap.put(key.charAt(i), original.charAt(i));
        }
        StringBuilder decrypted = new StringBuilder();
        for(char ch : input.toCharArray()) {
            boolean isLower = false;
            if(Character.isLowerCase(ch)) {
                isLower = true;
                ch = Character.toUpperCase(ch);
            }
            if(Character.isLetter(ch)) {
                if(isLower) decrypted.append(Character.toLowerCase(decryptMap.get(ch)));
                else decrypted.append(decryptMap.get(ch));
            } else {
                decrypted.append(ch);
            }
        }
        System.out.println(decryptMap);
        return decrypted.toString();
    }

}