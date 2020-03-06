package Type;

import Value.Value;
import Value.IntValue;

public class IntType implements Type{
    @Override
    public boolean equals(Object another) {
        return (another instanceof IntType);
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }
}
