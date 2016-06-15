package com.javaclasses.brainfuck;

import com.javaclasses.brainfuck.command.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JavaScriptCodeGenerator implements CommandVisitor {

        /*TODO create commons interface CodeGenerator*/

    final static Logger log =
            LoggerFactory.getLogger(JavaScriptCodeGenerator.class);
    private static final String LINE_SEPARATOR =
            System.getProperty("line.separator");
    private StringBuffer codeContainer;


    public JavaScriptCodeGenerator() {
        if (log.isInfoEnabled()) {
            log.info("Initialize");
        }
        if (log.isDebugEnabled()) {
            log.debug("Initialize");
        }
        codeContainer = new StringBuffer();
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

        JavaScriptCodeGenerator generator = new JavaScriptCodeGenerator();
        generator.execute(commands);
        String templatePath = new TemplatePathHolder().getPath("javascript");
        new TemplateModifier().execute(
                templatePath, generator.getCodeContainer(), "generatedOut/JavaScriptCode.html");
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
        codeContainer.append("memory[pointer]++;" + LINE_SEPARATOR);
    }

    @Override
    public void visit(DecrementCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(DecrementCommand command).");
        }
        codeContainer.append("memory[pointer]--;" + LINE_SEPARATOR);
    }

    @Override
    public void visit(MovePointerLeftCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(MovePointerLeftCommand command).");
        }
        codeContainer.append("pointer--;" + LINE_SEPARATOR);
    }

    @Override
    public void visit(MovePointerRightCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(MovePointerRightCommand command).");
        }
        codeContainer.append("pointer++;" + LINE_SEPARATOR);
    }

    @Override
    public void visit(PrintCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Executing visit(PrintCommand command).");
        }

        codeContainer.append("buffer = buffer + String.fromCharCode(memory[pointer])" + "\n");
    }

    @Override
    public void visit(LoopCommand command) {

        if (log.isDebugEnabled()) {
            log.debug("Enter: visit(LoopCommand command).");
        }

        codeContainer.append("while ( memory[pointer] > 0 ) {" + LINE_SEPARATOR);

        for (Command innerCommand : command.getCommands()) {
            innerCommand.acceptVisitor(this);

            if (log.isDebugEnabled()) {
                log.debug("executing visit(LoopCommand command).");
            }

        }

        codeContainer.append("}" + LINE_SEPARATOR);

        if (log.isDebugEnabled()) {
            log.debug("Exit: visit(LoopCommand command).");
        }

    }

    public String getCodeContainer() {
        if (log.isInfoEnabled()) {
            log.debug("Execute: getCodeContainer()");
        }
        if (log.isDebugEnabled()) {
            log.debug("Execute: getCodeContainer() " + LINE_SEPARATOR +
                    "Return:" + codeContainer.toString());
        }
        return codeContainer.toString();
    }
}


