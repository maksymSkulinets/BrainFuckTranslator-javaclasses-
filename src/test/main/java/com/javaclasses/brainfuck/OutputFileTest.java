package com.javaclasses.brainfuck;

import com.javaclasses.brainfuck.command.JavaCodeGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OutputFileTest {



    @Test
    public void javaGenerationTest()  {

        final Analyser analyzer = new Analyser();
        final JavaCodeGenerator codeGenerator = new JavaCodeGenerator();

        final List<Command> commands = new Analyser().
                parseProgram(
                        "++++++++[>++++[>++>+++>+++>+<<<<-]>+>" +
                                "+>->>+[<]<-]>>.>---.+++++++..+" +
                                "++.>>.<-.<.+++.------.--------." +
                                ">>+.>++.");

        JavaCodeGenerator generator = new JavaCodeGenerator();
        codeGenerator.execute(commands);

        String input = codeGenerator.getCode();
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
        final JavaScriptCodeGenerator codeGenerator = new JavaScriptCodeGenerator();

        final List<Command> commands = new Analyser().
                parseProgram(
                        "++++++++[>++++[>++>+++>+++>+<<<<-]>+>" +
                                "+>->>+[<]<-]>>.>---.+++++++..+" +
                                "++.>>.<-.<.+++.------.--------." +
                                ">>+.>++.");

        JavaScriptCodeGenerator generator = new JavaScriptCodeGenerator();
        codeGenerator.execute(commands);

        String input = codeGenerator.getCode();
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

}
