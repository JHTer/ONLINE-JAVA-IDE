package org.olexec.execute;

import org.olexec.core.BytecodePatcher;
import org.springframework.stereotype.Component;

/**
 * Constant-pool patcher that redirects System & Scanner references to sandbox
 * helpers.
 */
@Component
public class DefaultBytecodePatcher implements BytecodePatcher {
    @Override
    public byte[] patch(byte[] original) {
        ClassModifier cm = new ClassModifier(original);
        return cm.modifyUTF8Constant("java/lang/System", "org/olexec/sandbox/SafeSystemStubs");
    }
} 