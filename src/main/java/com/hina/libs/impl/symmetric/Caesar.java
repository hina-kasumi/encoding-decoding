package com.hina.libs.impl.symmetric;

import com.hina.libs.core.symmetric.SymmetricEncryption;

public class Caesar implements SymmetricEncryption {
    public Caesar() {
    }

    @Override
    public String getName() {
        return "Caesar Cipher";
    }

    @Override
    public byte[] encrypt(byte[] plaintext, byte[] key) {
        int shift = key[0]; // lấy shift
        byte[] result = new byte[plaintext.length];

        for (int i = 0; i < plaintext.length; i++) {
            result[i] = (byte) (plaintext[i] + shift);
        }
        return result;
    }

    @Override
    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        int shift = key[0]; // lấy shift
        byte[] result = new byte[ciphertext.length];

        for (int i = 0; i < ciphertext.length; i++) {
            result[i] = (byte) (ciphertext[i] - shift);
        }
        return result;
    }
}
