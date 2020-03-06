package Value;

import Type.Type;
import Type.IntType;
import Value.Value;

public class IntValue implements Value{

    private int val;

    public IntValue(int value) {
        this.val=value;
    }
    public int getVal(){
        return val;
    }
    @Override
    public String toString()
    {
        return Integer.toString(val);
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public Value deepCopy() {
        return new IntValue(val);
    }

    @Override
    public boolean equals(Object another) {
        return (another instanceof IntValue);
    }
}
