package Heap;
import Dictionary.MyIDictionary;

import java.util.Collection;
import java.util.Map;
public interface MyIHeap<Val> extends MyIDictionary<Integer,Val> {
    Integer getNewAddress();
    void setHeap(Map<Integer,Val> hp);
    Map<Integer,Val> getHeap();

}
