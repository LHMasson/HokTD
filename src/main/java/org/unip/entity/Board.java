package org.unip.entity;

import java.util.Objects;

public class Board {
    public String[][] pieces;
    public int matrixScale;
    public Position currentPosition;
    public int sufferedPoints = 0;

    public Board(String[][] pieces, int matrixScale) {
        this.pieces = pieces;
        this.matrixScale = matrixScale;
        this.currentPosition = new Position(0, 0);
    }

    public Board(String[][] pieces, int matrixScale, Position currentPosition, int sufferedPoints) {
        this.pieces = pieces;
        this.matrixScale = matrixScale;
        this.currentPosition = currentPosition;
        this.sufferedPoints = sufferedPoints;
    }

    public void initialize(){
        this.pieces[currentPosition.line][currentPosition.column] = "1";
    }

    public boolean isActionValid(Action action){
        switch (action) {
            case N:
                if(currentPosition.line == 0 || Objects.equals(pieces[currentPosition.line - 1][currentPosition.column], "T") || Objects.equals(pieces[currentPosition.line - 1][currentPosition.column], "1")){
                    return false;
                }
            break;
            case S:
                if(currentPosition.line == matrixScale - 1 || Objects.equals(pieces[currentPosition.line + 1][currentPosition.column], "T") || Objects.equals(pieces[currentPosition.line + 1][currentPosition.column], "1")){
                    return false;
                }
            break;
            case L:
                if(currentPosition.column == matrixScale - 1 || Objects.equals(pieces[currentPosition.line][currentPosition.column + 1], "T") || Objects.equals(pieces[currentPosition.line][currentPosition.column + 1], "1")){
                    return false;
                }
            break;
            case O:
                if(currentPosition.column == 0 || Objects.equals(pieces[currentPosition.line][currentPosition.column - 1], "T") || Objects.equals(pieces[currentPosition.line][currentPosition.column - 1], "1")){
                    return false;
                }
            break;
            default: return true;
        }
        return true;
    }

    public void countSufferedPoints(Position newPosition){
        for (int i = newPosition.line - 1; i <= newPosition.line + 1; i++) {
            for (int j = newPosition.column - 1; j <= newPosition.column + 1; j++) {
                if (i >= 0 && i < pieces.length && j >= 0 && j < pieces[0].length) {
                    if (pieces[i][j].equals("T")) {
                        this.sufferedPoints -= 10;
                    }
                }
            }
        }

    }

    public void doAction(Action action){
        Position newPosition = null;
        switch (action){
            case N:
                newPosition = new Position(currentPosition.line-1, currentPosition.column);
                this.countSufferedPoints(newPosition);
                break;
            case L:
                newPosition = new Position(currentPosition.line, currentPosition.column+1);
                this.countSufferedPoints(newPosition);
                break;
            case S:
                newPosition = new Position(currentPosition.line+1, currentPosition.column);
                this.countSufferedPoints(newPosition);
                break;
            case O:
                newPosition = new Position(currentPosition.line, currentPosition.column-1);
                this.countSufferedPoints(newPosition);
                break;
            default:
                break;
        }
        this.pieces[newPosition.line][newPosition.column] = "1";
        currentPosition = newPosition;
    }

    public Board deepCopy(){
        String[][] newBoard = new String[this.matrixScale][this.matrixScale];
        for (int i = 0; i < this.matrixScale; i++){
            System.arraycopy(this.pieces[i], 0, newBoard[i], 0, this.matrixScale);
        }
        return new Board(newBoard, this.matrixScale, this.currentPosition, this.sufferedPoints);
    }

}
