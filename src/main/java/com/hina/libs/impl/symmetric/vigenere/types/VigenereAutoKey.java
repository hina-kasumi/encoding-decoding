package com.hina.libs.impl.symmetric.vigenere.types;

import com.hina.libs.core.symmetric.SymmetricEncryption;

public class VigenereAutoKey implements SymmetricEncryption {
    final private byte length = (byte) 127;

    public VigenereAutoKey() {
    }

    @Override
    public byte[] encrypt(byte[] plaintext, byte[] key) {
            byte[] extendedKey = new byte[plaintext.length];
            System.arraycopy(key, 0, extendedKey, 0, key.length);
            System.arraycopy(plaintext, 0, extendedKey, key.length, plaintext.length - key.length);

            byte[] ciphertext = new byte[plaintext.length];
            for (int i = 0; i < plaintext.length; i++) {
                int p = plaintext[i];
                int k = extendedKey[i];
                ciphertext[i] = (byte) ((p + k) % length);
            }
            return ciphertext;
    }

    @Override
    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        byte[] extendedKey = new byte[ciphertext.length];
        System.arraycopy(key, 0, extendedKey, 0, key.length);

        byte[] plaintext = new byte[ciphertext.length];
        for (int i = 0; i < ciphertext.length; i++) {
            int c = ciphertext[i];
            int k = extendedKey[i];
            plaintext[i] = (byte) ((c - k + length) % length);
            if (i + key.length < extendedKey.length) {
                extendedKey[i + key.length] = plaintext[i];
            }
        }
        return plaintext;
    }

    @Override
    public String getName() {
        return "Vigenere Auto Key";
    }
}
