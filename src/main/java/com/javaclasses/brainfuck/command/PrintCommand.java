package com.javaclasses.brainfuck.command;

import com.javaclasses.brainfuck.CommandVisitor;

public class PrintCommand extends AbstractCommand {

    @Override
    public void acceptVisitor(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
