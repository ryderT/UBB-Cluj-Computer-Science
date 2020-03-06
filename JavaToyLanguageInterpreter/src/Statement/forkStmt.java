package Statement;

import ProgramState.PrgState;
import Exception.MyException;
import Dictionary.MyIDictionary;
import Stack.MyStack;
import Value.Value;


public class forkStmt implements IStmt {

    private IStmt stmt;
    public forkStmt(IStmt statement){
        this.stmt=statement;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String,Value>symTbl=state.getSymTable().clone();
        return new PrgState(new MyStack<IStmt>(),symTbl,state.getOut(),stmt,state.getFileTable(),state.getHeap());
    }

    @Override
    public IStmt deepCopy() {
        return new forkStmt(stmt.deepCopy());
    }

    @Override
    public String toString() {
        return "fork("+stmt.toString()+")";
    }
}
