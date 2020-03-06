package Statement;
import ProgramState.PrgState;
import Exception.MyException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    String toString();
    IStmt deepCopy();
}
