package Heap;

import Dictionary.MyIDictionary;

import java.util.*;

public class MyHeap<Val> implements MyIHeap<Val> {
    private HashMap<Integer,Val> heap;
    public MyHeap(){this.heap=new HashMap<Integer,Val>();}

    @Override
    public Collection<Val> getValues() {
        return heap.values();
    }

    @Override
    public void setDict(HashMap<Integer, Val> hm) {

    }

    @Override
    public Map<Integer, Val> getDict() {
        return (Map<Integer,Val>)heap;
    }

    @Override
    public Set<Integer> keySet() {
        return this.heap.keySet();
    }

    @Override
    public Integer getNewAddress() {
        while(true){
            int nr= new Random().nextInt(10000000);
            if(!this.isDefined(nr))
                return nr;
        }
    }
    @Override
    public void setHeap(Map<Integer, Val> hp) {
        this.heap=(HashMap<Integer,Val>)hp;
    }

    @Override
    public Map<Integer, Val> getHeap() {
        return (Map<Integer,Val>)this.heap;
    }

    @Override
    public void put(Integer k, Val v) {
        this.heap.put(k,v);
    }

    @Override
    public boolean isDefined(Integer k) {
        return this.heap.containsKey(k);
    }

    @Override
    public Val getVal(Integer k) {
        return this.heap.get(k);
    }

    @Override
    public void removeVal(Integer k) {
        this.heap.remove(k);
    }

    @Override
    public void replace(Integer k, Val newVal) {
        this.heap.replace(k,newVal);
    }

    @Override
    public MyIDictionary<Integer, Val> clone() {
        return null;
    }

    @Override
    public String toString() {
        return this.heap.toString();
    }
}
