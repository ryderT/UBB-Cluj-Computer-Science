package Statement;

import Dictionary.MyIDictionary;
import List.MyIList;
import ProgramState.PrgState;
import Exception.MyException;
import Expression.Exp;
import Value.Value;

public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp e){
        exp = e;
    }

    @Override
    public String toString() {
        return "print("+exp.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String,Value> symTbl=state.getSymTable();
        MyIList<Value> out=state.getOut();
        Value v=exp.eval(symTbl,state.getHeap());
        out.add(v);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }
}
