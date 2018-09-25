# Linked List

## Definition
* Singly-linked list: linked list in which each node points to the next node and the last node points to null
	* size()
	* isEmpty()
	* first()
	* last()
	* addFirst(e)
	* addLast(e)
	* removeFirst()
* Doubly-linked list: linked list in which each node has two pointers, p and n, such that p points to the previous node and n points to the next node; the last node's pointer points to null
	* removeLast()
* Circular-linked list: linked list in which each node points to the next node and the last node points back to the first node
	* rotate() // rotate the first element to the back of the list

Time Complexity:
* Access: O(n)
* Search: O(n)
* Insert: O(1)
* Remove: O(1)

## Java api
> java.util.LinkedList
```java
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, Serializable {
	boolean add(E e);
	void add(int index, E element);
	boolean addAll(Collection<? extends E> c);
	boolean addAll(int index, Collection<? extends E> c);
	void addFirst(E e);
	void addLast(E e);
	void clear();
	Object clone();
	boolean contains(Object o);
	Iterator<E> descendingIterator();
	E element();
	E get(int index);
	E getFirst();
	E get Last();
	int indexOf(Object o);
	boolean offer(E e);
	boolean offerFirst(E e);
	boolean offerLast(E e);
	E poll();
	E pollFirst();
	E pollLast();
	E pop();
	void push (E e);
	E remove();
	E remove(int index);
	boolean remove(Object o);
	E removeFirst();
	boolean removeFirstOccurrence(Object o);
	E removeLast();
	boolean removeLastOccurrence(Object o);
	E set(int index, E element); // replace
	int size()
	Object[] toArray();
	<T> T[] toArray(T[] a);
}
```

## Java Implementation
### Singly Linked List

```java
public class SinglyLinkedList<E> {
	private static class Node<E> {
		private E element;
		private Node<E> next;
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}
		public E getElement() {
			return element;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> n) {
			next = n;
		}
	}

	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;

	public SinglyLinkedList() {}

	public int size() {
		return size;
	}

	public boolean isEmpty(){
		return (size == 0);
	}

	public E first() {
		if (isEmpty()) return null;
		return head.getElement();
	}

	public E last() {
		if (isEmpty()) return null;
		return tail.getElement();
	}

	public void addFirst(E e) {
		head = new Node<>(e, head); 
		if (size == 0) tail = head;
		size++;
	}

	public void addLast(E e) {
		Node<E> newest = new Node<>(e, null);
		if (isEmpty()) {
			head = newest;
		} else {
			tail.setNext(newest); //new node after existing tail
		}
		tail = newest; // new node becomes the tail
		size++;
	}

	public E removeFirst() {
		if (isEmpty()) return null;
		E answer = head.getElement();
		head = head.getNext(); // will be null if list had only one node
		size--;
		if (size == 0) {
			tail = null;
		}

		return answer;
	}
}
```

### Doubly Linked List

```java
public DoublyLinkedList<E> {

	private static class Node<E> {
		private E element;
		private Node<E> prev;
		private Node<E> next;

		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}

		public E getElement() { return element; }
		public Node<E> getPrev() { return prev; }
		public Node<E> getNext() { return next; }
		public void setPrev(Node<E> p) { prev = p; }
		public void setNext(Node<E> n) { next = n; }
	}

	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;

	public DoublyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public E first() {
		if (isEmpty()) return null;
		return header.getNext().getElement();
	}

	public E last() {
		if (isEmpty()) return null;
		return trailer.getPrev().getElement();
	}

	public void addFrist(E e) {
		addBetween(e, header, header.getNext());
	}

	public void addLast(E e) {
		addBetween(e, trailer.getPrev(), trailer);
	}

	public E removeFirst() {
		if (isEmpty()) return null;
		return remove(header.getNext());
	}

	public E removeLast() {
		if (isEmpty()) return null;
		return remove(trailer.getPrev())
	}

	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newest = new Node<>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
	}

	private E remove(Node<E> node) {
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;

		return node.getElement();
	}
}
```

### Circularly Linked List
```java
public class CircularlyLinkedList<E> {
	private static class Node<E> {
		private E element;
		private Node<E> next;
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}
		public E getElement() {
			return element;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> n) {
			next = n;
		}
	}
	private Node<E> tail = null;
	private int size = 0;

	public CircularlyLinkedList() {}

	public int size() {
		return size;
	}

	public boolean isEmpty(){
		return (size == 0);
	}

	public E first() {
		if (isEmpty()) return null;
		return tail.getNext().getElement();
	}

	public E last() {
		if (isEmpty()) return null;
		return tail.getElement();
	}

	public void rotate() {
		if (tail != null)
			tail = tail.getNext();
	}

	public void addFirst(E e) {
		if (size == 0) {
			tail = new Node<>(e, null);
			tail.setNext(tail);
		} else {
			Node<E> newest = new Node<>(e, tail.getNext()); // tail -> e -> head 
			tail.setNext(newest);
		}
		size++;
	}

	public void addLast(E e) {
		addFirst(e);
		tail = newest;
	}

	public E removeFirst() {
		if (isEmpty()) return null;
		Node<E> head = tail.getNext();
		if (head == tail) return null;
		else tail.setNext(head.getNext());
		size--;

		return head.getElement();
	}
}
```

## Algorithm
### Cloning

```java
public SinglyLinkedList<E> clone() throws CloneNotSupportedException {

	SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();

	if (size > 0) {
		other.head = new Node<>(head.getElement(), null);
		Node<E> walk = head.getNext();
		Node<E> otherTail = other.head;
		while(walk != null) {
			Node<E> newest = new Node<>(walk.getElement(), null);
			otherTail.setNext(newest);
			otherTail = newest;
			walk = walk.getNext();
		}
	}

	return other;
}
```

