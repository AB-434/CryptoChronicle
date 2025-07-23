package com.mypackage.cryptographytool.ciphers;

import java.util.ArrayList;
import java.util.List;

public class PlayfairCipher {

    private static char[][] generateKey(String KEY) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[26];
        visited['J' - 'A'] = true;
        for(char ch : KEY.toCharArray()) {
            if(!visited[ch - 'A']) {
                visited[ch - 'A'] = true;
                sb.append(ch);
            }
        }
        for(char ch='A'; ch<='Z'; ch++) {
            if(!visited[ch - 'A']) {
                visited[ch - 'A'] = true;
                sb.append(ch);
            }
        }
        char[][] newKey = new char[5][5];
        int idx = 0;
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                newKey[i][j] = sb.charAt(idx++);
            }
        }
        return newKey;
    }

    private static List<String> generateBigrams(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        List<String> l = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<text.length(); i++) {
            char ch = text.charAt(i);
            if(ch == ' ') continue;
            if(sb.length() == 0) {
                sb.append(ch);
            }
            else if(sb.length() == 1) {
                if(sb.charAt(0) == ch) {
                    sb.append("X");
                    l.add(sb.toString());
                    sb.setLength(0);
                    sb.append(ch);
                }
                else {
                    sb.append(ch);
                    l.add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
        if(sb.length() == 2) {
            l.add(sb.toString());
        }
        else if(sb.length() == 1) {
            sb.append("X");
            l.add(sb.toString());
        }
        return l;
    }

    private static int[] findPosition(char[][] KEY, char ch) {
        if(ch == 'J') {
            ch = 'I';
        }
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if(KEY[i][j] == ch) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static String encrypt(String plainText, String KEY) {
        KEY = KEY.toUpperCase();
        char[][] newKey = generateKey(KEY);
        List<String> bigrams = generateBigrams(plainText);
        StringBuilder cipherText = new StringBuilder();
        for(String str : bigrams) {
            int[] first = findPosition(newKey,str.charAt(0));
            int[] second = findPosition(newKey,str.charAt(1));
            if(first[0] == second[0]) {
                cipherText.append(newKey[first[0]][(first[1] + 1) % 5]);
                cipherText.append(newKey[first[0]][(second[1] + 1) % 5]);
            }
            else if(first[1] == second[1]) {
                cipherText.append(newKey[(first[0] + 1) % 5][first[1]]);
                cipherText.append(newKey[(second[0] + 1) % 5][first[1]]);
            }
            else {
                cipherText.append(newKey[first[0]][second[1]]);
                cipherText.append(newKey[second[0]][first[1]]);
            }
        }
        return cipherText.toString();
    }

    public static String decrypt(String cipherText, String KEY) {
        KEY = KEY.toUpperCase();
        char[][] newKey = generateKey(KEY);
        List<String> bigrams = generateBigrams(cipherText);
        StringBuilder plainText = new StringBuilder();
        for(String str : bigrams) {
            int[] first = findPosition(newKey,str.charAt(0));
            int[] second = findPosition(newKey,str.charAt(1));
            if(first[0] == second[0]) {
                plainText.append(newKey[first[0]][(first[1] + 4) % 5]);
                plainText.append(newKey[first[0]][(second[1] + 4) % 5]);
            }
            else if(first[1] == second[1]) {
                plainText.append(newKey[(first[0] + 4) % 5][first[1]]);
                plainText.append(newKey[(second[0] + 4) % 5][first[1]]);
            }
            else {
                plainText.append(newKey[first[0]][second[1]]);
                plainText.append(newKey[second[0]][first[1]]);
            }
        }
        return plainText.toString();
    }

}