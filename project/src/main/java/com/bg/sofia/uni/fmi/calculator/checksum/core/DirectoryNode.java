package main.java.com.bg.sofia.uni.fmi.calculator.checksum.core;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.visitor.FileSystemVisitor;

import java.util.ArrayList;
import java.util.List;

public class DirectoryNode extends FileSystemNode {

    private final List<FileSystemNode> content;

    public DirectoryNode(long size, String path) {
        super(size, path);
        content = new ArrayList<>();
    }

    public void add(FileSystemNode node) {
        content.add(node);
    }

    public List<FileSystemNode> getContent() {
        return content;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitDirectory(this);
    }

}
