package org.unip.dao;

import org.unip.entity.Board;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class InputInstance {
    public static Board getInstance(String file) {
        Board board = null;
        String line;

        try {
            var is = new FileInputStream(file);
            var isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            var br = new BufferedReader(isr);

            int matrixScale = Integer.parseInt(br.readLine());

            String[][] pieces = new String[matrixScale][matrixScale];

            for(int i = 0; i < matrixScale; i++) {
                line = br.readLine();
                String[] values = line.split("");
                System.arraycopy(values, 0, pieces[i], 0, matrixScale);
            }
            board = new Board(pieces, matrixScale);
        }catch(IOException e) {
            e.printStackTrace();
        }

        return board;
    }
}
