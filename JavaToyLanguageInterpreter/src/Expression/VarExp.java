package Expression;

import Dictionary.MyIDictionary;
import Heap.MyIHeap;
import Value.Value;
import Exception.MyException;
public class VarExp implements Exp{

    private String id;

    public VarExp(String vid){
        this.id=vid;
    }


    @Override
    public String toString() {
        return "VarExp("+id+")";
    }

    @Override
    public Exp deepCopy() {
        return new VarExp(id);
    }


    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws MyException {
        return tbl.getVal(id);
    }

}
