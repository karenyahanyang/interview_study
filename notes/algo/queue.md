# Queue

## Definition
First in first out (FIFO) data structure

Singly linked queue : 单队列就是常见的队列, 每次添加元素时，都是添加到队尾，存在“假溢出”的问题也就是明明有位置却不能添加的情况

Circular queue: （避免了“假溢出”的问题）

* enqueue(e) : O(1) adds element e to the back of queue
* dequeue() : O(1) removes and returns the first element
* first() : O(1) returns the first element of the queue
* size() : O(1)
* isEmpty() : O(1)

Time Complexity:
* Access: O(n)
* Search: O(n)
* Insert: O(1)
* Remove: O(1)

## Java API
> java.util.Queue
``` java
public class Queue<E> implements Iterable<E> {
	boolean add(E e) throws Exception;// throws exception if no space
    E remove() throws Exception; // retrieve and remove the head
    E element() throws Exception;// similar to peek
    boolean offer(E e);// inserts element if possible
    E peek();// retrieve but not remove the head, or returns null if empty
    E poll();// retrieves and removes the head of this queue, or returns null if empty
    boolean isEmpty();
    int size();
} 
```
Usage:
```java
Queue<String> myQueue = new LinkedList<String>();
Queue<Integer> myNumbers = new LinkedList<Integer>();
myQueue.add("Hello");
myNumbers.add(1);
```

## Java Implementation
### Array-Based Queue Implementation - limited capacity

```java
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
```
space usage : O(N)

### Singly Linked List Implementation of Queue - no limit

```
public class LinkedQueue<E> implements Queue<E> {
	private SinglyLinkedList<E> list = new SinglyLinkedList<>();

	public LinkedQueue() {}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void enqueue(E e) {
		list.addLast(e);
	}

	public E dequeue() {
		return list.removeFirst();
	}

	public E first() {
		return list.first();
	}

}
```
### Circular Queue
```
public interface CircularQueue<E> extends Queue<E> {
	private CircularlyLinkedList<E> list = new CircularlyLinkedList<>();

	public CircularQueue() {}

	public rotate() {
		list.rotate();
	}
}
```
## Algorithm
### Reverse a Singly-Linked Queue
```java
public Node<E> reverseSinglyLinkedList(Node<E> head) {
	//1 -> 2 -> 3
	Node<E> current = head;// 1
	Node<E> prev = null;
	Node<E> next = null;

	while (current != null) {
		next = current.getNext();// next = 2, next = 3;
		current.setNext(prev); // 1-> null, 2-> 1->null
		prev = current; // prev = 1
		current = next;// current = 2
	}

	head = prev;

	return head;
}
```

# Double-Ended Queues
## Definition
* addFirst(e)
* addLast(e)
* removeFirst()
* removeLast()
* first()
* last()
* size()
* isEmpty()

## Java api
> java.util.Deque
```java
public interface Dequeue<E> {
	E getFirst() throws Exception;
	E getLast() throws Exception;
	void addFirst(e) throws Exception;
	void addLast(e) throws Exception;
	E removeFirst() throws Exception;
	E removeLast() throws Exceptions;
	E peekFirst();
	E peekLast();
	boolean offerFirst(e);
	boolean offerLast(e);
	E pollFirst();
	E pollLast();
	int size();
	boolean isEmpty;
}
```

## Java Implementation
### Circular Array
### Doubly Linked List