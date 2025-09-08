package main.java.com.bg.sofia.uni.fmi.calculator.checksum.report;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.DirectoryNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileSystemNode;

public class XMLFormatter implements ReportFormatter {

    private static final int INDENTATION_ITEM_INFO = 4;
    private static final int INDENTATION_ITEM = 2;
    private static final String MODE = "binary";

    @Override
    public String format(FileSystemNode root) {
        StringBuilder stringBuilder = new StringBuilder("<checksum>\n");
        appendNode(stringBuilder, root);

        return stringBuilder.append("</checksum>\n").toString();
    }

    private void appendNode(StringBuilder stringBuilder, FileSystemNode node) {
        if (node instanceof FileNode file) {
            stringBuilder.append(" ".repeat(INDENTATION_ITEM)).append("<item>\n");

            stringBuilder.append(" ".repeat(INDENTATION_ITEM_INFO)).append(String.format("<mode>%s</mode>\n", MODE));
            stringBuilder.append(" ".repeat(INDENTATION_ITEM_INFO)).append(String.format("<checksum>%s</checksum>\n", file.getChecksum()));
            stringBuilder.append(" ".repeat(INDENTATION_ITEM_INFO)).append(String.format("<path>%s</path>\n", file.getPath()));
            stringBuilder.append(" ".repeat(INDENTATION_ITEM_INFO)).append(String.format("<size>%d</size>\n", file.getSize()));

            stringBuilder.append(" ".repeat(INDENTATION_ITEM)).append("</item>\n");
        } else if (node instanceof DirectoryNode dir) {
            for (FileSystemNode dirNode : dir.getContent()) {
                appendNode(stringBuilder, dirNode);
            }
        }
    }

}
