package org.unip.solver;

import org.unip.entity.structures.OpenStates;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class StateSpaces {
    State initialState;
    OpenStates openStates;
    Set<State> closedStates;

    public StateSpaces(State initialState, OpenStates openStates) {
        this.initialState = initialState;
        this.openStates = openStates;
        openStates.push(this.initialState);
        closedStates = new HashSet<>();
    }

    public State solve(){

        while(openStates.size()>0) {
            State lastTestedState = openStates.pop();
            if(lastTestedState.isGoal()) {
                System.out.println("-------------ENCONTROU SOLUÇÃO----------");
                return lastTestedState;
            }
            System.out.println(lastTestedState.toString());
            closedStates.add(lastTestedState);
            Collection<State> generatedStates = lastTestedState.generateStates();
            for(State generated: generatedStates) {
                if(!closedStates.contains(generated))
                    openStates.push(generated);
            }
        }
        System.out.println("NÃO ENCONTROU SOLUÇÃO");

        return null;
    }
}
