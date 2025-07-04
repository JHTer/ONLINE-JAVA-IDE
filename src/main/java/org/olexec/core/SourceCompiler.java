package org.olexec.core;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

/**
 * Compiles in-memory Java source and returns the resulting byte-code.
 */
public interface SourceCompiler {
    byte[] compile(String source, DiagnosticCollector<JavaFileObject> collector);
} 