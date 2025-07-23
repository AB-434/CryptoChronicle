package com.mypackage.cryptographytool.ciphers;

import java.util.ArrayList;
import java.util.List;

public class HillCipher {

    public static String encrypt(String plainText, String keyStr) {
        if(!plainText.matches("[a-zA-Z]+")) return "Plain Text can only contain alphabets !!";
        if (!isValidKey(keyStr)) {
            return "Key must be exactly 4 alphabetic characters.";
        }
        int[][] key = buildKeyMatrix(keyStr.toLowerCase());
        StringBuilder result = new StringBuilder();
        List<Character> filtered = new ArrayList<>();
        for (char ch : plainText.toCharArray()) {
            if (Character.isLetter(ch)) {
                filtered.add(ch);
            }
        }
        if (filtered.size() % 2 != 0) {
            filtered.add('X');
        }
        for (int i = 0; i < filtered.size(); i += 2) {
            int a = Character.toLowerCase(filtered.get(i)) - 'a';
            int b = Character.toLowerCase(filtered.get(i + 1)) - 'a';
            int c1 = (key[0][0] * a + key[0][1] * b) % 26;
            int c2 = (key[1][0] * a + key[1][1] * b) % 26;
            char ec1 = matchCase((char) (c1 + 'a'), filtered.get(i));
            char ec2 = matchCase((char) (c2 + 'a'), filtered.get(i + 1));
            result.append(ec1).append(ec2);
        }

        return result.toString();
    }

    public static String decrypt(String cipherText, String keyStr) {
        if(!cipherText.matches("[a-zA-Z]+")) return "Plain Text can only contain alphabets !!";
        if (!isValidKey(keyStr)) {
            return "Key must be exactly 4 alphabetic characters.";
        }

        int[][] key = buildKeyMatrix(keyStr.toLowerCase());
        int[][] inverseKey = computeInverseKey(key);
        if(inverseKey[0][0] == -1 && inverseKey[1][0] == -1 && inverseKey[0][1] == -1 && inverseKey[1][1] == -1){
            return "Matrix is not invertible";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i += 2) {
            int a = Character.toLowerCase(cipherText.charAt(i)) - 'a';
            int b = Character.toLowerCase(cipherText.charAt(i + 1)) - 'a';
            int p1 = (inverseKey[0][0] * a + inverseKey[0][1] * b) % 26;
            int p2 = (inverseKey[1][0] * a + inverseKey[1][1] * b) % 26;

            if (p1 < 0) p1 += 26;
            if (p2 < 0) p2 += 26;

            char dc1 = matchCase((char) (p1 + 'a'), cipherText.charAt(i));
            char dc2 = matchCase((char) (p2 + 'a'), cipherText.charAt(i + 1));
            result.append(dc1).append(dc2);
        }

        return result.toString();
    }

    private static boolean isValidKey(String keyStr) {
        return keyStr != null && keyStr.length() == 4 && keyStr.matches("[a-zA-Z]+");
    }

    private static int[][] buildKeyMatrix(String keyStr) {
        int[][] key = new int[2][2];
        int k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key[i][j] = keyStr.charAt(k++) - 'a';
            }
        }
        return key;
    }

    private static int[][] computeInverseKey(int[][] key) {
        int det = key[0][0] * key[1][1] - key[0][1] * key[1][0];
        det = ((det % 26) + 26) % 26;

        int invDet = -1;
        for (int i = 0; i < 26; i++) {
            if ((det * i) % 26 == 1) {
                invDet = i;
                break;
            }
        }

        if (invDet == -1) {
            return new int[][]{{-1,-1},{-1,-1}};
        }

        int[][] inverseKey = new int[2][2];
        inverseKey[0][0] = (key[1][1] * invDet) % 26;
        inverseKey[0][1] = (-key[0][1] * invDet) % 26;
        inverseKey[1][0] = (-key[1][0] * invDet) % 26;
        inverseKey[1][1] = (key[0][0] * invDet) % 26;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                inverseKey[i][j] = (inverseKey[i][j] + 26) % 26;
            }
        }

        return inverseKey;
    }

    private static char matchCase(char c, char ref) {
        return Character.isUpperCase(ref) ? Character.toUpperCase(c) : c;
    }
}
