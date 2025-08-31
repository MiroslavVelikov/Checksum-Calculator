package test.java.com.bg.sofia.uni.fmi.calculator.checksum.report;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.report.ReportFormatter;
import org.junit.jupiter.api.BeforeEach;

public class PlainTextFormatter {

    ReportFormatter formatter;

    @BeforeEach
    public void setUp() {
        formatter = new PlainTextFormatter();
    }

}
