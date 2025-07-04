package org.olexec.sandbox;

import org.olexec.io.IOContextHolder;

import java.io.IOException;
import java.io.InputStream;

/** InputStream that delegates to the current thread's IOContext. */
class DelegatingInputStream extends InputStream {
    @Override
    public int read() throws IOException {
        IOContextHolder.get();
        InputStream in = IOContextHolder.get() != null ? IOContextHolder.get().getIn() : InputStream.nullInputStream();
        return in.read();
    }
} 