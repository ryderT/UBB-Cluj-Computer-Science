package Dictionary;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary<Key, Val> {
    void put(Key k, Val v);
    boolean isDefined(Key k);
    Val getVal(Key k);
    void removeVal(Key k);
    String toString();
    void replace(Key k,Val newVal);
    MyIDictionary<Key,Val> clone();
    Collection<Val> getValues();
    void setDict(HashMap<Key,Val> hm);
    public Map<Key,Val> getDict();
    Set<Key> keySet();
}
