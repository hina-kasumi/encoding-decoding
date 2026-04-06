package symmetric;

import com.hina.libs.core.symmetric.SymmetricEncryption;
import com.hina.libs.impl.symmetric.Caesar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaesarTest {
    private SymmetricEncryption caesar;

    @BeforeEach
    public void init() {
        caesar = new Caesar();
    }

    @Test
    public void testEncryptDecrypt() {
        String plaintext = "Hello, World!";
        byte[] key = {3}; // shift 3
        byte[] encrypted = caesar.encrypt(plaintext.getBytes(), key);
        byte[] decrypted = caesar.decrypt(encrypted, key);
        String decryptedText = new String(decrypted);

        assert plaintext.equals(decryptedText) : "Decrypted text should match original plaintext";
    }

    @Test
    public void testEncryptWithDifferentShift() {
        String plaintext = "Hello, World!";
        byte[] key = {5}; // shift 5
        byte[] encrypted = caesar.encrypt(plaintext.getBytes(), key);
        byte[] decrypted = caesar.decrypt(encrypted, key);
        String decryptedText = new String(decrypted);

        assert plaintext.equals(decryptedText) : "Decrypted text should match original plaintext with different shift";
    }

    @Test
    public void testEncryptWithNegativeShift() {
        String plaintext = "Hello, World!";
        byte[] key = {-3}; // shift -3
        byte[] encrypted = caesar.encrypt(plaintext.getBytes(), key);
        byte[] decrypted = caesar.decrypt(encrypted, key);
        String decryptedText = new String(decrypted);
        assert plaintext.equals(decryptedText) : "Decrypted text should match original plaintext with negative shift";
    }

    @Test
    public void testEncryptAndDecryptWithDifferentKeys() {
        String plaintext = "Hello, World!";
        byte[] key1 = {3}; // shift 3
        byte[] key2 = {5}; // shift 5
        byte[] encrypted = caesar.encrypt(plaintext.getBytes(), key1);
        byte[] decrypted = caesar.decrypt(encrypted, key2);
        String decryptedText = new String(decrypted);

        assert !plaintext.equals(decryptedText) : "Decrypted text should not match original plaintext with different keys";
    }
}
