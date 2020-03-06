package Statement;

import ProgramState.PrgState;
import Exception.MyException;


public class NopStmt implements IStmt {
    //no operation Statement!!!
    public NopStmt(){};

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
