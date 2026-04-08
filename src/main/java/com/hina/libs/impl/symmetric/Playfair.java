package com.hina.libs.impl.symmetric;

import com.hina.libs.core.symmetric.SymmetricEncryption;

public class Playfair implements SymmetricEncryption {

    public Playfair() {
    }

    private char[][] createMatrix(String key) {
        char[][] matrix = new char[5][5];
        String alphabet = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ"; // Không có J
        StringBuilder temp = new StringBuilder();

        for (char c : alphabet.toCharArray()) {
            if (temp.indexOf(String.valueOf(c)) == -1 && Character.isLetter(c)) {
                temp.append(c);
            }
        }

        for (int i = 0; i < 25; i++) {
            matrix[i / 5][i % 5] = temp.charAt(i);
        }
        return matrix;
    }

    private String prepareText(String text, boolean encode) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) sb.append(c);
        }

        for (int i = 0; i < sb.length() - 1; i += 2) {
            if (sb.charAt(i) == sb.charAt(i + 1)) {
                sb.insert(i + 1, 'X'); // Thêm ký tự đệm nếu cặp trùng nhau
            }
        }
        if (sb.length() % 2 != 0) sb.append('X'); // Thêm ký tự đệm nếu lẻ
        return sb.toString();
    }

    private String process(String text, char[][] matrix, int direction) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);
            int[] posA = findPosition(a, matrix);
            int[] posB = findPosition(b, matrix);

            if (posA[0] == posB[0]) { // Cùng hàng
                out.append(matrix[posA[0]][(posA[1] + direction) % 5]);
                out.append(matrix[posB[0]][(posB[1] + direction) % 5]);
            } else if (posA[1] == posB[1]) { // Cùng cột
                out.append(matrix[(posA[0] + direction) % 5][posA[1]]);
                out.append(matrix[(posB[0] + direction) % 5][posB[1]]);
            } else { // Hình chữ nhật
                out.append(matrix[posA[0]][posB[1]]);
                out.append(matrix[posB[0]][posA[1]]);
            }
        }
        return out.toString();
    }

    private int[] findPosition(char c, char[][] matrix) {
        for (int r = 0; r < 5; r++) {
            for (int col = 0; col < 5; col++) {
                if (matrix[r][col] == c) return new int[]{r, col};
            }
        }
        return null;
    }

    @Override
    public byte[] encrypt(byte[] plaintext, byte[] key) {
        String pText = prepareText(new String(plaintext).toUpperCase().replace("J", "I"), true);
        char[][] matrix = createMatrix(new String(key).toUpperCase().replace("J", "I"));
        return process(pText, matrix, 1).getBytes();
    }

    @Override
    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        String cText = new String(ciphertext).toUpperCase();
        char[][] matrix = createMatrix(new String(key).toUpperCase().replace("J", "I"));
        return process(cText, matrix, 4).getBytes(); // +4 mod 5 tương đương với -1
    }

    @Override
    public String getName() {
        return "Playfair Cipher";
    }
}