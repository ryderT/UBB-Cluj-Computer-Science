package Statement;

import ProgramState.PrgState;
import Exception.MyException;
import Expression.Exp;
import Type.RefType;
import Value.RefValue;
import Value.Value;
import Type.Type;

public class writeHeapStmt implements IStmt{
    private String name;
    private Exp exp;
    public writeHeapStmt(String varName,Exp expr){
        this.name=varName;
        this.exp=expr;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(!state.getSymTable().isDefined(name))
            throw new MyException("Variable not defined!");
        if(!(state.getSymTable().getVal(name) instanceof RefValue))
            throw new MyException("Variable is not RefValue!");

        Integer address=((RefValue)state.getSymTable().getVal(name)).getAddress();
        if(!state.getHeap().isDefined(address))
            throw new MyException("Address not in heap!");

        Value v=this.exp.eval(state.getSymTable(),state.getHeap());
        Type t=((RefValue)state.getSymTable().getVal(name)).getLocationType();
        if(!v.getType().equals(t))
            throw new MyException("Types do not match!");

        state.getHeap().replace(address,v);
        return null;
    }

    @Override
    public String toString() {
        return "wH("+name+","+exp.toString()+")";
    }

    @Override
    public IStmt deepCopy() {
        return new writeHeapStmt(name,exp.deepCopy());
    }
}
