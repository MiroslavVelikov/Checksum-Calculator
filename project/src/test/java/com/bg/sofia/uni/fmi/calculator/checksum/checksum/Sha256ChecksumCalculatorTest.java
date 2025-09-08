package test.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum.ChecksumCalculator;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum.Sha256ChecksumCalculator;
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

public class Sha256ChecksumCalculatorTest {

    private ChecksumCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Sha256ChecksumCalculator();
    }

    @Test
    void testCalculateWithValidInput() {
        String input = "Hello World!";
        InputStream is = new ByteArrayInputStream(input.getBytes());

        String actualHash = calculator.calculate(is);

        assertEquals("d0e47486bbf4c16acac26f8b653592973c1362909f90262877089f9c8a4536af", actualHash);
    }

    @Test
    void testCalculateThrowsRuntimeExceptionOnIOException() throws IOException {
        InputStream is = mock(InputStream.class);
        when(is.read(any(byte[].class))).thenThrow(new IOException("Test IOException"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.calculate(is));
        assertTrue(exception.getMessage().contains("Error reading input stream"));
    }

}
