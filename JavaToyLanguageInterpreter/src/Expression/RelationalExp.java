package Expression;

import Exception.MyException;
import Dictionary.MyIDictionary;
import Heap.MyIHeap;
import Type.IntType;
import Value.Value;
import Value.IntValue;
import Value.BoolValue;
public class RelationalExp implements Exp {

    private Exp e1;
    private Exp e2;
    private String op;

    public RelationalExp(String ope,Exp ex1,Exp ex2){
        this.op=ope;
        this.e1=ex1;
        this.e2=ex2;
    }


    @Override
    public String toString() {
        return e1.toString()+op+e2.toString();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws MyException {
        if(!op.equals("<") && !op.equals("<=") &&!op.equals(">") &&!op.equals(">=") &&!op.equals("==") &&!op.equals("!="))
            throw new MyException("Operator not relational!");
        Value v1=e1.eval(tbl,heap);
        if(!v1.getType().equals(new IntType()))
            throw new MyException("Operand1 not int!");
        Value v2=e2.eval(tbl,heap);
        if(!v2.getType().equals(new IntType()))
            throw new MyException("Operand2 not int!");

        IntValue i1=(IntValue)v1;
        IntValue i2=(IntValue)v2;

        switch (op){
            case ">": return new BoolValue(i1.getVal()>i2.getVal());
            case ">=": return new BoolValue(i1.getVal()>=i2.getVal());
            case "<": return new BoolValue(i1.getVal()<i2.getVal());
            case "<=": return new BoolValue(i1.getVal()<=i2.getVal());
            case "==": return new BoolValue(i1.getVal()==i2.getVal());
            case "!=": return new BoolValue(i1.getVal()!=i2.getVal());
        }
        return null;
    }

    @Override
    public Exp deepCopy() {
        return new RelationalExp(op,e1.deepCopy(),e2.deepCopy());
    }
}
