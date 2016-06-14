package com.javaclasses.brainfuck.command;

import com.javaclasses.brainfuck.Command;
import com.javaclasses.brainfuck.CommandVisitor;
import com.javaclasses.brainfuck.Analyser;

import java.util.List;

public class JavaCodeGenerator implements CommandVisitor {

    private StringBuffer javaCode;

    public JavaCodeGenerator() {
        javaCode = new StringBuffer();
    }

    public static void main(String[] args) {
        final List<Command> commands = new Analyser().
                parseProgram(
                        "++++++++[>++++[>++>+++>+++>+<<<<-]>+>" +
                                "+>->>+[<]<-]>>.>---.+++++++..+" +
                                "++.>>.<-.<.+++.------.--------." +
                                ">>+.>++.");

        JavaCodeGenerator generator = new JavaCodeGenerator();

    }

    public void execute(List<Command> commands) {
        for (Command command : commands) {
            command.acceptVisitor(this);
        }
    }

    @Override
    public void visit(IncrementCommand command) {
        javaCode.append("memory[pointer]++;" + "\n");
    }

    @Override
    public void visit(DecrementCommand command) {
        javaCode.append("memory[pointer]--;" + "\n");
    }

    @Override
    public void visit(MovePointerLeftCommand command) {
        javaCode.append("pointer--;" + "\n");
    }

    @Override
    public void visit(MovePointerRightCommand command) {
        javaCode.append("pointer++;" + "\n");
    }

    @Override
    public void visit(PrintCommand command) {
        StringBuffer append = javaCode.append("System.out.print(\"memory[pointer]\")" + "\n");
    }

    @Override
    public void visit(LoopCommand command) {
        javaCode.append("while ( memory[pointer] > 0 ) {\n");

        for (Command innerCommand : command.getCommands()) {
            innerCommand.acceptVisitor(this);
        }

        javaCode.append("}\n");
    }

    public String getCode() {
        return javaCode.toString();
    }
}

