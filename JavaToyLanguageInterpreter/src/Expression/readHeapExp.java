package Expression;

import Dictionary.MyIDictionary;
import Heap.MyIHeap;
import Value.Value;
import Exception.MyException;
import Value.RefValue;

public class readHeapExp implements Exp {
    private Exp exp;
    public readHeapExp(Exp expression){this.exp=expression;}

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws MyException {
        Value v=exp.eval(tbl,heap);
        if(!(v instanceof RefValue))
            throw new MyException("Expression result not RefValue!");

        Integer address=((RefValue)v).getAddress();
        if(!heap.isDefined(address))
            throw new MyException("Address is empty!");

        return heap.getVal(address);
    }

    @Override
    public String toString() {
        return "rH("+exp.toString()+")";
    }

    @Override
    public Exp deepCopy() {
        return new readHeapExp(exp.deepCopy());
    }
}
