package main.java.com.bg.sofia.uni.fmi.calculator.checksum.visitor;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum.ChecksumCalculator;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.DirectoryNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileSystemNode;

public class ReportWriter implements FileSystemVisitor {

    private final ChecksumCalculator calculator;

    public ReportWriter(ChecksumCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void visitFile(FileNode file) {

    }

    @Override
    public void visitDirectory(DirectoryNode directory) {
        for (FileSystemNode node : directory.getContent()) {
            node.accept(this);
        }
    }

}
