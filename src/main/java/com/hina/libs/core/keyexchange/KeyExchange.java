package com.hina.libs.core.keyexchange;

import com.hina.libs.core.CryptographicAlgorithm;

/*
Khóa trao đổi (key exchange) là một phương pháp bảo mật được sử dụng để thiết lập một khóa bí mật chung giữa hai bên mà không cần phải truyền khóa đó qua mạng.
Điều này giúp đảm bảo rằng khóa bí mật không bị lộ ra ngoài trong quá trình truyền thông.
Một trong những thuật toán khóa trao đổi phổ biến nhất là Diffie-Hellman,
cho phép hai bên tạo ra một khóa bí mật chung dựa trên các giá trị công khai mà họ trao đổi với nhau.
Khi hai bên đã thiết lập được khóa bí mật chung, họ có thể sử dụng khóa này để mã hóa và giải mã dữ liệu một cách an toàn.
*/
public interface KeyExchange extends CryptographicAlgorithm {
    byte[] generatePublicKey(); // tạo khóa công khai từ khóa riêng
    byte[] computeSharedSecret(byte[] otherPublicKey); // tính toán khóa bí mật chung từ khóa công khai của bên kia
}
