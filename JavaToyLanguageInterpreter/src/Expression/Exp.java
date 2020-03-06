package Expression;

import Heap.MyIHeap;
import Value.Value;
import Dictionary.*;
import Exception.MyException;

public interface Exp {

    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Value> heap) throws MyException;
    String toString();
    Exp deepCopy();


}
