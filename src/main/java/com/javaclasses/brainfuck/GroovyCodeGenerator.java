package com.javaclasses.brainfuck;

import com.javaclasses.brainfuck.command.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GroovyCodeGenerator implements CommandVisitor {

    /*TODO create commons interface CodeGenerator*/

    final static Logger log =
            LoggerFactory.getLogger(GroovyCodeGenerator.class);

    private static final String LINE_SEPARATOR =
            System.getProperty("line.separator");

    private StringBuffer сodeContainer;

    public GroovyCodeGenerator() {
        if (log.isInfoEnabled()) {
            log.info("Initialize");
        }
        if (log.isDebugEnabled()) {
            log.debug("Initialize");
        }
        сodeContainer = new StringBuffer();
    }

    public static void main(String[] args) {

        String program = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>" +
                "+>->>+[<]<-]>>.>---.+++++++..+" +
                "++.>>.<-.<.+++.------.--------." +
                ">>+.>++.";

        if (log.isInfoEnabled()) {
            log.info("BrainFuck input = " + "\"" + program + "\"");
        }
        if (log.isDebugEnabled()) {
            log.info("BrainFuck input = " + "\"" + program + "\"");
        }

        final List<Command> commands = new Analyser().
                parseProgram(program);

        GroovyCodeGenerator generator = new GroovyCodeGenerator();
        generator.execute(commands);
        String templatePath = new TemplatePathHolder().getPath("groovy");
        new TemplateModifier().execute(
                templatePath, generator.getСodeContainer(), "generatedOut/GroovyCode.groovy");


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
        сodeContainer.append("memory[pointer]++;" + LINE_SEPARATOR);
    }

    @Override
    public void visit(DecrementCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(DecrementCommand command).");
        }
        сodeContainer.append("memory[pointer]--;" + LINE_SEPARATOR);
    }

    @Override
    public void visit(MovePointerLeftCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(MovePointerLeftCommand command).");
        }
        сodeContainer.append("pointer--;" + LINE_SEPARATOR);
    }

    @Override
    public void visit(MovePointerRightCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(MovePointerRightCommand command).");
        }
        сodeContainer.append("pointer++;" + LINE_SEPARATOR);
    }

    @Override
    public void visit(PrintCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Executing visit(PrintCommand command).");
        }
        сodeContainer.append("System.out.print((char)memory[pointer]);" + "\n");
    }

    @Override
    public void visit(LoopCommand command) {

        if (log.isDebugEnabled()) {
            log.debug("Enter: visit(LoopCommand command).");
        }

        сodeContainer.append("while ( memory[pointer] > 0 ) {\n");

        for (Command innerCommand : command.getCommands()) {
            innerCommand.acceptVisitor(this);

            if (log.isDebugEnabled()) {
                log.debug("executing visit(LoopCommand command).");
            }

        }

        сodeContainer.append("}" + LINE_SEPARATOR);

        if (log.isDebugEnabled()) {
            log.debug("Exit: visit(LoopCommand command).");
        }

    }

    public String getСodeContainer() {
        if (log.isInfoEnabled()) {
            log.debug("Execute: getCodeContainer()");
        }
        if (log.isDebugEnabled()) {
            log.debug("Execute: getCodeContainer() " + LINE_SEPARATOR +
                    "Return:" + сodeContainer.toString());
        }
        return сodeContainer.toString();
    }

}


