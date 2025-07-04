package org.olexec.execute;

import org.olexec.core.BytecodePatcher;
import org.olexec.core.ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.olexec.io.IOContext;
import org.olexec.io.IOContextHolder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Adapter around {@link JavaClassExecutor} for DI.
 */
@Component
public class DefaultClassRunner implements ClassRunner {

    @Autowired
    private BytecodePatcher patcher;

    @Override
    public String run(byte[] classBytes, String systemIn) {
        byte[] patched = patcher.patch(classBytes);

        // Prepare IO context for this execution
        IOContext ctx = new IOContext(systemIn);
        IOContextHolder.set(ctx);

        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class<?> clazz = loader.loadByte(patched);

        try {
            Method main = clazz.getMethod("main", String[].class);
            main.invoke(null, (Object) new String[]{null});
        } catch (NoSuchMethodException | IllegalAccessException e) {
            return e.getMessage();
        } catch (InvocationTargetException e) {
            if (IOContextHolder.get()!=null) {
                e.getCause().printStackTrace(IOContextHolder.get().getErr());
            }
        }

        String output = ctx.getOutputString();
        ctx.close();
        IOContextHolder.clear();
        return output;
    }
} 