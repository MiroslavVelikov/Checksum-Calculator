package main.java.com.bg.sofia.uni.fmi.calculator.checksum.report;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.DirectoryNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileSystemNode;

public class PlainTextFormatter implements ReportFormatter {

    @Override
    public String format(FileSystemNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        appendNode(stringBuilder, root);

        return stringBuilder.toString();
    }

    private void appendNode(StringBuilder sb, FileSystemNode node) {
        if (node instanceof FileNode file) {
            String checksum = file.getChecksum() != null ? file.getChecksum() : "UNKNOWN";
            sb.append(checksum)
                .append(" *")
                .append(file.getPath())
                .append("\n");
        } else if (node instanceof DirectoryNode dir) {
            for (FileSystemNode dirNode : dir.getContent()) {
                appendNode(sb, dirNode);
            }
        }
    }

}
