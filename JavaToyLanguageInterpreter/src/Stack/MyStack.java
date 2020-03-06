package Stack;
import java.util.Stack;
public class MyStack<TElem> implements MyIStack<TElem> {

    private Stack<TElem> stack;

    public MyStack(){
        this.stack=new Stack<TElem>();
    }

    @Override
    public TElem top() {
        return this.stack.peek();
    }

    @Override
    public TElem pop() {
        return this.stack.pop();
    }

    @Override
    public void push(TElem elem) {
        this.stack.push(elem);
    }

    @Override
    public String toString() {
        return this.stack.toString();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public Stack<?> clone() {
        return (Stack<?>)this.stack.clone();
    }
}
