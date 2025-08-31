package main.java.com.bg.sofia.uni.fmi.calculator.checksum.progress;

import main.java.com.bg.sofia.uni.fmi.calculator.checksum.observer.Observable;
import main.java.com.bg.sofia.uni.fmi.calculator.checksum.observer.Observer;

public class ProgressReporter implements Observer {

    private String currentFile = "";
    private long bytesProcessed = 0;

    @Override
    public void update(Observable source, Object message) {
        if (message instanceof String msg) {
            if (msg.startsWith("Processing")) {
                currentFile = msg.substring("Processing".length()).trim();
                System.out.println("\n" + msg);
                bytesProcessed = 0;
            }
            else if (msg.startsWith("Checksum for")) {
                System.out.println(msg);
            }
            else if (msg.startsWith("Error")) {
                System.err.println(msg);
            }
            else {
                System.out.println(msg);
            }
        }
    }

}
