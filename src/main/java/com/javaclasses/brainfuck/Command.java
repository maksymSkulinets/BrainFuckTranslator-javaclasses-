package com.javaclasses.brainfuck;

public interface Command {
    void acceptVisitor(CommandVisitor visitor);
}
