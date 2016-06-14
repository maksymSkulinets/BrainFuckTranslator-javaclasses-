package com.javaclasses.brainfuck;

import com.javaclasses.brainfuck.command.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JavaCodeGenerator implements CommandVisitor {

    final static Logger log =
            LoggerFactory.getLogger(JavaCodeGenerator.class);

    private StringBuffer javaCode;

    public JavaCodeGenerator() {
        if (log.isInfoEnabled()) {
            log.info("Initialize");
        }
        if (log.isDebugEnabled()) {
            log.debug("Initialize");
        }
        javaCode = new StringBuffer();
    }

    public static void main(String[] args) {
        final List<Command> commands = new Analyser().
                parseProgram(
                        "++++++++[>++++[>++>+++>+++>+<<<<-]>+>" +
                                "+>->>+[<]<-]>>.>---.+++++++..+" +
                                "++.>>.<-.<.+++.------.--------." +
                                ">>+.>++.");

        com.javaclasses.brainfuck.command.JavaCodeGenerator generator = new com.javaclasses.brainfuck.command.JavaCodeGenerator();
        generator.execute(commands);
        String templatePath = new TemplatePathHolder().getPath("java");
        new TemplateModifier().execute(
                templatePath,generator.getCode(),"generatedOut/JavaCode.txt");


    }

    public void execute(List<Command> commands) {
        for (Command command : commands) {
            command.acceptVisitor(this);
        }
    }

    @Override
    public void visit(IncrementCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(IncrementCommand command).");
        }
        javaCode.append("memory[pointer]++;" + "\n");
    }

    @Override
    public void visit(DecrementCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(DecrementCommand command).");
        }
        javaCode.append("memory[pointer]--;" + "\n");
    }

    @Override
    public void visit(MovePointerLeftCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(MovePointerLeftCommand command).");
        }
        javaCode.append("pointer--;" + "\n");
    }

    @Override
    public void visit(MovePointerRightCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(MovePointerRightCommand command).");
        }
        javaCode.append("pointer++;" + "\n");
    }

    @Override
    public void visit(PrintCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Executing visit(PrintCommand command).");
        }
        javaCode.append("System.out.print(\"memory[pointer]\")" + "\n");
    }

    @Override
    public void visit(LoopCommand command) {

        if (log.isDebugEnabled()) {
            log.debug("Enter: visit(LoopCommand command).");
        }

        javaCode.append("while ( memory[pointer] > 0 ) {\n");

        for (Command innerCommand : command.getCommands()) {
            innerCommand.acceptVisitor(this);

            if (log.isDebugEnabled()) {
                log.debug("executing visit(LoopCommand command).");
            }

        }

        javaCode.append("}\n");

        if (log.isDebugEnabled()) {
            log.debug("Exit: visit(LoopCommand command).");
        }

    }

    public String getJavaCode() {
        if (log.isInfoEnabled()) {
            log.debug("Execute: getCode()");
        }
        if (log.isDebugEnabled()) {
            log.debug("Execute: getCode() " +
                    "\n Return:" + javaCode.toString());
        }
        return javaCode.toString();
    }
}

