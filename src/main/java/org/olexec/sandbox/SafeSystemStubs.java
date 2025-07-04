package org.olexec.sandbox;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Replacement for java.lang.System inside user code. Exposes only safe
 * operations and delegates I/O to the current IOContext via delegating streams.
 */
public final class SafeSystemStubs {
    private SafeSystemStubs() {}

    public static final InputStream in = new DelegatingInputStream();
    public static final PrintStream out = new DelegatingPrintStream();
    public static final PrintStream err = out;

    // ---- Dangerous operations throw SecurityException ----
    public static void exit(int status) { throw new SecurityException("System.exit()"); }
    public static void setSecurityManager(SecurityManager s) { throw new SecurityException(); }
    public static SecurityManager getSecurityManager() { throw new SecurityException(); }
    public static void load(String filename) { throw new SecurityException(); }
    public static void loadLibrary(String libname) { throw new SecurityException(); }
    public static void gc() { throw new SecurityException(); }
    // add others as needed

    // ---- Allowed safe wrappers ----
    public static long currentTimeMillis() { return System.currentTimeMillis(); }
    public static long nanoTime() { return System.nanoTime(); }
} 