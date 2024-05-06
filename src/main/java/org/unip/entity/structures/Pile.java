package org.unip.entity.structures;

import org.unip.solver.State;

import java.util.Stack;

public class Pile implements OpenStates{
    private Stack<State> elements = new Stack<>();

    @Override
    public void push(State estado) {
        this.elements.push(estado);
    }

    @Override
    public State pop() {
        return this.elements.pop();
    }

    @Override
    public int size() {
        return this.elements.size();
    }
}
