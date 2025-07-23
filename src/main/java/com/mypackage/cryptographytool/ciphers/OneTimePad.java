package com.mypackage.cryptographytool.ciphers;

public class OneTimePad {

    public static String encrypt(String plainText, String KEY) {
        StringBuilder cipherText = new StringBuilder();
        for(int i=0; i<plainText.length(); i++) {
            char plain = plainText.charAt(i);
            char key = KEY.charAt(i);
            if(Character.isUpperCase(plain)) {
                int c = (plain - 'A' + Character.toUpperCase(key) - 'A') % 26;
                cipherText.append((char) (c + 'A'));
            }
            else if(Character.isLowerCase(plain)) {
                int c = (plain - 'a' + Character.toLowerCase(key) - 'a') % 26;
                cipherText.append((char) (c + 'a'));
            }
            else {
                cipherText.append(plain);
            }
        }
        return cipherText.toString();
    }

    public static String decrypt(String cipherText, String KEY) {
        StringBuilder plainText = new StringBuilder();
        for(int i=0; i<cipherText.length(); i++) {
            char plain = cipherText.charAt(i);
            char key = KEY.charAt(i);
            if(Character.isUpperCase(plain)) {
                int c = (plain - 'A' - (Character.toUpperCase(key) - 'A') + 26) % 26;
                plainText.append((char) (c + 'A'));
            }
            else if(Character.isLowerCase(plain)) {
                int c = (plain - 'a' - (Character.toLowerCase(key) - 'a') + 26) % 26;
                plainText.append((char) (c + 'a'));
            }
            else {
                plainText.append(plain);
            }
        }
        return plainText.toString();
    }

}