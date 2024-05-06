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
    Set<State> solvedStates;

    public StateSpaces(State initialState, OpenStates openStates) {
        this.initialState = initialState.initialize();
        this.openStates = openStates;
        openStates.push(this.initialState);
        closedStates = new HashSet<>();
        solvedStates = new HashSet<>();
    }

    public State solve(){
        State bestSolution = null;
        Collection<State> generatedStates = null;
        while(openStates.size()>0) {
            State lastTestedState = openStates.pop();
            System.out.println(lastTestedState.toString());
            if(lastTestedState.isGoal()) {
                if(lastTestedState.board.sufferedPoints == 0) {
                    return lastTestedState;
                } else if (bestSolution == null) {
                    bestSolution = lastTestedState.deepCopy();
                } else {
                    if(lastTestedState.board.sufferedPoints > bestSolution.board.sufferedPoints) {
                        bestSolution = lastTestedState.deepCopy();
                    }
                }
            } else {
                if(bestSolution != null && lastTestedState.board.sufferedPoints <= bestSolution.board.sufferedPoints){
                    closedStates.add(lastTestedState);
                } else {
                    generatedStates = lastTestedState.generateStates();
                }
            }
            closedStates.add(lastTestedState);
            for(State generated: generatedStates) {
                if(!closedStates.contains(generated)){
                    openStates.push(generated);
                }
            }
        }
        System.out.println("NÃO ENCONTROU SOLUÇÃO");
        return null;
    }
}
