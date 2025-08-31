package main.java.com.bg.sofia.uni.fmi.calculator.checksum.report;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileSystemNode;

public interface ReportFormatter {

    String format(FileSystemNode root);

}
