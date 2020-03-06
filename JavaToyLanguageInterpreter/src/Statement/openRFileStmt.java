package Statement;

import ProgramState.PrgState;
import Exception.MyException;
import Expression.Exp;
import Value.Value;
import Value.StringValue;
import Type.StringType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class openRFileStmt implements IStmt {

    private Exp e;
    public openRFileStmt(Exp exp){
        this.e=exp;
    }

    @Override
    public String toString() {
        return "openRFile("+e.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value v = e.eval(state.getSymTable(),state.getHeap());
        if(!v.getType().equals(new StringType()))
            throw new MyException("openRFile not String!");
        String val = ((StringValue)v).toString();
        if(state.getFileTable().isDefined(val))
//        if(state.getFileTable().isDefined(val.toString()))
            throw new MyException("File allready exissts!");
        try{
            BufferedReader buff = new BufferedReader(new FileReader(val.toString()));
            state.getFileTable().put(val,buff);
//            state.getFileTable().put(val.toString(),buff);
        }
        catch (IOException er) {
            throw new MyException("Failed to create file!");
        }
        return null;

    }

    @Override
    public IStmt deepCopy() {
        return new openRFileStmt(e.deepCopy());
    }
}
