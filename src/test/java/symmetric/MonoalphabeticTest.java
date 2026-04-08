package symmetric;

import com.hina.libs.core.symmetric.SymmetricEncryption;
import com.hina.libs.impl.symmetric.Monoalphabetic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonoalphabeticTest {
    private SymmetricEncryption cipher;

    @BeforeEach
    public void init() {
        cipher = new Monoalphabetic();
    }

    @Test
    public void testEncryptDecrypt() {
        String key = "QWERTYUIOPASDFGHJKLZXCVBNM"; // Key hợp lệ
        String plaintext = "HELLO WORLD";
        byte[] encrypted = cipher.encrypt(plaintext.getBytes(), key.getBytes());
        byte[] decrypted = cipher.decrypt(encrypted, key.getBytes());
        assert new String(decrypted).equals(plaintext);
    }

    @Test
    public void testEncryptWithInvalidKey() {
        String invalidKey = "ABCDE"; // Key không đủ 26 ký tự
        String plaintext = "TEST";
        try {
            cipher.encrypt(plaintext.getBytes(), invalidKey.getBytes());
            assert false; // Phải ném ngoại lệ
        } catch (IllegalArgumentException e) {
            assert true; // Đúng, ngoại lệ đã được ném
        }
    }

    @Test
    public void testEncryptDecryptWithDifferentKeys() {
        String key1 = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String key2 = "MNBVCXZLKJHGFDSAPOIUYTREWQ"; // Key khác
        String plaintext = "HELLO WORLD";
        byte[] encrypted = cipher.encrypt(plaintext.getBytes(), key1.getBytes());
        assert !new String(cipher.decrypt(encrypted, key2.getBytes())).equals(plaintext); // Decrypt với key khác sẽ không ra plaintext
    }
}
