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
        State bestSolution;
        Collection<State> generatedStates = null;
        while(openStates.size()>0) {
            State lastTestedState = openStates.pop();
            if(lastTestedState.isGoal()) {
                solvedStates.add(lastTestedState);
            } else {
                generatedStates = lastTestedState.generateStates();
            }
            closedStates.add(lastTestedState);
            for(State generated: generatedStates) {
                if(!closedStates.contains(generated)){
                    openStates.push(generated);
                }
            }
        }
        if(this.solvedStates != null){
            bestSolution = this.solvedStates.iterator().next();
            for(State solved: this.solvedStates) {
               if (solved.board.sufferedPoints >= bestSolution.board.sufferedPoints){
                   bestSolution = solved.deepCopy();
               }
            }
            return bestSolution;
        }
        System.out.println("NÃO ENCONTROU SOLUÇÃO");
        return null;
    }
}
