package Value;

import Type.Type;
import Type.BoolType;
import Value.Value;

public class BoolValue implements Value {

    private boolean val;
    public BoolValue(boolean value) {
        this.val=value;
    }
    public boolean getVal(){
        return val;
    }
    @Override
    public String toString()
    {
        return Boolean.toString(val);
    }
    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(val);
    }

    @Override
    public boolean equals(Object another) {
        return (another instanceof BoolValue);
    }
}
