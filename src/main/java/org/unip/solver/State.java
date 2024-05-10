package org.unip.solver;

import org.unip.entity.Action;
import org.unip.entity.Board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class State {
    public Board board;
    public List<Action> actions;

    public State(Board board){
        this.board = board;
        this.actions = new LinkedList<>();
    }

    public State(Board board, List<Action> actions) {
        this(board);
        this.actions = actions;
    }

    protected State initialize(){
        this.board.initialize();
        return this;
    }
    public boolean isGoal(){
        return Integer.parseInt(board.pieces[board.matrixScale-1][board.matrixScale-1]) == 1;
    }

    public void doAction(Action action){
        this.actions.add(action);
        this.board.doAction(action);
    }

    public Collection<State> generateStates(){
        Collection<State> generatedStates = new ArrayList<>();
        for(Action action: Action.values()){
            if(this.board.isActionValid(action)) {
                State generated = this.deepCopy();
                generated.doAction(action);
                generatedStates.add(generated);
            }
        }

        return generatedStates;
    }

    public State deepCopy(){
        List<Action> newActions = new LinkedList<>(this.actions);
        return new State(this.board.deepCopy(), newActions);
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\n");
        result.append("Nível: ").append(this.actions.size()).append("\n");
        result.append("Dano: ").append(this.board.sufferedPoints).append("\n");
        for(int i=0; i < this.board.matrixScale; i++) {
            result.append("[");
            for(int j=0; j < this.board.matrixScale; j++){
                result.append(this.board.pieces[i][j]);
            }
            result.append("]\n");
        }

        for(Action action: this.actions) {
            result.append(action);
        }
        return result.toString();
    }
}
