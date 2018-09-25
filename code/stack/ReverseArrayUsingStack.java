public static <E> reverse(E[] array) {
	Stack<E> buffer = new ArrayStack<>(array.length);

	for (int i = 0; i < array.length; i++) {
		buffer.push(array[i]);
	}

	for (int i = 0; i < array.length; i++) {
		array[i] = buffer.pop();
	}
} 