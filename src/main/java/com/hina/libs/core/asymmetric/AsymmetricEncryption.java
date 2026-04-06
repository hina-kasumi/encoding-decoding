package com.hina.libs.core.asymmetric;

import com.hina.libs.core.CryptographicAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;

/*
Mã hóa đối xứng sử dụng một cặp khóa: một khóa công khai (public key) để mã hóa dữ liệu và một khóa riêng (private key) để giải mã dữ liệu.
Điều này cho phép người dùng chia sẻ khóa công khai mà không lo lắng về việc ai đó có thể giải mã dữ liệu nếu họ không có khóa riêng.
*/
public interface AsymmetricEncryption extends CryptographicAlgorithm {
    byte[] encrypt(byte[] plaintext, PublicKey publicKey); // mã hóa dữ liệu bằng khóa công khai
    byte[] decrypt(byte[] ciphertext, PrivateKey privateKey); // giải mã dữ liệu bằng khóa riêng
}