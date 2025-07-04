package org.olexec.io;

public class IOContextHolder {
    private static final ThreadLocal<IOContext> HOLDER = new ThreadLocal<>();

    public static void set(IOContext ctx) {
        HOLDER.set(ctx);
    }

    public static IOContext get() {
        return HOLDER.get();
    }

    public static void clear() {
        HOLDER.remove();
    }
} 