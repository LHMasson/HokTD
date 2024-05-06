package org.unip.entity;

public class Position {
    int line;
    int column;
    public Position(int line, int column) {
        super();
        this.line = line;
        this.column = column;
    }
    @Override
    public String toString() {
        return "(" + line + ", " + line + ")";
    }
}
