package com.mypackage.cryptographytool.ciphers;

public class VigenereCipher {

    private static String generateKey(String plainText, String KEY) {
        StringBuilder sb = new StringBuilder();
        int n = KEY.length();
        int k = 0;
        for(int i=0; i<plainText.length(); i++) {
            if(plainText.charAt(i) == ' ') {
                sb.append(" ");
            }
            else {
                sb.append(KEY.charAt(k % n));
                k++;
            }
        }
        return sb.toString();
    }

    public static String encrypt(String plainText, String KEY) {
        KEY = KEY.toUpperCase();
        String newKey = generateKey(plainText, KEY);
        StringBuilder cipherText = new StringBuilder();
        for(int i=0; i<plainText.length(); i++) {
            char ch = plainText.charAt(i);
            boolean lower = false;
            if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                if(Character.isLowerCase(ch)) lower = true;
                ch = Character.toUpperCase(ch);
                int plain = ch - 'A';
                int key = newKey.charAt(i) - 'A';
                int value = (plain + key) % 26;
                char newChar = (char)(value + 'A');
                if(lower) cipherText.append(Character.toLowerCase(newChar));
                else cipherText.append(newChar);
            }
            else {
                cipherText.append(ch);
            }
        }
        return cipherText.toString();
    }

    public static String decrypt(String cipherText, String KEY) {
        KEY = KEY.toUpperCase();
        String newKey = generateKey(cipherText, KEY);
        StringBuilder plainText = new StringBuilder();
        for(int i=0; i<cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            boolean lower = false;
            if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                if(Character.isLowerCase(ch)) lower = true;
                ch = Character.toUpperCase(ch);
                int plain = ch - 'A';
                int key = newKey.charAt(i) - 'A';
                int value = (plain - key + 26) % 26;
                char newChar = (char)(value + 'A');
                if(lower) plainText.append(Character.toLowerCase(newChar));
                else plainText.append(newChar);
            }
            else {
                plainText.append(ch);
            }
        }
        return plainText.toString();
    }

}