package com.javaclasses.brainfuck;

import com.javaclasses.brainfuck.command.JavaCodeGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OutputFileTest {



    @Test
    public void javaGenerationTest()  {

        final Analyser analyzer = new Analyser();
        final JavaCodeGenerator generator = new JavaCodeGenerator();

        final List<Command> commands = new Analyser().
                parseProgram(
                        "++++++++[>++++[>++>+++>+++>+<<<<-]>+>" +
                                "+>->>+[<]<-]>>.>---.+++++++..+" +
                                "++.>>.<-.<.+++.------.--------." +
                                ">>+.>++.");

        generator.execute(commands);

        String input = generator.getCode();
        String actualPath = "testresources/ActualJavaCode.txt";
        String expectedPath = "testresources/ExpectedJavaCode.txt";
        String templatePath = new TemplatePathHolder().getPath("java");

        TemplateModifier modifier =  new TemplateModifier();
        FileOperationManager.writeToFile(actualPath, input);
        modifier.execute(templatePath,input,actualPath);

        String expected = FileOperationManager.readFromFile(actualPath);
        String actual = FileOperationManager.readFromFile(expectedPath);

        Assert.assertEquals("Not equals actual and expected models", expected, actual);
    }

    @Test
    public void javascriptGenerationTest()  {

        final Analyser analyzer = new Analyser();
        final JavaScriptCodeGenerator generator = new JavaScriptCodeGenerator();

        final List<Command> commands = new Analyser().
                parseProgram(
                        "++++++++[>++++[>++>+++>+++>+<<<<-]>+>" +
                                "+>->>+[<]<-]>>.>---.+++++++..+" +
                                "++.>>.<-.<.+++.------.--------." +
                                ">>+.>++.");

        generator.execute(commands);

        String input = generator.getCode();
        String actualPath = "testresources/ActualJavaScriptCode.txt";
        String expectedPath = "testresources/ExpectedJavaScriptCode.txt";
        String templatePath = new TemplatePathHolder().getPath("javascript");

        TemplateModifier modifier =  new TemplateModifier();
        FileOperationManager.writeToFile(actualPath, input);
        modifier.execute(templatePath,input,actualPath);

        String expected = FileOperationManager.readFromFile(actualPath);
        String actual = FileOperationManager.readFromFile(expectedPath);

        Assert.assertEquals("Not equals actual and expected models", expected, actual);
    }

    @Test
    public void groovyGenerationTest()  {

        final Analyser analyzer = new Analyser();
        final GroovyCodeGenerator generator = new GroovyCodeGenerator();

        final List<Command> commands = new Analyser().
                parseProgram(
                        "++++++++[>++++[>++>+++>+++>+<<<<-]>+>" +
                                "+>->>+[<]<-]>>.>---.+++++++..+" +
                                "++.>>.<-.<.+++.------.--------." +
                                ">>+.>++.");

        generator.execute(commands);

        String input = generator.getСode();
        String actualPath = "testresources/ActualGroovyCode.txt";
        String expectedPath = "testresources/ExpectedGroovyCode.txt";
        String templatePath = new TemplatePathHolder().getPath("groovy");

        TemplateModifier modifier =  new TemplateModifier();
        FileOperationManager.writeToFile(actualPath, input);
        modifier.execute(templatePath,input,actualPath);

        String expected = FileOperationManager.readFromFile(actualPath);
        String actual = FileOperationManager.readFromFile(expectedPath);

        Assert.assertEquals("Not equals actual and expected models", expected, actual);
    }


}
