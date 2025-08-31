package main.java.com.bg.sofia.uni.fmi.calculator.checksum.core;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.visitor.FileSystemVisitor;

public class FileNode extends FileSystemNode {

    private String checksum;

    public FileNode(long size, String path) {
        super(size, path);
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitFile(this);
    }

}
