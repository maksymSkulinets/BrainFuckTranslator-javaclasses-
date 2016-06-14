package com.javaclasses.brainfuck;

import com.javaclasses.brainfuck.command.*;

public interface CommandVisitor {

    void visit(IncrementCommand command);

    void visit(DecrementCommand command);

    void visit(MovePointerRightCommand command);

    void visit(MovePointerLeftCommand command);

    void visit(PrintCommand command);

    void visit(LoopCommand command);
}
