import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.lang.IllegalStateException;

public class TqsStack<T> {

	private final ArrayList<T> stack;
    private int max_size = -1;

    public TqsStack(){
        this.stack = new ArrayList<T>();
    }

    public TqsStack(int max_size) {
        this.stack = new ArrayList<T>();
        if (max_size >= 0 ) {
            this.max_size = max_size;
        }
    }

    public boolean isEmpty() {
        if (this.stack.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public int size() {
        return this.stack.size();
    }

    public void push(T elemento) {
        if (this.max_size >= 0 && this.max_size <= this.stack.size()) {
            throw new IllegalStateException();
        } else {
            stack.add(elemento);
        }
    }

    public T pop() {
        if (stack.size() == 0) {
            throw new NoSuchElementException();
        } else {
            return stack.remove(stack.size()-1);
        }
    }

    public T peek() {
        if (stack.size() == 0) {
            throw new NoSuchElementException();
        } else {
            return stack.get(stack.size()-1);
        }
    }

}