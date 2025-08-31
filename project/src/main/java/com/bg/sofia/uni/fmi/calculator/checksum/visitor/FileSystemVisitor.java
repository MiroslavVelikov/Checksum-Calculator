package main.java.com.bg.sofia.uni.fmi.calculator.checksum.visitor;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.DirectoryNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileNode;

public interface FileSystemVisitor {

    void visitFile(FileNode file);
    void visitDirectory(DirectoryNode directory);

}
