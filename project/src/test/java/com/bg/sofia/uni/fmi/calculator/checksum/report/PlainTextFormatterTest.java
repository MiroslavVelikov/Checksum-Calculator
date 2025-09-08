package test.java.com.bg.sofia.uni.fmi.calculator.checksum.report;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.DirectoryNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileSystemNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.report.PlainTextFormatter;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.report.ReportFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainTextFormatterTest {

    ReportFormatter formatter;

    @BeforeEach
    public void setUp() {
        formatter = new PlainTextFormatter();
    }

    @Test
    public void testFormatterWithValidFileInput() {
        FileNode node = new FileNode(123456, "./test/testingFile.txt");
        node.setChecksum("testingChecksum");

        assertEquals("testingChecksum *./test/testingFile.txt\n", formatter.format(node));
    }

    @Test
    public void testFormatterWithValidDirectoryInput() {
        FileNode node1 = new FileNode(123456, "./testingFile1.txt");
        node1.setChecksum("testingChecksum1");
        FileNode node2 = new FileNode(123456, "./testingFile2.txt");
        node2.setChecksum("testingChecksum2");

        DirectoryNode dir = new DirectoryNode(123456 * 2, "./");
        dir.add(node1);
        dir.add(node2);

        assertEquals("testingChecksum1 *./testingFile1.txt\ntestingChecksum2 *./testingFile2.txt\n",
            formatter.format(dir));
    }

    @Test
    public void testFormatterWithNullInput() {
        assertEquals("", formatter.format(null));
    }

    @Test
    public void testFormatterWithEmptyDirectoryInput() {
        FileSystemNode dir = new DirectoryNode(0, "./");

        assertEquals("", formatter.format(dir));
    }

}
