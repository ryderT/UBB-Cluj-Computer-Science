package Statement;

import ProgramState.PrgState;
import Exception.MyException;
import Stack.MyIStack;

public class CompStmt implements IStmt{
    private IStmt first;
    private IStmt snd;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.snd = second;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getExeStack();
        stk.push(snd);
        stk.push(first);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(),snd.deepCopy());
    }

    @Override
    public String toString() {
        return "("+first.toString() + ";" + snd.toString()+")";
    }
}
