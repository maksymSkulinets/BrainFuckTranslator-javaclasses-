package com.javaclasses.brainfuck.command;

import com.javaclasses.brainfuck.CommandVisitor;

public class MovePointerRightCommand extends ValueAwareCommand {

    @Override
    public void acceptVisitor(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
