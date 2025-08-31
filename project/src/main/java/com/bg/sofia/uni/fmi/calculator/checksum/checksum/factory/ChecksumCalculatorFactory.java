package main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum.factory;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum.ChecksumCalculator;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum.ChecksumType;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum.Md5ChecksumCalculator;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum.Sha256ChecksumCalculator;

public class ChecksumCalculatorFactory {

    public static ChecksumCalculator create(String algorithm) {
        try {
            return switch (ChecksumType.valueOf(algorithm.toUpperCase())) {
                case SHA256 -> new Sha256ChecksumCalculator();
                case MD5 -> new Md5ChecksumCalculator();
            };
        } catch (IllegalArgumentException | NullPointerException e) {
            return new Md5ChecksumCalculator();
        }
    }

}
