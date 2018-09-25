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