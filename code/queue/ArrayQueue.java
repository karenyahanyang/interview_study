public class ArrayQueue<E> implements Queue<E> {

	public static final int CAPACITY = 1000;

	private E[] data;
	private int index = 0; // index of head element
	private int size = 0; // current number of elements

	public ArrayQueue(int capacity) {
		data = (E[]) Object[capacity];
	}
	public ArrayQueue() {
		this(CAPACITY);
	}

	public boolean() isEmpty{
		return (size == 0);
	}

	public int size() {
		return size;
	}

	// add a new element to the back of the queue
	public void enqueue(e) throws IllegalStateException {
		
		if (size == data.length) throws IllegalStateException("Queue is full");
		int avail = (index + size) % data.length; // if index= 8, size=3, cap =10, (8+3)%10 = 1
		data[avail] = e;
		size++;

	}

	public E dequeue() {
		if (isEmpty()) return null;
		E head = data[index];
		data[index] = null;
		index = (index + 1) % data.length; // index becomes next number
		size--;
		return head;
	}

	public E first() {
		if (isEmpty()) return null;
		return data[index];
	}

}