package org.unip.dao;

import org.unip.entity.Action;
import org.unip.entity.Board;
import org.unip.solver.State;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileInstance {
    public String fileName;
    public FileInstance(String filename){
        this.fileName = filename;
    }
    public Board getInstance() {
        Board board = null;
        String line;

        try {
            var is = new FileInputStream(this.fileName);
            var isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            var br = new BufferedReader(isr);

            int matrixScale = Integer.parseInt(br.readLine());

            String[][] pieces = new String[matrixScale][matrixScale];

            for (int i = 0; i < matrixScale; i++) {
                line = br.readLine();
                String[] values = line.split("");
                System.arraycopy(values, 0, pieces[i], 0, matrixScale);
            }
            board = new Board(pieces, matrixScale);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return board;
    }

    public static String getSolutionFileName(String instanceFileName) {
        String solutionFileName = instanceFileName.replace("inst", "sol").replace(".in", ".out");
        return solutionFileName;
    }

    public void writeSolution(State state) {
        try {
            String fileName = getSolutionFileName(this.fileName);
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Action action: state.actions) {
                bufferedWriter.write(action.toString());
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readSolution(){
        try{
            var is = new FileInputStream(getSolutionFileName(this.fileName));
            var isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            var br = new BufferedReader(isr);
            return br.readLine();
        }
        catch (Exception err){
            System.out.println(err.toString());
        }
        return null;
    }
}
