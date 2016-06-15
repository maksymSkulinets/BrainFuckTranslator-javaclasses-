package com.javaclasses.brainfuck;

import com.javaclasses.brainfuck.command.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Analyser {

    final static Logger log = LoggerFactory.getLogger(Analyser.class);

    public Analyser() {

        if (log.isDebugEnabled()) {
            log.debug("Initialize");
        }
        if (log.isInfoEnabled()) {
            log.info("Initialize");
        }
    }

    public List<Command> parseProgram(String program) {

        if (log.isDebugEnabled()) {
            log.debug("Execute parseProgram(String program)");
        }

        if (program == null || program.trim().isEmpty()) {
            if (log.isDebugEnabled() || log.isInfoEnabled()) {
                String message =
                        "Throw IllegalStateException." +
                                "Cause: " +
                                "Program is empty: ";
            }

            throw new IllegalArgumentException("Program is empty.");

        }

        final Deque<List<Command>> stack = new ArrayDeque<>();

        stack.push(new ArrayList<>());

        for (char commandSymbol : program.toCharArray()) {

            switch (commandSymbol) {

                case '+':
                    stack.peek().add(new IncrementCommand());

                    if (log.isDebugEnabled()) {
                        log.debug("     parse '+' operator.");
                    }
                    break;

                case '-':
                    stack.peek().add(new DecrementCommand());

                    if (log.isDebugEnabled()) {
                        log.debug("     parse '-' operator.");
                    }
                    break;

                case '>':
                    stack.peek().add(new MovePointerRightCommand());

                    if (log.isDebugEnabled()) {
                        log.debug("     parse '>' operator.");
                    }
                    break;

                case '<':
                    stack.peek().add(new MovePointerLeftCommand());

                    if (log.isDebugEnabled()) {
                        log.debug("     parse '<' operator.");
                    }
                    break;

                case '.':
                    stack.peek().add(new PrintCommand());

                    if (log.isDebugEnabled()) {
                        log.debug("     parse '.' operator.");
                    }
                    break;

                case '[':
                    stack.push(new ArrayList<>());

                    if (log.isDebugEnabled()) {
                        log.debug("     parse '[' operator.");
                    }
                    break;

                case ']':

                    if (log.isDebugEnabled()) {
                        log.debug("     parse ']' operator.");
                    }

                    if (stack.size() < 2) {

                        if (log.isErrorEnabled()) {
                            log.error("Throw IllegalStateException." +
                                    "Cause: " +
                                    "Start loop command expected: ");
                        }

                        throw new IllegalStateException("Start loop command expected.");

                    }

                    final List<Command> loopCommands = stack.pop();
                    stack.peek().add(
                            new LoopCommand(loopCommands));
                    break;

                default:

                    if (log.isErrorEnabled()) {
                        log.error(
                                "Throw IllegalStateException." +
                                        "Cause: " +
                                        "Unknown Command: " + commandSymbol);
                    }

                    throw new IllegalStateException(
                            "Unknown command: " +
                                    commandSymbol);

            }
        }

        if (stack.size() > 1) {

            if (log.isErrorEnabled()) {
                log.error(
                        "Throw IllegalStateException." +
                                "Cause: " +
                                "Some loop is not closed: ");

            }

            throw new IllegalStateException(
                    "Some loop is not closed.");
        }

        return stack.pop();
    }

}
