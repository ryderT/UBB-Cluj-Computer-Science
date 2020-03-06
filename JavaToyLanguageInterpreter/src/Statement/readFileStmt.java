package Statement;

import ProgramState.PrgState;
import Exception.MyException;
import Expression.Exp;
import Value.Value;
import Value.StringValue;
import Type.StringType;
import java.io.BufferedReader;
import java.io.IOException;
import Type.IntType;
import Value.IntValue;

public class readFileStmt implements IStmt{
    private String name;
    private Exp e;
    public readFileStmt(String zaname,Exp exp){
        this.name=zaname;
        this.e=exp;
    }

    @Override
    public String toString() {
        return "readFileStmt("+name+","+e.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(state.getSymTable().getVal(name)==null)
            throw new MyException("Variable not defined!");
        if(!state.getSymTable().getVal(name).getType().equals(new IntType()))
            throw new MyException("Type not int!");
        Value exprVal =e.eval(state.getSymTable(),state.getHeap());
        if(!exprVal.getType().equals(new StringType()))
            throw new MyException("Expression has to be of Type String");
        String tableKey = ((StringValue)exprVal).toString();
        //.toString si era String
        BufferedReader file = state.getFileTable().getVal(tableKey);
        if(file==null)
            throw new MyException("File "+tableKey+" not found in the FileTable");
        try {
            String line = file.readLine();
            if(line != null){
                state.getSymTable().replace(name,new IntValue(Integer.parseInt(line)));
            }
            else
                state.getSymTable().replace(name,new IntType().defaultValue());

        }
        catch (IOException er){
            throw new MyException("Failed to read from file!");
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new readFileStmt(name,e.deepCopy());
    }
}
