package main.java.com.bg.sofia.uni.fmi.calculator.checksum.utils;

public class BytesToHex {

    public static String convert(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
