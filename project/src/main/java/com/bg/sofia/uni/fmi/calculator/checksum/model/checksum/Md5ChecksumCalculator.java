package main.java.com.bg.sofia.uni.fmi.calculator.checksum.model.checksum;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.model.utils.BytesToHex;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5ChecksumCalculator implements ChecksumCalculator {

    private static final String ALGORITHM_MD5 = "MD5";

    @Override
    public String calculate(InputStream is) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM_MD5);
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }

            byte[] hashBytes = digest.digest();
            return BytesToHex.convert(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading input stream", e);
        }
    }
    
}
