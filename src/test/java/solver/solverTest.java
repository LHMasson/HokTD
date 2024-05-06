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
            StateSpaces stateSpaces = new StateSpaces(initialState, new Pile());
        State solution = stateSpaces.solve();
        System.out.println(solution.toString());
    }
}
