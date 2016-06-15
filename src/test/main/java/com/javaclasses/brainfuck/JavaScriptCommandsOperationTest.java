package com.javaclasses.brainfuck;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class JavaScriptCommandsOperationTest {

    final Analyser analyzer = new Analyser();
    final JavaScriptCodeGenerator generator = new JavaScriptCodeGenerator();

    @Test
    public void IncrementTest() {
        String input = "+";
        String expected = "memory[pointer]++;\n";
        String message = "Wrong logic of increment operator";
        createTestCondition(input, expected, message);
    }

    @Test
    public void DecrementTest() {
        String input = "-";
        String expected = "memory[pointer]--;\n";
        String message = "Wrong logic of decrement operator";
        createTestCondition(input, expected, message);
    }

    @Test
    public void MovePointerRightTest() {
        String input = ">";
        String expected = "pointer++;\n";
        String message = "Wrong logic of move right operator";
        createTestCondition(input, expected, message);
    }

    @Test
    public void MovePointerLeftTest() {
        String input = "<";
        String expected = "pointer--;\n";
        String message = "Wrong logic of move left operator";
        createTestCondition(input, expected, message);
    }

    @Test
    public void emptyLoopTest() {
        String input = "[]";
        String expected = "while ( memory[pointer] > 0 ) {\n" +
                "}\n";
        String message = "Wrong logic of move left operator";
        createTestCondition(input, expected, message);
    }

    @Test
    public void simpleLoopTest() {
        String input = "[+-]";
        String expected = "while ( memory[pointer] > 0 ) {\n" +
                "memory[pointer]++;\n" +
                "memory[pointer]--;\n" +
                "}\n";
        String message = "Wrong logic of move left operator";
        createTestCondition(input, expected, message);
    }


    private void createTestCondition(String input, String expected, String message) {
        String actual = "";
        List<Command> commands = analyzer.parseProgram(input);
        for (Command current : commands) {
            current.acceptVisitor(generator);
            actual = generator.getCode();
        }
        Assert.assertEquals(message, expected, actual);
    }

}
