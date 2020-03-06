package Expression;

import Dictionary.MyIDictionary;
import Heap.MyIHeap;
import Value.Value;
import Value.IntValue;
import Type.IntType;
import Exception.MyException;

public class ArithExp implements Exp {

    private Exp e1;
    private Exp e2;
    private int op; //1-plus, 2-minus, 3-star, 4-divide
    public ArithExp(int operand, Exp exp1, Exp exp2){
        this.op=operand;
        this.e1 = exp1;
        this.e2 = exp2;
    }

    @Override
    public String toString() {
        char n = '0';
        if (op==1)
            n='+';
        else if(op==2)
            n='-';
        else if(op==3)
            n='*';
        else if(op==4)
            n='/';
        return e1.toString() + n + e2.toString();
    }

    @Override
    public Exp deepCopy() {
        return new ArithExp(op,e1.deepCopy(),e2.deepCopy());
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws MyException{
        if(op<1 || op>4)
            throw new MyException("Not existing operand");
        Value v1,v2;
        v1= e1.eval(tbl,heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl,heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();
                if (op==1) return new IntValue(n1+n2);
                if (op ==2) return new IntValue(n1-n2);
                if(op==3) return new IntValue(n1*n2);
                if(op==4)
                    if(n2==0) throw new MyException("division by zero");
                    else return new IntValue(n1/n2);
            }else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
        return null;
    }
}
