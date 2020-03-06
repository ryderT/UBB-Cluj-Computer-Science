package Statement;

import ProgramState.PrgState;
import Exception.MyException;
import Expression.Exp;
import Value.Value;
import Type.RefType;
import Type.Type;
import Value.RefValue;
public class NewStmt implements IStmt {
    private String name;
    private Exp exp;
    public NewStmt(String varName,Exp expression){
        this.name=varName;
        this.exp=expression;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(!state.getSymTable().isDefined(name))
            throw new MyException("Variable not found!");
        if(!(state.getSymTable().getVal(name).getType() instanceof RefType))
            throw new MyException("Variable not Reference type!");
        Value v=exp.eval(state.getSymTable(),state.getHeap());
        RefValue r=((RefValue)state.getSymTable().getVal(name));
        Type t=((RefType)r.getType()).getInner();
        if(!v.getType().equals(t))
            throw new MyException("Type of expression not ref type!");

        Integer address=state.getHeap().getNewAddress();
        state.getHeap().put(address,v);
        state.getSymTable().replace(name,new RefValue(t,address));

        return null;
    }

    @Override
    public String toString() {
        return "new("+name+","+exp.toString()+")";
    }

    @Override
    public IStmt deepCopy() {
        return new NewStmt(name,exp.deepCopy());
    }
}
