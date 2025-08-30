package main.java.com.bg.sofia.uni.fmi.calculator.checksum.model.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLineOptions {

    private final String path;
    private final String algorithm;
    private final String checksumsFile;
    private final String format;

    private CommandLineOptions(String path, String algorithm, String checksumsFile, String format) {
        this.path = path;
        this.algorithm = algorithm;
        this.checksumsFile = checksumsFile;
        this.format = format;
    }

    public static CommandLineOptions parse(String[] args) {
        Options options = new Options();

        Option pathOpt = Option.builder()
            .longOpt("path")
            .hasArg()
            .desc("File or directory path to process. Defaults to current directory.")
            .build();
        Option algorithmOpt = Option.builder()
            .longOpt("algorithm")
            .hasArg()
            .desc("Checksum algorithm (md5, sha256, etc.). Default: md5")
            .build();
        Option checksumsOpt = Option.builder()
            .longOpt("checksums")
            .hasArg()
            .desc("Path to a file containing precomputed checksums for verification mode.")
            .build();
        Option formatOpt = Option.builder()
            .longOpt("format")
            .hasArg()
            .desc("Output format: text, xml, json, etc. Default: text")
            .build();
        Option helpOpt = new Option("h", "help", false, "Show help message");

        options.addOption(pathOpt);
        options.addOption(algorithmOpt);
        options.addOption(checksumsOpt);
        options.addOption(formatOpt);
        options.addOption(helpOpt);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("help")) {
                formatter.printHelp("checksum-calculator", options);
                System.exit(0);
            }

            String path = cmd.getOptionValue("path", ".");
            String algorithm = cmd.getOptionValue("algorithm", "md5");
            String checksumsFile = cmd.getOptionValue("checksums");
            String format = cmd.getOptionValue("format", "text");

            return new CommandLineOptions(path, algorithm, checksumsFile, format);

        } catch (ParseException e) {
            System.err.println("Error: " + e.getMessage());
            formatter.printHelp("checksum-calculator", options);
            System.exit(1);
        }

        return null;
    }

    public String getPath() {
        return path;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getChecksumsFile() {
        return checksumsFile;
    }

    public String getFormat() {
        return format;
    }

}
