# Tree
## Definition
an undirected, connected, acyclic graph
* depth: number of ancestors of p, other than p itself
* height: maximum of the depths of its positions

## Java api

```java
public interface Tree<E> extends Iterable<E> {
	Position<E> root();
	Position<E> parent(Position<E> p) throws IllegalArgumentException;
	Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;// return an iterable collection containing the children of position p
	int numChildren(Position<E> p) throws IllegalArgumentException;
	boolean isInternal(Position<E> p) throws IllegalArgumentException;
	boolean isRoot(Position<E> p) throws IllegalArgumentException;
	int size();
	boolean isEmpty();
	Iterator<E> iterator();
	Iterable<Position<E>> positions();
}
```

## Algorithm
### Depth
```java
public int depth(Position<E> p) {
	if (isRoot(p))
		return 0;
	else
		return 1+depth(parent(p));
}
```

### Height
```java
public int height(Position<E> p) {
	int h = 0;
	for (Position<E> c: children(p))
		h = Math.max(h, 1+height(c));
	return h;
}
```

# Binary Tree
an ordered tree with :
* every node has at most two children
* each child node is labeled as being either a left child or a right child
* a left child precedes a right child in the order of children of a node


