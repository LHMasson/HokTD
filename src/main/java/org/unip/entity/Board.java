package org.unip.entity;

import java.util.Objects;

public class Board {
    public String[][] pieces;
    public int matrixScale;
    public Position currentPosition;

    public Board(String[][] pieces, int matrixScale) {
        this.pieces = pieces;
        this.matrixScale = matrixScale;
        this.currentPosition = new Position(0, 0);
    }

    public Board(String[][] pieces, int matrixScale, Position currentPosition) {
        this.pieces = pieces;
        this.matrixScale = matrixScale;
        this.currentPosition = currentPosition;
    }

    public boolean isActionValid(Action action){
        switch (action) {
            case N: if(currentPosition.line == 0 || Objects.equals(pieces[currentPosition.line - 1][currentPosition.column], "T") || Objects.equals(pieces[currentPosition.line - 1][currentPosition.column], "1")){
                return false;
            }
            break;
            case S: if(currentPosition.line == matrixScale - 1 || Objects.equals(pieces[currentPosition.line + 1][currentPosition.column], "T") || Objects.equals(pieces[currentPosition.line + 1][currentPosition.column], "1")){
                return false;
            }
            break;
            case L: if(currentPosition.column == matrixScale - 1 || Objects.equals(pieces[currentPosition.line][currentPosition.column + 1], "T") || Objects.equals(pieces[currentPosition.line][currentPosition.column + 1], "1")){
                return false;
            }
            break;
            case O: if(currentPosition.column == 0 || Objects.equals(pieces[currentPosition.line][currentPosition.column - 1], "T") || Objects.equals(pieces[currentPosition.line][currentPosition.column - 1], "1")){
                return false;
            }
            break;
            default: return true;
        }
        return true;
    }

    public void doAction(Action action){
        Position newPosition = null;
        switch (action){
            case N: newPosition = new Position(currentPosition.line-1, currentPosition.column); break;
            case S: newPosition = new Position(currentPosition.line+1, currentPosition.column); break;
            case L: newPosition = new Position(currentPosition.line, currentPosition.column+1); break;
            case O: newPosition = new Position(currentPosition.line, currentPosition.column-1); break;
            default:
                break;
        }
        this.pieces[currentPosition.line][currentPosition.column] = "1";
        this.pieces[newPosition.line][newPosition.column] = "1";
        currentPosition = newPosition;
    }

    public Board deepCopy(){
        String[][] newBoard = new String[this.matrixScale][this.matrixScale];
        for (int i = 0; i < this.matrixScale; i++){
            System.arraycopy(this.pieces[i], 0, newBoard[i], 0, this.matrixScale);
        }
        return new Board(newBoard, this.matrixScale, this.currentPosition);
    }

}
