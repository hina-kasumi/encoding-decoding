package com.hina.libs.impl.symmetric.vigenere;

import com.hina.libs.core.symmetric.SymmetricEncryption;
import com.hina.libs.impl.symmetric.vigenere.types.VigenereAutoKey;
import com.hina.libs.impl.symmetric.vigenere.types.VigenereRepeatKey;

public class Vigenere implements SymmetricEncryption {
    private final SymmetricEncryption vigenere;

    public Vigenere() {
        this(VigenereType.AUTO_GEN_KEY);
    }

    public Vigenere(VigenereType type) {
        vigenere = switch (type) {
            case REPEAT_KEY -> new VigenereRepeatKey();
            default -> new VigenereAutoKey();
        };
    }

    @Override
    public String getName() {
        return vigenere.getName();
    }

    @Override
    public byte[] encrypt(byte[] plaintext, byte[] key) {
        return vigenere.encrypt(plaintext, key);
    }

    @Override
    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        return vigenere.decrypt(ciphertext, key);
    }
}
