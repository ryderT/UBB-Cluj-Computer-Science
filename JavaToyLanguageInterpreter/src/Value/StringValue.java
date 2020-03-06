package Value;

import Type.Type;
import Type.StringType;
import Value.Value;

public class StringValue implements Value {

    private String val;

    public StringValue(String str){
        this.val=str;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(val);
    }

    @Override
    public String toString() {
        return this.val;
    }

    @Override
    public boolean equals(Object another) {
        return (another instanceof StringValue);
    }
}
