package org.olexec.sandbox;

import org.olexec.io.IOContextHolder;

import java.io.PrintStream;

/** PrintStream that delegates to current IOContext. */
class DelegatingPrintStream extends PrintStream {
    DelegatingPrintStream() { super(System.out); }
    @Override
    public void write(int b) {
        if (IOContextHolder.get() != null) {
            IOContextHolder.get().getOut().write(b);
        }
    }
    @Override
    public void write(byte[] buf, int off, int len) {
        if (IOContextHolder.get() != null) {
            IOContextHolder.get().getOut().write(buf, off, len);
        }
    }
} 