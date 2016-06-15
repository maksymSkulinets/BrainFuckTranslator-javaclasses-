package com.javaclasses.brainfuck;

import com.javaclasses.brainfuck.command.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GroovyCodeGenerator implements CommandVisitor {

    /*TODO create commons interface CodeGenerator*/

    final static Logger log =
            LoggerFactory.getLogger(GroovyCodeGenerator.class);

    private StringBuffer сode;

    public GroovyCodeGenerator() {
        if (log.isInfoEnabled()) {
            log.info("Initialize");
        }
        if (log.isDebugEnabled()) {
            log.debug("Initialize");
        }
        сode = new StringBuffer();
    }

    public static void main(String[] args) {
        final List<Command> commands = new Analyser().
                parseProgram(
                        "++++++++[>++++[>++>+++>+++>+<<<<-]>+>" +
                                "+>->>+[<]<-]>>.>---.+++++++..+" +
                                "++.>>.<-.<.+++.------.--------." +
                                ">>+.>++.");

        GroovyCodeGenerator generator = new GroovyCodeGenerator();
        generator.execute(commands);
        String templatePath = new TemplatePathHolder().getPath("groovy");
        new TemplateModifier().execute(
                templatePath, generator.getСode(), "generatedOut/GroovyCode.groovy");


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
        сode.append("memory[pointer]++;" + "\n");
    }

    @Override
    public void visit(DecrementCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(DecrementCommand command).");
        }
        сode.append("memory[pointer]--;" + "\n");
    }

    @Override
    public void visit(MovePointerLeftCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(MovePointerLeftCommand command).");
        }
        сode.append("pointer--;" + "\n");
    }

    @Override
    public void visit(MovePointerRightCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(MovePointerRightCommand command).");
        }
        сode.append("pointer++;" + "\n");
    }

    @Override
    public void visit(PrintCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Executing visit(PrintCommand command).");
        }
        сode.append("System.out.print((char)memory[pointer]);" + "\n");
    }

    @Override
    public void visit(LoopCommand command) {

        if (log.isDebugEnabled()) {
            log.debug("Enter: visit(LoopCommand command).");
        }

        сode.append("while ( memory[pointer] > 0 ) {\n");

        for (Command innerCommand : command.getCommands()) {
            innerCommand.acceptVisitor(this);

            if (log.isDebugEnabled()) {
                log.debug("executing visit(LoopCommand command).");
            }

        }

        сode.append("}\n");

        if (log.isDebugEnabled()) {
            log.debug("Exit: visit(LoopCommand command).");
        }

    }

    public String getСode() {
        if (log.isInfoEnabled()) {
            log.debug("Execute: getCode()");
        }
        if (log.isDebugEnabled()) {
            log.debug("Execute: getCode() " +
                    "\n Return:" + сode.toString());
        }
        return сode.toString();
    }


}


