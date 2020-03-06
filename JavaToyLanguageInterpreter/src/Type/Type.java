package Type;
import Value.Value;

public interface Type {
    boolean equals(Object another);
    String toString();
    Value defaultValue();
}
