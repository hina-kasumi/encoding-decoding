package com.hina.libs.core.signature;

import com.hina.libs.core.CryptographicAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;

/*
Chữ ký số (digital signature) là một phương pháp bảo mật được sử dụng để xác minh tính toàn vẹn và xác thực của dữ liệu.
Nó cho phép người nhận xác định xem dữ liệu có bị thay đổi hay không và xác nhận danh tính của người gửi.
Chữ ký số thường được tạo ra bằng cách sử dụng một thuật toán băm (hash function) để tạo ra một giá trị băm từ dữ liệu gốc,
sau đó giá trị băm này được mã hóa bằng khóa riêng của người gửi để tạo thành chữ ký số.
Người nhận có thể sử dụng khóa công khai của người gửi để giải mã chữ ký số và so sánh giá trị băm đã giải mã với
giá trị băm được tạo ra từ dữ liệu gốc để xác minh tính toàn vẹn và xác thực của dữ liệu.
*/
public interface DigitalSignature extends CryptographicAlgorithm {
    byte[] sign(byte[] message, PrivateKey privateKey); // ký dữ liệu bằng khóa riêng
    boolean verify(byte[] message, byte[] signature, PublicKey publicKey); // kiểm tra chữ ký số bằng khóa công khai
}
