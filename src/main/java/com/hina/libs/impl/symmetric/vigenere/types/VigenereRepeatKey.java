package com.hina.libs.impl.symmetric.vigenere.types;

import com.hina.libs.core.symmetric.SymmetricEncryption;

public class VigenereRepeatKey implements SymmetricEncryption {
    final private byte length = (byte) 127;

    public VigenereRepeatKey() {
    }

    @Override
    public byte[] encrypt(byte[] plaintext, byte[] key) {
        byte[] ciphertext = new byte[plaintext.length];
        for (int i = 0; i < plaintext.length; i++) {
            int p = plaintext[i];
            int k = key[i % key.length];
            ciphertext[i] = (byte) ((p + k) % length);
        }
        return ciphertext;
    }

    @Override
    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        byte[] plaintext = new byte[ciphertext.length];
        for (int i = 0; i < ciphertext.length; i++) {
            int c = ciphertext[i];
            int k = key[i % key.length];
            plaintext[i] = (byte) ((c - k + length) % length);
        }
        return plaintext;
    }

    @Override
    public String getName() {
        return "Vigenere Repeat Key";
    }
}
