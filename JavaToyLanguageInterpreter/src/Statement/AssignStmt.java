package Statement;

import ProgramState.PrgState;
import Exception.MyException;
import Expression.Exp;
import Stack.MyIStack;
import Value.Value;
import Dictionary.MyIDictionary;
import Type.Type;

public class AssignStmt implements IStmt{
    private String id;
    private Exp exp;

    public AssignStmt(String aid,Exp ex){
        this.id=aid;
        this.exp=ex;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        Value val = exp.eval(symTbl,state.getHeap());
        if (symTbl.isDefined(id)) {
            Type typId = (symTbl.getVal(id)).getType(); //getValue / lookup
            if ((val.getType()).equals(typId))
                symTbl.replace(id, val);
            else
                throw new MyException("declared type of variable " + id + " and type of the assigned expression do not match");
        }
        else throw new MyException("the used variable " + id + " was not declared before");

        return null;
    }




    @Override
    public IStmt deepCopy() {
        return new AssignStmt(id, exp.deepCopy());
    }

    @Override
    public String toString() {
        return id+"="+ exp.toString();
    }
}
