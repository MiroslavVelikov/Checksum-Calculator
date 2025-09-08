package test.java.com.bg.sofia.uni.fmi.calculator.checksum.cli;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.cli.CommandLineOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandLineOptionTest {

    @Test
    void testDefaultValues() {
        String[] args = {};
        CommandLineOptions options = CommandLineOptions.parse(args);

        assertEquals(".", options.getPath());
        assertEquals("md5", options.getAlgorithm());
        assertNull(options.getChecksumsFile());
        assertEquals("text", options.getFormat());
    }

    @Test
    void testCustomPathAndAlgorithm() {
        String[] args = {"--path", "/tmp/files", "--algorithm", "sha256"};
        CommandLineOptions options = CommandLineOptions.parse(args);

        assertEquals("/tmp/files", options.getPath());
        assertEquals("sha256", options.getAlgorithm());
        assertNull(options.getChecksumsFile());
        assertEquals("text", options.getFormat());
    }

    @Test
    void testChecksumsFileAndFormat() {
        String[] args = {"--checksums", "/tmp/checksums.txt", "--format", "json"};
        CommandLineOptions options = CommandLineOptions.parse(args);

        assertEquals(".", options.getPath());
        assertEquals("md5", options.getAlgorithm());
        assertEquals("/tmp/checksums.txt", options.getChecksumsFile());
        assertEquals("json", options.getFormat());
    }

    @Test
    void testAllOptionsTogether() {
        String[] args = {
            "--path", "/home/user/data",
            "--algorithm", "sha512",
            "--checksums", "/home/user/checksums.txt",
            "--format", "xml"
        };
        CommandLineOptions options = CommandLineOptions.parse(args);

        assertEquals("/home/user/data", options.getPath());
        assertEquals("sha512", options.getAlgorithm());
        assertEquals("/home/user/checksums.txt", options.getChecksumsFile());
        assertEquals("xml", options.getFormat());
    }

}
