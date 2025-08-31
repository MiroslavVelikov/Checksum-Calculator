package main.java.com.bg.sofia.uni.fmi.calculator.checksum;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum.ChecksumCalculator;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.checksum.factory.ChecksumCalculatorFactory;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.cli.CommandLineOptions;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.DirectoryNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.core.FileSystemNode;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.progress.ProgressReporter;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.report.PlainTextFormatter;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.report.ReportFormatter;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.visitor.HashStreamWriter;

import java.io.File;
import java.nio.file.Path;

// Testing main for current directory of the project
public class Main {
    public static void main(String[] args) {
        CommandLineOptions options = CommandLineOptions.parse(args);
        String pathStr = options.getPath();
        String algorithm = options.getAlgorithm();
        String format = options.getFormat();

        System.out.println("Starting checksum calculation...");
        System.out.println("Path: " + pathStr);
        System.out.println("Algorithm: " + algorithm);
        System.out.println("Format: " + format);

        try {
            Path path = Path.of(pathStr);
            File file = path.toFile();
            FileSystemNode root;
            if (file.isDirectory()) {
                root = buildDirectoryNode(file);
            } else {
                root = new FileNode(file.length(), path.toString());
            }

            ChecksumCalculator calculator = ChecksumCalculatorFactory.create(algorithm);

            HashStreamWriter writer = new HashStreamWriter(calculator);
            ProgressReporter reporter = new ProgressReporter();
            writer.registerObserver(reporter);

            root.accept(writer);

            ReportFormatter formatter = new PlainTextFormatter();
            String report = formatter.format(root);
            System.out.println("\n--- Checksum Report ---");
            System.out.println(report);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static DirectoryNode buildDirectoryNode(File dir) {
        DirectoryNode directoryNode = new DirectoryNode(dir.length(), dir.toPath().toString());
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    directoryNode.add(buildDirectoryNode(f));
                } else {
                    directoryNode.add(new FileNode(f.length(), f.toPath().toString()));
                }
            }
        }
        return directoryNode;
    }
}
