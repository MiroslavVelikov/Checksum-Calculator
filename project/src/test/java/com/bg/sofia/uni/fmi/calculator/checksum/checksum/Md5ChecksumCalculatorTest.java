package test.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum.ChecksumCalculator;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum.Md5ChecksumCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Md5ChecksumCalculatorTest {

    private ChecksumCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Md5ChecksumCalculator();
    }

    @Test
    void testCalculateWithValidInput() {
        String input = "Hello World!";
        InputStream is = new ByteArrayInputStream(input.getBytes());

        String actualHash = calculator.calculate(is);

        assertEquals("ed076287532e86365e841e92bfc50d8c", actualHash);
    }

    @Test
    void testCalculateThrowsRuntimeExceptionOnIOException() throws IOException {
        InputStream is = mock(InputStream.class);
        when(is.read(any(byte[].class))).thenThrow(new IOException("Test IOException"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.calculate(is));
        assertTrue(exception.getMessage().contains("Error reading input stream"));
    }

}
