package symmetric;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hina.libs.core.symmetric.SymmetricEncryption;
import com.hina.libs.impl.symmetric.Playfair;

public class PlayfairTest {
    private SymmetricEncryption playfair;

    @BeforeEach
    public void init() {
        playfair = new Playfair();
    }

    @Test
    public void testPlayfairWithKey() {
        String key = "KEYWORD";
        String plaintext = "HELO";
        byte[] encrypted = playfair.encrypt(plaintext.getBytes(), key.getBytes());
        byte[] decrypted = playfair.decrypt(encrypted, key.getBytes());
        assert new String(decrypted).equals(plaintext.replaceAll("[^A-Z]", ""));
    }

    @Test
    public void testPlayfairWithDifferentKeys() {
        String key1 = "KEYWORD";
        String key2 = "ANOTHERKEY";
        String plaintext = "HELO";
        byte[] encrypted = playfair.encrypt(plaintext.getBytes(), key1.getBytes());
        byte[] decrypted = playfair.decrypt(encrypted, key2.getBytes());
        assert !new String(decrypted).equals(plaintext.replaceAll("[^A-Z]", ""));
    }
}
