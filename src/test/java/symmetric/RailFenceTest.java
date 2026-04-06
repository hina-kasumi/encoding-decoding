package symmetric;

import com.hina.libs.core.symmetric.SymmetricEncryption;
import com.hina.libs.impl.symmetric.RailFence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RailFenceTest {
    private SymmetricEncryption railFence;

    @BeforeEach
    public void init() {
        railFence = new RailFence();
    }

    @Test
    public void testEncryptDecrypt() {
        String plaintext = "HELLO WORLD";
        byte[] key = {3}; // số hàng
        byte[] encrypted = railFence.encrypt(plaintext.getBytes(), key);
        byte[] decrypted = railFence.decrypt(encrypted, key);
        String decryptedText = new String(decrypted);

        assert plaintext.equals(decryptedText) : "Decrypted text should match original plaintext";
    }

    @Test
    public void testEncryptDecryptWithDifferentKey() {
        String plaintext = "HELLO WORLD";
        byte[] key1 = {3}; // số hàng
        byte[] key2 = {4}; // số hàng khác
        byte[] encrypted = railFence.encrypt(plaintext.getBytes(), key1);
        byte[] decrypted = railFence.decrypt(encrypted, key2);
        String decryptedText = new String(decrypted);

        assert !plaintext.equals(decryptedText) : "Decrypted text should not match original plaintext with different keys";
    }
}
