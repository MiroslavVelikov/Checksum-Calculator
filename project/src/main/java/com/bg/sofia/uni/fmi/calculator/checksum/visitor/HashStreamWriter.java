package main.java.com.bg.sofia.uni.fmi.calculator.checksum.visitor;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum.ChecksumCalculator;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.DirectoryNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileSystemNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.observer.Observable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HashStreamWriter extends Observable implements FileSystemVisitor {

    private final ChecksumCalculator calculator;

    public HashStreamWriter(ChecksumCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void visitFile(FileNode file) {
        notifyObservers("Processing " + file.getPath());

        try (InputStream is = new FileInputStream(file.getPath())) {
            String checksum = calculator.calculate(is);
            file.setChecksum(checksum);
            notifyObservers("Checksum for " + file.getPath() + ": " + checksum);
        } catch (IOException e) {
            notifyObservers("Error reading file: " + file.getPath());
        }
    }

    @Override
    public void visitDirectory(DirectoryNode directory) {
        for (FileSystemNode node : directory.getContent()) {
            node.accept(this);
        }
    }

}
