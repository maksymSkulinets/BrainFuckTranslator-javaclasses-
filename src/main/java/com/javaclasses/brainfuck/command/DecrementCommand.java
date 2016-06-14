package com.javaclasses.brainfuck.command;

import com.javaclasses.brainfuck.CommandVisitor;

public class DecrementCommand extends ValueAwareCommand {

    public DecrementCommand() {
    }

    public DecrementCommand(int value) {
        super(value);
    }

    @Override
    public void acceptVisitor(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
