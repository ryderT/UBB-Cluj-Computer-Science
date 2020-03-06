package Statement;

import ProgramState.PrgState;
import Exception.MyException;
import Expression.Exp;
import Value.Value;
import Value.StringValue;
import Type.StringType;
import java.io.BufferedReader;
import java.io.IOException;
import Dictionary.MyIDictionary;



public class closeRFileStmt implements IStmt{

    private Exp e;

    public closeRFileStmt(Exp exp){
        this.e=exp;
    }

    @Override
    public String toString() {
        return "CloseRFile("+e.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value v = e.eval(state.getSymTable(),state.getHeap());
        if(!v.getType().equals(new StringType()))
            throw new MyException("CloseRFile not string!");
        String fileKey = ((StringValue)v).toString();
//        String fileKey = ((StringValue)v).toString();
        BufferedReader file = state.getFileTable().getVal(fileKey);
        if(file==null)
            throw new MyException("File not found!");
        try{
            file.close();
            state.getFileTable().removeVal(fileKey);
        }
        catch (IOException er) {
            throw new MyException("Failed to close file!");
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new closeRFileStmt(e.deepCopy());
    }
}
