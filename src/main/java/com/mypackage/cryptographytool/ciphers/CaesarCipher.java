package com.mypackage.cryptographytool.ciphers;

public class CaesarCipher {

    public static String encrypt(String message, int key) {
        StringBuilder sb = new StringBuilder();
        for(char ch : message.toCharArray()) {
            if(ch >= 'A' && ch <= 'Z') {
                ch = (char)(((ch - 'A' + key) % 26) + 'A');
            }
            else if (ch >= 'a' && ch <= 'z') {
                ch = (char)(((ch - 'a' + key) % 26) + 'a');
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String decrypt(String message, int key) {
        key = key % 26;
        key = 26 - key;
        return encrypt(message, key);
    }

}