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