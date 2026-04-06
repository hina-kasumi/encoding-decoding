package com.hina.libs.core.hash;

import com.hina.libs.core.CryptographicAlgorithm;

/*
Hàm băm (hash function) là một thuật toán toán học được sử dụng để chuyển đổi dữ liệu đầu vào (có thể có kích thước bất kỳ) thành một chuỗi ký tự có độ dài cố định,
thường được gọi là giá trị băm (hash value) hoặc mã băm (hash code).
Hàm băm thường được sử dụng trong nhiều lĩnh vực khác nhau, bao gồm bảo mật, lưu trữ dữ liệu, và kiểm tra tính toàn vẹn của dữ liệu.
*/
public interface HashFunction extends CryptographicAlgorithm {
    byte[] hash(byte[] input);
}
