package com.mypackage.cryptographytool.ciphers;

import java.util.Arrays;

public class ColumnarTranspositionCipher {

    private static int[] getKeyOrder(String key) {
        int length = key.length();
        Character[] keyChars = new Character[length];
        for(int i=0; i<length; i++) {
            keyChars[i] = key.charAt(i);
        }
        Character[] sortedKey = keyChars.clone();
        Arrays.sort(sortedKey);
        int[] order = new int[length];
        for(int i=0; i<length; i++) {
            for(int j=0; j<length; j++) {
                if(keyChars[i] == sortedKey[j]) {
                    order[i] = j;
                    sortedKey[j] = '\0';
                    break;
                }
            }
        }
        return order;
    }

    public static String encrypt(String plaintext, String key) {
        plaintext = plaintext.replaceAll("\\s", "");
        int cols = key.length();
        int rows = (int) Math.ceil((double) plaintext.length() / cols);
        char[][] matrix = new char[rows][cols];
        int k = 0;
        for(int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                if(k < plaintext.length()) matrix[i][j] = plaintext.charAt(k++);
                else matrix[i][j] = 'X';
            }
        }
        int[] order = getKeyOrder(key);
        StringBuilder ciphertext = new StringBuilder();
        for(int i=0; i<cols; i++) {
            int col = -1;
            for (int j = 0; j < cols; j++) {
                if (order[j] == i) {
                    col = j;
                    break;
                }
            }
            for(int row = 0; row < rows; row++) {
                ciphertext.append(matrix[row][col]);
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        int cols = key.length();
        int rows = (int) Math.ceil((double) ciphertext.length() / cols);
        char[][] matrix = new char[rows][cols];
        int[] order = getKeyOrder(key);
        int k = 0;
        for(int i=0; i<cols; i++) {
            int col = -1;
            for(int j=0; j<cols; j++) {
                if(order[j] == i) {
                    col = j;
                    break;
                }
            }
            for(int row = 0; row < rows; row++) {
                if (k < ciphertext.length())
                    matrix[row][col] = ciphertext.charAt(k++);
            }
        }
        StringBuilder plaintext = new StringBuilder();
        for(int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                plaintext.append(matrix[i][j]);
            }
        }
        return plaintext.toString().replaceAll("X+$", "");
    }

}
