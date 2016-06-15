package com.javaclasses.brainfuck;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GroovyExecutorTest {

    static final String LINE_SEPARATOR =
            System.getProperty("line.separator");

    @Test
    public void groovyExecutorTest() {


        final Analyser analyzer = new Analyser();
        final GroovyCodeGenerator generator = new GroovyCodeGenerator();

        final List<Command> commands = new Analyser().
                parseProgram(
                        "++++++++[>++++[>++>+++>+++>+<<<<-]>+>" +
                                "+>->>+[<]<-]>>.>---.+++++++..+" +
                                "++.>>.<-.<.+++.------.--------." +
                                ">>+.>++.");

        generator.execute(commands);

        String codeBuffer = generator.getСodeContainer();
        String actualPath = "testresources/ActualGroovyCode.txt";
        String expectedPath = "testresources/ExpectedGroovyCode.txt";
        String templatePath = new TemplatePathHolder().getPath("groovy");

        TemplateModifier modifier = new TemplateModifier();
        FileOperationManager.writeToFile(actualPath, codeBuffer);
        modifier.execute(templatePath, codeBuffer, "generatedOut/GroovyCode.groovy");
        String actual = GroovyExecutor.run("generatedOut/GroovyCode.groovy", "execute");
        String expected = "Hello World!\n";

        Assert.assertEquals("Not equals actual and expected models", expected, actual);

    }
}
