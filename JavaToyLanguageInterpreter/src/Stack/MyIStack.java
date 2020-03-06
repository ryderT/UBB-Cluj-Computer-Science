package Stack;

import java.util.Stack;

public interface MyIStack<TElem> {
    TElem top();
    TElem pop();
    void push(TElem elem);
    String toString();
    boolean isEmpty();
    Stack<?> clone();
}
