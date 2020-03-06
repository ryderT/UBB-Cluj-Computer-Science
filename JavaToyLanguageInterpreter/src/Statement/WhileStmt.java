package Statement;

import ProgramState.PrgState;
import Exception.MyException;
import Expression.Exp;
import Stack.MyIStack;
import Type.BoolType;
import Value.BoolValue;
import Stack.*;


public class WhileStmt implements IStmt {
    private Exp condition;
    private IStmt body;
    public WhileStmt(Exp whileCondition,IStmt whileBody){
        this.condition=whileCondition;
        this.body=whileBody;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(!(condition.eval(state.getSymTable(),state.getHeap()).getType().equals(new BoolType())))
            throw new MyException("Evaluation of condition not Boolean!");

        boolean b=((BoolValue)condition.eval(state.getSymTable(),state.getHeap())).getVal();
        MyIStack<IStmt> stack;
        if(b){
            stack=state.getExeStack();
            stack.push(this);
            state.setExeStack((MyStack<IStmt>)stack);
            body.execute(state);
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(this.condition.deepCopy(),this.body.deepCopy());
    }

    @Override
    public String toString() {
        return "while("+this.condition.toString()+"){"+this.body.toString()+"}";
    }
}
