package org.olexec.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Holds per-execution standard input / output buffers.
 */
public class IOContext {
    private final InputStream in;
    private final ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
    private final PrintStream out;

    public IOContext(String stdin) {
        this.in = new ByteArrayInputStream(stdin.getBytes());
        this.out = new PrintStream(outBuffer, true);
    }

    public InputStream getIn() {
        return in;
    }

    public PrintStream getOut() {
        return out;
    }

    public PrintStream getErr() {
        return out; // redirect err to same buffer
    }

    public String getOutputString() {
        return outBuffer.toString();
    }

    public void close() {
        try { in.close(); } catch (Exception ignored) {}
        out.close();
    }
} 