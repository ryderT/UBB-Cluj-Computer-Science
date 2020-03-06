package Type;

import Value.Value;
import Value.RefValue;


public class RefType implements Type {
    private Type inner;
    public RefType(){this.inner=null;}
    public RefType(Type innerV){this.inner=innerV;}

    public Type getInner() {
        return this.inner;
    }

    public boolean equals(Object another){
        if (another instanceof RefType)
            return inner.equals(((RefType)another).getInner());
        else
            return false;
    }

    @Override
    public String toString() {
        return "Ref("+this.inner.toString()+")";
    }

    @Override
    public Value defaultValue() {
        return new RefValue(inner,0);
    }
}
