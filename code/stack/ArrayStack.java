public class ArrayStack<E> implements Stack<E> {

	public static final int CAPACITY = 1000; // default array size...why public?
	private int N = -1; // index of top element
	private E[] data;

	//stack constructor 
	public ArrayStack(int capacity) {
		data = (E[]) Object[capacity]; // safe cast
	}

	public ArrayStack() {
		this(CAPACITY);
	}

	public int size() {
		return N+1;
	}

	public boolean isEmpty() {
		return (N == -1);
	}

	public void push(E item) throws IllegalStateException {
		if (size() == data.length) throw new IllegalStateException("Stack is full");
		data[++N] = item;
	}

	public E top() {
		if (isEmpty()) return null;
		return data[N];
	}

	public E pop() {
		if (isEmpty()) return null;
		E top = data[N];
		data[N] = null;
		N--;
		return top;
	}

}