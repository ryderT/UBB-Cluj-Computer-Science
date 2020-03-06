package Statement;

import ProgramState.PrgState;
import Exception.MyException;
import Expression.Exp;
import Stack.MyIStack;
import Dictionary.MyIDictionary;
import Type.BoolType;
import Value.Value;
import Value.BoolValue;

public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(Exp e, IStmt i1, IStmt i2){
        this.exp=e;
        this.thenS=i1;
        this.elseS=i2;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        Value v = exp.eval(symTbl,state.getHeap());
        MyIStack<IStmt> stack = state.getExeStack();
        if (!v.getType().equals(new BoolType()))
            throw new MyException("expression not boolean");
        BoolValue b=(BoolValue) v;
        if(b.getVal())
            stack.push(thenS);
        else
            stack.push(elseS);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(),thenS.deepCopy(),elseS.deepCopy());
    }

    @Override
    public String toString() {
        return "(IF("+ exp.toString()+") THEN(" +thenS.toString() +")ELSE("+elseS.toString()+"))";
    }
}
