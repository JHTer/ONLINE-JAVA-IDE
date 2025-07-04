package org.olexec.util;

import java.nio.charset.StandardCharsets;

public final class StringCodec {
    private StringCodec() {}

    public static String bytesToString(byte[] b, int start, int len) {
        return new String(b, start, len, StandardCharsets.UTF_8);
    }

    public static byte[] stringToBytes(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }
} 