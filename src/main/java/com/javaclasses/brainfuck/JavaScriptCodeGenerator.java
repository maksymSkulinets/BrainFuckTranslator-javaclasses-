package com.javaclasses.brainfuck;

import com.javaclasses.brainfuck.command.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JavaScriptCodeGenerator implements CommandVisitor {

    final static Logger log =
            LoggerFactory.getLogger(JavaScriptCodeGenerator.class);

    private StringBuffer code;

    public JavaScriptCodeGenerator() {
        if (log.isInfoEnabled()) {
            log.info("Initialize");
        }
        if (log.isDebugEnabled()) {
            log.debug("Initialize");
        }
        code = new StringBuffer();
    }

    public static void main(String[] args) {
        final List<Command> commands = new Analyser().
                parseProgram(
                        "++++++++[>++++[>++>+++>+++>+<<<<-]>+>" +
                                "+>->>+[<]<-]>>.>---.+++++++..+" +
                                "++.>>.<-.<.+++.------.--------." +
                                ">>+.>++.");

     JavaScriptCodeGenerator generator = new JavaScriptCodeGenerator();
        generator.execute(commands);
        String templatePath = new TemplatePathHolder().getPath("javascript");
        new TemplateModifier().execute(
                templatePath,generator.getCode(),"generatedOut/JavaScriptCode.html");
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
        code.append("memory[pointer]++;" + "\n");
    }

    @Override
    public void visit(DecrementCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(DecrementCommand command).");
        }
        code.append("memory[pointer]--;" + "\n");
    }

    @Override
    public void visit(MovePointerLeftCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(MovePointerLeftCommand command).");
        }
        code.append("pointer--;" + "\n");
    }

    @Override
    public void visit(MovePointerRightCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Execute: visit(MovePointerRightCommand command).");
        }
        code.append("pointer++;" + "\n");
    }

    @Override
    public void visit(PrintCommand command) {
        if (log.isDebugEnabled()) {
            log.debug("Executing visit(PrintCommand command).");
        }
        /*TODO WTF!!!???*/
        code.append("buffer = buffer + String.fromCharCode(memory[pointer])" + "\n");
    }

    @Override
    public void visit(LoopCommand command) {

        if (log.isDebugEnabled()) {
            log.debug("Enter: visit(LoopCommand command).");
        }

        code.append("while ( memory[pointer] > 0 ) {\n");

        for (Command innerCommand : command.getCommands()) {
            innerCommand.acceptVisitor(this);

            if (log.isDebugEnabled()) {
                log.debug("executing visit(LoopCommand command).");
            }

        }

        code.append("}\n");

        if (log.isDebugEnabled()) {
            log.debug("Exit: visit(LoopCommand command).");
        }

    }

    public String getCode() {
        if (log.isInfoEnabled()) {
            log.debug("Execute: getCode()");
        }
        if (log.isDebugEnabled()) {
            log.debug("Execute: getCode() " +
                    "\n Return:" + code.toString());
        }
        return code.toString();
    }
}


