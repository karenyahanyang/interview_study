# Packages and Interfaces



## Packages

- a way to name a collection of classes
- a means by which classes can be encapsulated

when no package statement is specified, the default (global) package is used.

```java
package mypack;

package alpha.beta.gamma;// must be stored in .../alpha/beta/gamma
```



```java
package bookpack;
class Book {
    //...
}
class BookDemo {
    //...
}
```

```java
javac bookpack/BookDemo.java
java bookpack.BookDemo // you will need to be in the directory above bookpack
java BookDemo // error! cannot be executed by itself
```

```java
package bookpackext;

class UseBook {
    publc static void main(String args[]) {
        bookpack.Book books[] = new bookpack.Book[5];
        //...
    }
}
```



### Importing Packages

```java
package bookpackext;
import bookpack.*; //or import bookpack.Book

class UseBook {
    publc static void main(String args[]) {
        Book books[] = new Book[5]; //can refer to Book directly
    }
}
```



### Member Access

* ***public*** members are visible everywhere including different classes and packages
* ***private*** members are accessible only to other members of its classes
* ***protected*** members are accessible within its package and to all subclasses including subclasses in other packages
* ***default*** members are visible within its packages but not outside its package.



```java
//protected member example
package bookpack;
class Book {
    protected String title;
    protected String author;
    protected int pubDate;
    
    Book(String t, String a, int d) {
        title = t;
        author = a;
        pubDate = d;
    }
    //...
}
```

```java
package bookpackext;

class ExtBook extends bookpack.Book {
	private String publisher;
    
    public ExtBook(String t, String a, int d, String p) {
        super(t,a,d);
        publisher = p;
    }
    
    public String getTitle() {
        return title; // <- access to Book's members is allowed by subclasses
    }
}

class Demo {
    public static void main(String args[]) {
        ExtBook books[] = new ExtBook[5];
        book[0] = new UseBook("test","test","2014","test");
        book[0].getTitle();// works
        book[0].title = "error"; // cannot access
    }
}
```



## Interfaces

- any number of classes can implement an interface
- one class can implement any number of interfaces

*access* is either **public** or **default**.

key difference between interface and a class: class can maintain state information (through use of instance variables) but an inteface cannot.



### Default Method

**Prior to JDK8, an interface could not define any implementation. Today it is possible to add a default implementation to an interface method**

```java
//default method
public interface MyIF {
    int getUserID();
    default int getAdminID() {
        return 1;
    }
}
```





### Interface Reference

such a variable can refer to any object that implements its interface.

***Interface ob = new subObj();*** 

```java
public interface Series {
    int getNext();
    void reset();
    void setStart(int x);
}

class ByTwos implements Series {
    int start;
    int val;
    
    ByTwos() {
        start = 0;
        val = 0;
    }
    
    public int getNext() {
        val+= 2;
        return val;
    }
    
    public void reset() {
        val = start;
    }
    
    public void setStart(int x) {
        start = x;
        val = x;
    }
}

class ByThrees implements Series {
    int start;
    int val;
    
    ByThrees() {
        start = 0;
        val = 0;
    }
    
    public int getNext() {
        val += 3;
        return val;
    }
    
    public void reset() {
        val = start;
    }
    
    public void setStart(int x) {
        start = x;
        val = x;
    }
}

class SeriesDemo3 {
    public static void main(String args[]) {
        ByTwos twoOb = new ByTwos();
        ByThrees threeOb = new ByThrees();
       	Series ob;
        
        ob = twoOb;
        ob.getNext();// <-access an object via an interface reference
        ob = threeOb;
        ob.getNext();
    }
}
```



### Variables in Interface

are implicit **public**, **static** and **final**. 

To define a set of shared constants, create an interface that contains only these constants without any methods.

```java
interface IConst {
    int MIN = 0;
    int MAX = 10;
    String ERRORMSG = "Boundary Error";
}

class IConstD implements IConst {
    public static void main(String args[]) {
        int nums[] = new int[MAX];
    }
}
```



### Extended Inteface

One interface can extend another.

```java
interface A {
    void meth1();
    void meth2();
}
interface B extends A {
    void meth3();
}
class MyClass implements B {
    public void meth1(){
        //...
    }
    public void meth2() {
        //...
    }
    public void meth3() {
        //...
    }
}
```



#### Multiple Inheritance Issues

- if a subclass provides an override of a default method in an interface, the subclass's version is used.
- If a class inherits two interfaces that both have the same default method, if the class does not override that method, an error will result
- if one interface inherits another with both defining a common default method, the inheriting interface's version takes precedence. i.e. MyClass implements Beta, Beta extends Alpha, Beta's default method will be used

It is possible to refer explicitly to a default implementation by using a new form of super:

*InterfaceName.super.methodName();*



### Static Methods in an Interface

JDK 8 added the ability to define one or more static methods in an interface.

- No implementation of the interface is necessary
- no instance of the interface is required in order to call a static method

```java
public interface MyIF {
    int getUserID();
    default int getAdminID() {
        return 1;
    }
    static int getUniversalID() {
        return 0;
    }
}

int uID = MyIF.getUniversalID();
```

