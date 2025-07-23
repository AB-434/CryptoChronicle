package com.mypackage.cryptographytool.service;

import com.mypackage.cryptographytool.models.CipherRequest;
import com.mypackage.cryptographytool.ciphers.*;

import org.springframework.stereotype.Service;

@Service
public class CipherService {

    public String process(CipherRequest request) {
        String message = request.getMessage();
        String key = request.getKey();
        boolean encrypt = request.isEncrypt();
        switch(request.getType().toLowerCase()) {

            case "caesar":
                try {
                    int shift = Integer.parseInt(key);
                    return encrypt ? CaesarCipher.encrypt(message, shift)
                            : CaesarCipher.decrypt(message, shift);
                }
                catch(NumberFormatException e) {
                    return "Invalid key for Caesar Cipher. Key must be an integer.";
                }

            case "monoalphabetic":
                if(key.length() != 26 || !key.matches("[a-zA-Z]+")) {
                    return "Invalid key for Monoalphabetic Cipher. Key must be 26 unique letters.";
                }
                return encrypt ? MonoalphabeticCipher.encrypt(message, key)
                        : MonoalphabeticCipher.decrypt(message, key);

            case "playfair":
                if(key.isEmpty() || !key.matches("[a-zA-Z]+")) {
                    return "Invalid key for Playfair Cipher. Key must be a non-empty alphabetic string.";
                }
                return encrypt ? PlayfairCipher.encrypt(message, key)
                        : PlayfairCipher.decrypt(message, key);

            case "vigenere":
                if(key.isEmpty() || !key.matches("[a-zA-Z]+")) {
                    return "Invalid key for Vigenère Cipher. Key must be a non-empty alphabetic string.";
                }
                return encrypt ? VigenereCipher.encrypt(message, key)
                        : VigenereCipher.decrypt(message, key);

            case "vernam":
                if(key.isEmpty() || !key.matches("[a-zA-Z]+")) {
                    return "Invalid key for Vernam Cipher. Key must be a non-empty alphabetic string.";
                }
                return encrypt ? VernamCipher.encrypt(message, key)
                        : VernamCipher.decrypt(message, key);

            case "hill":
                if(!(key.length() == 4 || key.length() == 9)) {
                    return "Invalid key for Hill Cipher. Key must be a string of 4 or 9 letters";
                }
                return encrypt ? HillCipher.encrypt(message, key)
                        : HillCipher.decrypt(message, key);

            case "railfence":
                try{
                    int KEY = Integer.parseInt(key);
                    if (KEY <= 1) return "Key for RailFence must be greater than 1.";
                    return encrypt ? RailFenceCipher.encrypt(message, KEY)
                            : RailFenceCipher.decrypt(message, KEY);
                }
                catch(NumberFormatException e) {
                    return "Invalid key for RailFence Cipher. Key must be an integer.";
                }

            case "columnar":
                if(key.isEmpty() || key.matches("[a-zA-Z]+")) {
                    return "Invalid key for Columnar Cipher. Key must be a non-empty string of digits";
                }
                return encrypt ? ColumnarTranspositionCipher.encrypt(message, key)
                        : ColumnarTranspositionCipher.decrypt(message, key);

            case "otd":
                if(message.length() != key.length()) {
                    return "Invalid key for One Time Pad. Key length must match message length.";
                }
                return encrypt ? OneTimePad.encrypt(message, key)
                        : OneTimePad.decrypt(message, key);

            default:
                return "Unsupported cipher type.";

        }
    }

}
