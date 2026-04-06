package com.hina.libs.impl.symmetric;

import com.hina.libs.core.symmetric.SymmetricEncryption;

public class RailFence implements SymmetricEncryption {
    public RailFence() {
    }

    @Override
    public byte[] encrypt(byte[] plaintext, byte[] key) {
        int rails = key[0];
        if (rails <= 1) return plaintext;

        int len = plaintext.length;
        byte[] result = new byte[len];

        // tạo ma trận
        byte[][] fence = new byte[rails][len];

        boolean down = true;
        int row = 0, col = 0;

        // ghi zig-zag
        for (int i = 0; i < len; i++) {
            fence[row][col++] = plaintext[i];

            if (row == 0) {
                down = true;
            } else if (row == rails - 1) {
                down = false;
            }

            row += down ? 1 : -1;
        }

        // đọc theo hàng
        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < len; j++) {
                if (fence[i][j] != 0) {
                    result[index++] = fence[i][j];
                }
            }
        }

        return result;
    }

    @Override
    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        int rails = key[0];
        if (rails <= 1) return ciphertext;

        int len = ciphertext.length;
        byte[][] fence = new byte[rails][len];

        // bước 1: đánh dấu vị trí zig-zag
        boolean down = false;
        int row = 0, col = 0;

        for (int i = 0; i < len; i++) {
            if (row == 0 || row == rails - 1) {
                down = !down;
            }

            fence[row][col++] = 1; // đánh dấu
            row += down ? 1 : -1;
        }

        // bước 2: điền ciphertext vào
        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < len; j++) {
                if (fence[i][j] == 1 && index < len) {
                    fence[i][j] = ciphertext[index++];
                }
            }
        }

        // bước 3: đọc zig-zag
        byte[] result = new byte[len];
        row = 0;
        col = 0;
        down = false;

        for (int i = 0; i < len; i++) {
            if (row == 0 || row == rails - 1) {
                down = !down;
            }

            result[i] = fence[row][col++];
            row += down ? 1 : -1;
        }

        return result;
    }

    @Override
    public String getName() {
        return "Rail Fence Cipher";
    }
}
