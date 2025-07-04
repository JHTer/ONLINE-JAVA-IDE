package org.olexec.util;

public final class ArrayUtils {
    private ArrayUtils() {}

    /**
     * Replace a range of bytes in the original array with the given replacement bytes.
     */
    public static byte[] replace(byte[] original, int offset, int len, byte[] replacement) {
        byte[] newBytes = new byte[original.length + replacement.length - len];
        System.arraycopy(original, 0, newBytes, 0, offset);
        System.arraycopy(replacement, 0, newBytes, offset, replacement.length);
        System.arraycopy(original, offset + len, newBytes, offset + replacement.length,
                original.length - offset - len);
        return newBytes;
    }
} 