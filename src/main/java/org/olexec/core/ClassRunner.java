package org.olexec.core;

/**
 * Executes compiled byte-code and returns combined stdout/stderr output.
 */
public interface ClassRunner {
    String run(byte[] classBytes, String systemIn) throws Exception;
} 