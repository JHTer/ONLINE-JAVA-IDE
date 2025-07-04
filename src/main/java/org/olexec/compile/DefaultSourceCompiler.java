package org.olexec.compile;

import org.olexec.core.SourceCompiler;
import org.springframework.stereotype.Component;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

/**
 * Spring bean adapter around the existing {@link StringSourceCompiler} static helper.
 */
@Component
public class DefaultSourceCompiler implements SourceCompiler {
    @Override
    public byte[] compile(String source, DiagnosticCollector<JavaFileObject> collector) {
        return StringSourceCompiler.compile(source, collector);
    }
} 