package main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.utils.BytesToHex;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256ChecksumCalculator implements ChecksumCalculator {

    private static final String ALGORITHM_SHA3_256 = "SHA3-256";

    @Override
    public String calculate(InputStream is) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM_SHA3_256);
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }

            byte[] hashBytes = digest.digest();
            return BytesToHex.convert(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA3-256 algorithm not available", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading input stream", e);
        }
    }

}
