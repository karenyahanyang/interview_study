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