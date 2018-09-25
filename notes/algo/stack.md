# Stack

## Definition
Last in first out data structure (LIFO)
* push (e) : O(1)
* pop () - removes and returns the top element O(1)
* top()  - looks at the object at the top of this stack without removing O(1)
* getCount() : O(1)
* isEmpty() : O(1)

Time complexity:
* Access: O(n)
* Search: O(n)
* Insert: O(1)
* Remove: O(1)

## Java API
> java.util.Stack
```java
public class Stack<E> implements Iterable<E> {
    E push(E item);
    E pop() throws Exception;
    E peek() throws Exception;
    boolean empty();
    int size();
    int search(Object o); // returns the 1-based position when an object is on this stack
}
```
General Usage: 
```java
Stack<Integer> s = new Stack<Integer>();
Stack<String> s1 = new Stack<String>();
s.push(7);
s1.push("string");
```

## Java Implementation
### Array Implementation of Stack - limited capacity

```java
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
```
### Singly-Linked List Implementation of Stack -- no capacity limit

```java
public class LinkedStack<E> implements Stack<E> {
	private SinglyLinkedList<E> list = new SinglyLinkedList<>();

	public LinkedStack(){}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void push(E item) {
		list.addFirst(item);
	}

	public E top() {
		return list.first();
	}

	public E pop() {
		return list.removeFirst();
	}
}
```

## Algorithm
### Reversing an Array Using a Stack
```java
public static <E> reverse(E[] array) {
	Stack<E> buffer = new ArrayStack<>(array.length);

	for (int i = 0; i < array.length; i++) {
		buffer.push(array[i]);
	}

	for (int i = 0; i < array.length; i++) {
		array[i] = buffer.pop();
	}
} 
```

### Matching Parentheses and HTML Tags
Matching Parentheses
```java
public static boolean isMatched(String expression) {
	final String opening = "([{";
	final String closing = ")]}";
	Stack<Character> buffer = new LinkedStack<>();

	for (char c: expression.toCharArray) {


		if (opening.indexOf(c) != -1) {
			//c is an opening delimiter
			buffer.push(c);
		} else if (closing.indexOf(c) != 1) {
			//c is a closing delimiter

			if (buffer.isEmpty()) return false; // no opening delimiter found

			if (closing.indexOf(c) != opening.indexOf(buffer.pop())) return false; // mismatching
		}
	}

	return buffer.isEmpty(); // buffer should be empty if all opening delimiter matched
}
```

Matching Tags
```java
public static boolean isHTMLMatched(String html) {
	Stack<String> buffer = new LinkedStack<>();

	int i = html.indexOf("<"); // find first '<' character (if any)

	while (i != -1) {
		int j = html.indexOf(">"); // find '>'

		if (j == -1) return false;
		String tag = html.substring(i+1, j); // string away <>

		if (!tag.startsWith("/")) {
			// this is an opening tag
			buffer.push(tag);
		} else {
			if (buffer.isEmpty()) return false; // no opening tag exists
			
			if (!tag.subString(1).equals(buffer.pop())) return false; // mismatched tag
		}

		i = html.indexOf("<", j+1); // find next "<"
	}

	return buffer.isEmpty();
}
```

