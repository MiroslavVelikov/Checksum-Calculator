package test.java.com.bg.sofia.uni.fmi.calculator.checksum.utils;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.utils.BytesToHex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BytesToHexTest {

    @Test
    void testConvertEmptyArray() {
        byte[] input = new byte[0];
        String result = BytesToHex.convert(input);
        assertEquals("", result);
    }

    @Test
    void testConvertSingleByte() {
        byte[] input = new byte[] { (byte) 0x0A };
        String result = BytesToHex.convert(input);
        assertEquals("0a", result);
    }

    @Test
    void testConvertMultipleBytes() {
        byte[] input = new byte[] { (byte) 0x00, (byte) 0xFF, (byte) 0x10, (byte) 0xAB };
        String result = BytesToHex.convert(input);
        assertEquals("00ff10ab", result);
    }

    @Test
    void testConvertAllPossibleByteValues() {
        byte[] input = new byte[256];
        for (int i = 0; i < 256; i++) {
            input[i] = (byte) i;
        }

        String result = BytesToHex.convert(input);
        StringBuilder expected = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            expected.append(String.format("%02x", i));
        }

        assertEquals(expected.toString(), result);
    }

    @Test
    void testConvertNegativeBytes() {
        byte[] input = new byte[] { (byte) -1, (byte) -128, (byte) -10 };
        String result = BytesToHex.convert(input);
        assertEquals("ff80f6", result);
    }

}
