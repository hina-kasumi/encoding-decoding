package com.hina.libs.core.symmetric;

import com.hina.libs.core.CryptographicAlgorithm;

/*
Mã hóa đối xứng sử dụng một khóa duy nhất để mã hóa và giải mã dữ liệu.
Điều này có nghĩa là cùng một khóa được sử dụng để mã hóa dữ liệu và giải mã dữ liệu.
Do đó, người dùng phải đảm bảo rằng khóa được giữ bí mật và chỉ chia sẻ với những người mà họ tin tưởng.
Mã hóa đối xứng thường nhanh hơn so với mã hóa bất đối xứng, nhưng nó có một số hạn chế về bảo mật nếu khóa bị lộ hoặc bị đánh cắp.
*/
public interface SymmetricEncryption extends CryptographicAlgorithm {
    byte[] encrypt(byte[] plaintext, byte[] key); // Mã hóa dữ liệu bằng khóa đối xứng
    byte[] decrypt(byte[] ciphertext, byte[] key); // Giải mã dữ liệu bằng khóa đối xứng
}
