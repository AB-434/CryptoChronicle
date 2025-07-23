package com.mypackage.cryptographytool.ciphers;

public class RailFenceCipher {

    public static String encrypt(String plainText, int KEY) {
        if(KEY == 1) return plainText;
        plainText = plainText.replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<KEY;i++){
            for(int j=i;j<plainText.length();j+=(2*(KEY-1))){
                sb.append(plainText.charAt(j));
                if(i>0 && i<KEY-1 && j+(2*(KEY-1))-(2*i) < plainText.length()){
                    sb.append(plainText.charAt(j+(2*(KEY-1))-(2*i)));
                }
            }
        }
        return sb.toString();
    }

    public static String decrypt(String cipherText, int KEY) {
        if(KEY == 1) return cipherText;
        cipherText = cipherText.replaceAll(" ", "");
        int n = cipherText.length();
        if(n == 0) return "";
        int cycle = 2 * (KEY - 1);
        int fullCycles = n / cycle;
        int remainder = n % cycle;
        int[] railLengths = new int[KEY];
        for(int i=0; i<KEY; i++) {
            if(i == 0 || i == KEY-1) {
                railLengths[i] = fullCycles;
            }
            else {
                railLengths[i] = 2 * fullCycles;
            }
        }
        for(int i=0; i<remainder; i++) {
            int r = (i < KEY) ? i : cycle - i;
            railLengths[r]++;
        }
        String[] rails = new String[KEY];
        int start = 0;
        for(int i=0; i<KEY; i++) {
            rails[i] = cipherText.substring(start, start + railLengths[i]);
            start += railLengths[i];
        }
        StringBuilder plainText = new StringBuilder();
        int[] pointers = new int[KEY];
        for(int j=0; j<n; j++) {
            int mod = j % cycle;
            int railIndex = mod;
            if(mod >= KEY) {
                railIndex = cycle - mod;
            }
            plainText.append(rails[railIndex].charAt(pointers[railIndex]));
            pointers[railIndex]++;
        }
        return plainText.toString();
    }

}