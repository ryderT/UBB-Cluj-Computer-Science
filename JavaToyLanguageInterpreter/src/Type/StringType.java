package Type;

import Value.Value;
import Value.StringValue;

public class StringType implements Type {
    @Override
    public boolean equals(Object another) {
        return (another instanceof StringType);
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }
}
