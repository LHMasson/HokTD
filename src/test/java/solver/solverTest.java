package solver;

import org.unip.dao.InputInstance;
import org.unip.entity.Board;
import org.unip.entity.structures.Pile;
import org.unip.solver.State;
import org.unip.solver.StateSpaces;
public class solverTest {
    public static void main(String[] args) {
        Board board = InputInstance.getInstance("src/test/java/files/inst03.in");
        State initialState = new State(board);
        StateSpaces ee = new StateSpaces(initialState, new Pile());
        State solucao = ee.solve();
        System.out.println(solucao.toString());
    }
}
