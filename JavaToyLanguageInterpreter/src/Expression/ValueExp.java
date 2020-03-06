package Expression;

import Dictionary.MyIDictionary;
import Heap.MyIHeap;
import Value.Value;
import Exception.MyException;

public class ValueExp implements Exp{

    private Value e;
    public ValueExp(Value val){
        this.e=val;
    }


    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws MyException {
        return this.e;
    }

    @Override
    public String toString() {
        return this.e.toString();
    }

    @Override
    public Exp deepCopy() {
        return new ValueExp(e.deepCopy());
    }
}
