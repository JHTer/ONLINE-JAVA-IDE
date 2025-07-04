package org.olexec.execute;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.olexec.compile.DefaultSourceCompiler;
import org.olexec.core.BytecodePatcher;
import org.olexec.core.ClassRunner;
import org.olexec.core.SourceCompiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultClassRunnerTest {

    @Configuration
    @org.springframework.context.annotation.ComponentScan(basePackages = "org.olexec")
    static class TestConfig {
    }

    @Autowired
    private SourceCompiler compiler;

    @Autowired
    private ClassRunner runner;

    @Test
    public void runSimpleProgram() throws Exception {
        String src = "public class Run { public static void main(String[] args){ System.out.print(\"OK\"); } }";
        byte[] bytes = compiler.compile(src, new DiagnosticCollector<JavaFileObject>());
        String output = runner.run(bytes, "");
        Assert.assertEquals("OK", output);
    }
} 