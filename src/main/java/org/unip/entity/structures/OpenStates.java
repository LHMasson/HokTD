package org.unip.entity.structures;

import org.unip.solver.State;

public interface OpenStates {
    void push(State state);
    State pop();
    int size();
}
