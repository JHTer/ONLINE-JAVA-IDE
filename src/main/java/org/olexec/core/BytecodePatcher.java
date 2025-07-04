package org.olexec.core;

/**
 * Transforms compiled class bytes before execution (e.g., to rewrite constant
 * pool entries).
 */
public interface BytecodePatcher {
    byte[] patch(byte[] original);
} 