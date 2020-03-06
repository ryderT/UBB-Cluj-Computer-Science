package Dictionary;


import java.util.Collection;
import java.util.HashMap;
import Exception.MyException;
import java.util.Map;
import java.util.Set;


public class MyDictionary<Key,Val> implements MyIDictionary<Key,Val>{

    private HashMap<Key, Val> dict;

    public MyDictionary(){
        this.dict=new HashMap<Key,Val>();
    }


    @Override
    public void put(Key k, Val v) {
        dict.put(k,v);
    }

    @Override
    public boolean isDefined(Key k) {
        return this.dict.containsKey(k);
    }

    @Override
    public Val getVal(Key k) {
        if (!this.isDefined(k))
            throw new MyException("It does not exist!");
        else
            return this.dict.get(k);
    }

    @Override
    public void removeVal(Key k) {
        this.dict.remove(k);
    }

    @Override
    public String toString() {
        return this.dict.toString();
    }

    @Override
    public void replace(Key k, Val newVal) {
        this.dict.replace(k,newVal);
    }

    @Override
    public MyIDictionary<Key, Val> clone() {
        MyIDictionary<Key,Val> cloned = new MyDictionary<Key,Val>();
        HashMap<Key,Val> hm=new HashMap<>();
        for(Key k:dict.keySet())
            hm.put(k,dict.get(k));
        cloned.setDict(hm);
        return cloned;
    }

    @Override
    public Map<Key,Val> getDict(){return this.dict;}

    @Override
    public Set<Key> keySet() {
        return this.dict.keySet();
    }

    @Override
    public Collection<Val> getValues() {
        return this.dict.values();
    }

    @Override
    public void setDict(HashMap<Key, Val> hm) {
        this.dict=hm;
    }
}
