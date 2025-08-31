package main.java.com.bg.sofia.uni.fmi.calculator.checksum.memento;

public class ScanMemento {

    private final long bytesProcessed;

    public ScanMemento(long bytesProcessed) {
        this.bytesProcessed = bytesProcessed;
    }

    public long getBytesProcessed() {
        return bytesProcessed;
    }

}
