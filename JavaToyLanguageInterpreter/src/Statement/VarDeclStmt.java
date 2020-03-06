package Statement;

import ProgramState.PrgState;
import Exception.MyException;
import Type.Type;
import Value.Value;
import Dictionary.MyIDictionary;
import Type.IntType;
import Type.BoolType;
import Value.IntValue;
import Value.BoolValue;
import Value.StringValue;
import Type.StringType;
import Type.RefType;
import Value.RefValue;
public class VarDeclStmt implements IStmt {
    private String name;
    private Type type;

    public VarDeclStmt(String n,Type t){
        this.name=n;
        this.type=t;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(symTbl.isDefined(this.name))
            throw new MyException("Variable name already used!");
        if(type instanceof IntType)
            symTbl.put(name,new IntType().defaultValue());
        else
            if(type instanceof BoolType)
                symTbl.put(name,new BoolType().defaultValue());
            else
                if (type instanceof StringType)
                    symTbl.put(name,new StringType().defaultValue());
                else
                    symTbl.put(name,new RefType(((RefType)type).getInner()).defaultValue());
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name,type);
    }

    @Override
    public String toString() {
        return this.name+" "+this.type.toString();
    }
}
