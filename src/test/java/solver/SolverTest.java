package solver;

import org.unip.dao.FileInstance;
import org.unip.entity.Board;
import org.unip.entity.structures.Pile;
import org.unip.solver.State;
import org.unip.solver.StateSpaces;

public class SolverTest {
    public static void main(String[] args) {
        FileInstance fileInstance = new FileInstance("C:\\HOKTD\\HokTD\\files\\inst\\instg12.in");
        Board board = fileInstance.getInstance();
        State state = new State(board);
        StateSpaces space = new StateSpaces(state, new Pile());
        fileInstance.writeSolution(space.solve());
        System.out.println(fileInstance.readSolution());
    }
}