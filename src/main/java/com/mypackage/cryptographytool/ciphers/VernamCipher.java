package com.mypackage.cryptographytool.ciphers;

public class VernamCipher {

    private static String generateKey(String inputText, String KEY) {
        StringBuilder sb = new StringBuilder();
        int keyLen = KEY.length(), k = 0;
        for(int i=0; i<inputText.length(); i++) {
            char ch = inputText.charAt(i);
            if(ch == ' ') {
                sb.append(' ');
            }
            else {
                sb.append(KEY.charAt(k % keyLen));
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
            boolean isLower = Character.isLowerCase(ch);
            if (Character.isLetter(ch)) {
                ch = Character.toUpperCase(ch);
                int p = ch - 'A';
                int k = newKey.charAt(i) - 'A';
                int val = (p + k) % 26;
                char enc = (char) (val + 'A');
                cipherText.append(isLower ? Character.toLowerCase(enc) : enc);
            } else {
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
            boolean isLower = Character.isLowerCase(ch);
            if (Character.isLetter(ch)) {
                ch = Character.toUpperCase(ch);
                int c = ch - 'A';
                int k = newKey.charAt(i) - 'A';
                int val = (c - k + 26) % 26;
                char dec = (char) (val + 'A');
                plainText.append(isLower ? Character.toLowerCase(dec) : dec);
            } else {
                plainText.append(ch);
            }
        }
        return plainText.toString();
    }
}
