package symmetric;

import com.hina.libs.core.symmetric.SymmetricEncryption;
import com.hina.libs.impl.symmetric.vigenere.Vigenere;
import com.hina.libs.impl.symmetric.vigenere.VigenereType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VigenereTest {
    private SymmetricEncryption vigenereRepeatKey;
    private SymmetricEncryption vigenereAutoKey;

    @BeforeEach
    public void init() {
        vigenereRepeatKey = new Vigenere(VigenereType.REPEAT_KEY);
        vigenereAutoKey = new Vigenere(VigenereType.AUTO_GEN_KEY);
    }

    @Test
    public void testEncryptDecryptRepeatKey() {
        String plaintext = "Hello, World!";
        byte[] key = "KEY".getBytes();
        byte[] encrypted = vigenereRepeatKey.encrypt(plaintext.getBytes(), key);
        byte[] decrypted = vigenereRepeatKey.decrypt(encrypted, key);
        String decryptedText = new String(decrypted);

        assert plaintext.equals(decryptedText) : "Decrypted text should match original plaintext for repeat key";
    }

    @Test
    public void testEncryptDecryptAutoKey() {
        String plaintext = "Hello, World!";
        byte[] key = "KEY".getBytes();
        byte[] encrypted = vigenereAutoKey.encrypt(plaintext.getBytes(), key);
        byte[] decrypted = vigenereAutoKey.decrypt(encrypted, key);
        String decryptedText = new String(decrypted);

        assert plaintext.equals(decryptedText) : "Decrypted text should match original plaintext for auto key";
    }

}
