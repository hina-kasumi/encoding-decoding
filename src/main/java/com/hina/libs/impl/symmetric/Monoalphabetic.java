package com.hina.libs.impl.symmetric;

import com.hina.libs.core.symmetric.SymmetricEncryption;

public class Monoalphabetic implements SymmetricEncryption {
    public Monoalphabetic() {
    }

    private boolean isValidKey(byte[] key) {
        if (key.length != 26) return false;
        boolean[] seen = new boolean[26];
        for (byte b : key) {
            if (b < 'A' || b > 'Z') return false; // Chỉ chấp nhận chữ hoa
            int index = b - 'A';
            if (seen[index]) return false; // Kiểm tra trùng lặp
            seen[index] = true;
        }
        return true;
    }

    @Override
    public byte[] encrypt(byte[] plaintext, byte[] key) {
        if (!isValidKey(key)) throw new IllegalArgumentException("Invalid key");
        byte[] ciphertext = new byte[plaintext.length];
        for (int i = 0; i < plaintext.length; i++) {
            byte b = plaintext[i];
            if (b >= 'A' && b <= 'Z') {
                ciphertext[i] = key[b - 'A']; // Thay thế theo key
            } else {
                ciphertext[i] = b; // Giữ nguyên ký tự không phải chữ hoa
            }
        }
        return ciphertext;
    }

    @Override
    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        if (!isValidKey(key)) throw new IllegalArgumentException("Invalid key");
        byte[] plaintext = new byte[ciphertext.length];
        for (int i = 0; i < ciphertext.length; i++) {
            byte b = ciphertext[i];
            if (b >= 'A' && b <= 'Z') {
                int index = -1;
                for (int j = 0; j < 26; j++) {
                    if (key[j] == b) {
                        index = j;
                        break;
                    }
                }
                plaintext[i] = (byte) ('A' + index); // Thay thế ngược lại
            } else {
                plaintext[i] = b; // Giữ nguyên ký tự không phải chữ hoa
            }
        }
        return plaintext;
    }

    @Override
    public String getName() {
        return "Monoalphabetic Cipher";
    }
}
