package Value;

import Type.Type;


public interface Value {
    Type getType();
    Value deepCopy();
    boolean equals(Object another);
}
