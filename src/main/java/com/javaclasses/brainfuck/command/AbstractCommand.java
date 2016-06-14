package com.javaclasses.brainfuck.command;


import com.javaclasses.brainfuck.Command;

abstract public class AbstractCommand implements Command {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        return true;
    }

}
