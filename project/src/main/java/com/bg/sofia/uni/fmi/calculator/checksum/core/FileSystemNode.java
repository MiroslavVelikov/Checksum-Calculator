package main.java.com.bg.sofia.uni.fmi.calculator.checksum.core;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.visitor.FileSystemVisitor;

public abstract class FileSystemNode {

    private final long size;
    private final String path;

    public FileSystemNode(long size, String path) {
        this.size = size;
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public String getPath() {
        return path;
    }

    public abstract void accept(FileSystemVisitor visitor);

}
