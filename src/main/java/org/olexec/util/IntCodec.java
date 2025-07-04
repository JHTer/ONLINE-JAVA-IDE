package org.olexec.util;

/** Utility for converting integers to/from byte arrays (big-endian). */
public final class IntCodec {
    private IntCodec() {}

    public static int bytesToInt(byte[] b, int start, int len) {
        int res = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            int cur = (b[i] & 0xff);
            cur <<= (--len) * 8;
            res += cur;
        }
        return res;
    }

    public static byte[] intToBytes(int num, int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte) ((num >> (8 * i)) & 0xff);
        }
        return b;
    }
} 