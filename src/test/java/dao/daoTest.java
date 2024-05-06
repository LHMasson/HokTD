package dao;

import org.unip.dao.InputInstance;
import org.unip.entity.Board;

public class daoTest {
    public static void main(String[] args) {
        Board b = InputInstance.getInstance("src/test/java/files/inst05.in");

        for (int i = 0; i < b.matrixScale; i++) {
            for (int j = 0; j < b.pieces[i].length; j++) {
                System.out.print(b.pieces[i][j] + " ");
            }
            System.out.println();
        }
    }
}
