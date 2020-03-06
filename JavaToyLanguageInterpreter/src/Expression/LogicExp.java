package Expression;

import Dictionary.MyIDictionary;
import Heap.MyIHeap;
import Type.BoolType;
import Value.Value;
import Exception.MyException;
import Value.BoolValue;


public class LogicExp implements Exp{
    private Exp e1;
    private Exp e2;
    private String op;

    public LogicExp(String opr,Exp ex1,Exp ex2){
        this.op=opr;
        this.e1=ex1;
        this.e2=ex2;
    }

    @Override
    public String toString() { return e1.toString()+op+e2.toString();
    }

    @Override
    public Exp deepCopy() {
        return new LogicExp(op,e1.deepCopy(),e2.deepCopy());
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws MyException {
        if(!op.equals("&&") && !op.equals("||"))
            throw new MyException("Op not && or || !");
        Value v1=e1.eval(tbl,heap);
        if(!v1.getType().equals(new BoolType()))
            throw new MyException("First operand not boolean!");
        Value v2=e2.eval(tbl,heap);
        if(!v2.getType().equals(new BoolType()))
            throw new MyException("Second operand not boolean!");

        BoolValue b1=(BoolValue)v1;
        BoolValue b2=(BoolValue)v2;
        boolean eva;
        if(op.equals("&&"))
            eva=b1.getVal()&&b2.getVal();
        else
            eva=b1.getVal()||b2.getVal();

        return new BoolValue(eva);
    }
}
