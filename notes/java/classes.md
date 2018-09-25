# Classes, Objects and Methods

*Objects are instances of a class*



## Classes

Classes for encapsulation:

1. links data with the code that manipulates it

2. access to members can be controlled


###  3 Access Modifiers

* **public** : can be accessed by any other code in your program including methods fined in side other classes. **Default**
* **private**: member can be accesssed only by other members of its class.
* **protected**



### Static Members

Can be accessed before any objects of its class are created and without reference to any object.



```java
class StaticDemo {
    int x;
    static int y;
    
    int sum() {
        return x+y;
    }
    
    static int div() {
        return x/y; // won't compile! can't access a non-static variable
    }
}

class SDemo {
    public static void main(String args[]) {
        StaticDemo ob1 = new StaticDemo();
        StaticDemo ob2 = new StaticDemo();
        ob1.x = 10;
        ob2.x = 20;
        
    	StaticDemo.y = 100;
        ob1.sum();
        ob2.sum();
        StaticDemo.val(); // error
    }
}
```



#### Static Method

**Restrictions:**

- can directly call only other static methods
- can directly access only static data
- do not have a *this* reference

#### Static Blocks

A static block is executed when the class is first loaded. Thus, it is executed before the class can be used for any other purpose.

```java
class StaticBlock {
    static double rootOf2;
    static double rootOf3;
    
    static {
        System.out.println("Inside static block.");
        rootOf2 = Math.sqrt(2.0);
        rootOf3 = Math.sqrt(3.0);
    }
    
    StaticBlock(String msg) {
        System.out.println(msg);
    }
}

class SDemo3 {
    public static void main(String args[]) {
        StaticBlock ob = new StaticBlock("Inside constructor");
    }
}

/*
Inside static block
Inside constructor
*/
```



### Nested Classes

The scope of a nested class is bounded by its outer class.

2 general types:

* static
* non-static (inner class)

Inner classes have access to all of the variables and methods of its outer class and may refer to them directly in the same way that other non-static members of the outer class do. Outer class must declare a reference object to the inner class inorder to use its members.



Sometimes an inner class is used to provide a set of services that is used only by its enclosing class.

```java
class Outer {
    int nums[];
    
    Outer(int n[]) {
        nums = n;
    }
    
    void analyze() {
        Inner inOb = new Inner();// !!! important
       	System.out.println("Minimum:" + inOb.min());
    }
    
    class Inner {
        int min() {
            int m = nums[0];
            
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < m) m = nums[i];
                
                return m;
            }
        }
    }
}

class NestedClassDemo {
    public static void main(String args[]) {
        int x[] = {3,2,1,5,6,9,7,8};
        Outer outOb = new Outer(x);
        outOb.analyze();
    }
}
```



**Static nested class** must access other members of its outer class through an object reference.

#### Anonymous Inner Classes



### Inheritance

Allowing one class to incorporate another class into its declaration by using the ***extends*** keyword. Subclass extends the superclass.

**Subclasses cannot access those members of the superclass that have been declared *private***.

**Q: When should I make an instance variable private?**

A: 

* When an instance variable is to be used only by methods defined within its class.
* if an instance variable must be within certain bounds (prevent invalid values from being assigned)



```java
class TwoDShape {
    private double width;
    private double height;
    
    double getWidth() { return width; }
    double getHeight() { return height; }
    void setWidht(double w) {width = w;}
    void setHeight(double h) {height = h;}
    void showDim() {
       //...
    }
}

class Triangle extends TwoDshape {
 	String style;
    
    double area() {
        // return width*height will cause error
        return getWidth()*getHeight()/2;
    }
    
    void showStyle() {
        //...
	}
}

class Shapes2 {
    public static void main(String args[]) {
        Triangle t1 = new Triangle();
       
        t1.setWidth(4.0);
        t1.setHeight(4.0);
        t1.showStyle();
        t1.showDim();
    }
}
```



#### Subclass Constructor Only

Simply construct the subclass object. The superclas portion of the object is constructed automaticallly using its default constructor.



#### Both superclass and subclass constructors

superclass constructor executes first

***super***:

- calls a super class constructor
- access a member of the superclass that has been hidden by a member of a subclass

**super() must be the first statement executed inside a subclass constructor**

```java
class TwoDShape {
    private double width;
    private double height;
    
    TwoDShape(w, h) {
        width = w;
        height = h;
    }
    double getWidth() { return width; }
    double getHeight() { return height; }
    void setWidht(double w) {width = w;}
    void setHeight(double h) {height = h;}
    void showDim() {
       //...
    }
}

class Triangle extends TwoDshape {
 	String style;
    
    Triangle(String s, double w, double h){
        super(w,h);
        style = s;
    }
    
    double area() {
        // return width*height will cause error
        return getWidth()*getHeight()/2;
    }
    
    void showStyle() {
        //...
	}
}
        
```



```java
class A {
    int i;
}
class B extends A {
    int i;
    
    B(int a, int b) {
        super.i = a;
        i = b;
    }
    
    void show() {
        System.out.println("i in superclass: " + super.i);
        System.out.println("i in subclass: " + i);
    }
}

class UseSuper {
    public static void main(String args[]) {
        B subOb = new B(1,2);
        subOb.show();
    }
}
```



#### Multilevel Hierarchy

B extends A

C extends B -> C inherits all aspects of B and A

```java
class TwoDShape {
    private double width;
    private double height;
    
    TwoDShape(w, h) {
        width = w;
        height = h;
    }
    double getWidth() { return width; }
    double getHeight() { return height; }
    void setWidht(double w) {width = w;}
    void setHeight(double h) {height = h;}
    void showDim() {
       //...
    }
}

class Triangle extends TwoDshape {
 	String style;
    
    Triangle(String s, double w, double h){
        super(w,h);
        style = s;
    }
    
    double area() {
        // return width*height will cause error
        return getWidth()*getHeight()/2;
    }
    
    void showStyle() {
        //...
	}
}

class ColorTriangle extends Triangle {
    private String color;
    
    ColorTriangle(String c, String s, double w, double h) {
        super(s,w,h);
        color = c;
    }
}
        
```



#### Superclass Reference

Superclass ob = new Subclass();

*It is the type of the reference variable* - not the type of the object that it referes to - that determines what memebrs can be accessed.

only have access to those parts of the object defined by the superclass

```java
class X {
    int a;
    X(int i) {
        a = i;
    }
}
class Y extends X {
    int b;
    Y (int i, int j) {
        super(j);
        b = i;
    }
}

class SupSubRef {
    public static void main(String args[]) {
        X x = new X(10);
        X x2;
        Y y = new Y(5, 6);
        
        x2 = x; // ok, both of same type
        
        x2 = y;// ok because Y is derived from X
        
        x2.a = 19;// ok
        x2.b = 27;// X doesn't have a b member
    }
}
```



#### Method Overriding

When a method in a subclass has the same return type and signature ad a method in its superclass, the the method in the subclass is said to *override* the method in the superclass.

```java
class A {
    int i, j;
    A(int a, int b) {
        i = a;
        j = b;
    }
    void show() {
        System.out.println("i and j: " + i + " " + j);
    }
}

class B extends A {
    int k;
    
    B(int a, int b, int c) {
        super(a, b);
        k = c;
    }
    
    void show() {
        // will print both
        super.show();
        System.out.println("k: " + k);
    }
}
```

##### Dynamic Method Dispatch

The mehchanism by which a call to an overridden method is resolved at run time rather than compile time;

*It is the type of the obejct being referred to* (not the type of the reference variable) that determines which version of an overridden method will be executed.

```java
class Sup{
    void who(){
        System.out.println("who() in Sup");
    }
}

class Sub1 extends Sup {
    void who(){
        System.out.println("who() in Sub1");
    }
}

class Sub2 extends Sup {
    void who() {
        System.out.println("who() in sub2");
    }
}
class demo {
    public static void main(String args[]) {
        Sup superOb = new Sup();
        Sub1 subOb1 = new Sub1();
        Sub2 subOb2 = new Sub2();
        
        Sup supRef;
        
        supRef = superOb;
        supRef.who();
        
        supRef = subOb1;
        supRef.who();
        
        supRef = subOb2;
        supRef.who();
    }
}
```

```tex
who() in Sup
who() in Sub1
who() in Sub2
```



#### Abstract Classes

When a superclass is unable to create a meaningful implementation for a method.

An ***abstract*** method contains no body, thus must be override by a subclass.

> abstract *type name(parameter-list);*



- A class that contains one or more abstract methods must also be declared as abstract.

- Since an abstract class does not define a complete implementation, there can be no objects of an abstract class.
- subclass must implement all of the abstract methods, if it doesn't then the subclass must also be specified as abstract.



#### final

- prevent overriding
- prevent inheritance
- prevent variable from changing throughout the lifetime of your program 



```java
//prevent overriding
class A {
    final void meth() {
        //...
    }
}

class B extends A {
    void meth() {
     	// error!! cannot override
    }
}
```

```java
// prevent inheritance
final class A {
    //...
}

class B extends A {
   // error!! cannot inherit
}
```



**final static variable**: lets you refer to the constant through its class name rather than through an object.

**final parameters**: prevents it from being changed within the method

**final local variables**: prevents it from being assigned a value more than once. 



#### Object class

Object class is an implicit superclass of all other classes. This means that a reference variable of type Object can refer to an object of any other class. 



| Method                                                | Purpose                                                      |
| ----------------------------------------------------- | ------------------------------------------------------------ |
| Object clone()                                        | creates a new object that is the same as the object being clones |
| boolean equals(Object object)                         |                                                              |
| void finalize()                                       | Called before an unused object is recycled                   |
| final Class<?> getClass()                             | Obtains the class of an object at run time.                  |
| int hashCode()                                        | returns the hash code associated with the invoking object    |
| final void notify()                                   | resumes execution of a thread waiting on the invoking object |
| final void notifyAll()                                | resumes execution of all threads waiting on the invoking object |
| String toString()                                     | returns a string that describes the object                   |
| final void wait(long milliseconds?, int nanoseconds?) | waits an another thread of execution                         |



## Objects

```java
class MyClass {
    int x;
    MyClass(int i) {
        x = i;
    }
    
    protected void finalize() {
        System.out.println("Finalizing");
    }
}

class ParmConsDemo {
    public static void main(String args[]) {
        MyClass t1 = new MyClass(10); // new returns a reference to the object
    }
}
```



### new operator

Allocates memeory for an object and initializes it using the object's constructor.



### Constructors

* Initializes an object when it is created
* has the **same name as its class** and is syntactically similar to a method
* no explicit return type
* **all classes have constructors** whether you define or not, because Java automatically provides a default constructors that initializes all member variables to their default values (zero, null, false).



**Q: Why don't I need new for variables of the primitive types**

Java's primitives types are not implemented as objects.



### Garbage Collection

It is possible for *new* to fail because there is insufficient free memory.

When no references to an object exist, that object is assumed to be no longer needed and the memory occupied by the object is released.



### The finalize() Method

* called just before an object's final destruction by the garbage collector

* used to ensure that an object terminates cleanly i.e. use *finalize()* to make sure than an open file owned by that object is closed

* you cannot know when or even if *finalize()* will be executed


**Q: Is *finalize()* similar to *destructors* in C++?**

A: Not the same. A C++ destructor is always called just before an object goes out of scope but you can't know when finalize() will be executed.



### The *this* Keyword

When the name of a parameter/local variable is the same as the name of an instance variable, the local name hides the instance variable.

```java
Pwr(double b, int e) {
    this.b = b;
    this.e = e;
    
    val = 1;
    if (e == 0) return;
    for(; e > 0; e--) val = val * b;
}
```



## Methods

A method uses parameters.

A caller passes argument.



### Passing Arguments

Primitive parameters are passed to methods by use of call-by-value.

Objects are call-by-reference.



**Q: Is there any way that I can pass a primitive type by reference?**

A: Use wrapper classes such as *Double, Float, Byte, Short, Integer, Long* and *Character*.



### Method Overloading

one of the ways that Java implements polymorphism

They type and/or number of the parameters of each overloaded method must differ

```java
class MyClass {
    int x;
    
    MyClass() {
        x = 0;
    }
    
    MyClass(int i) {
        x = i;
    }
    
    MyClass(double d) {
        x = d;
    }
    
    MyClass(int i, int j) {
        x = i*j;
    }
}
```



### Varargs Arguments

Old fashion ways:

* use method overloading
* put arguments into an array

Simplifies the creation of methods that require a variable number of arguments.

**Varargs parameter must be las**

```java
class VarArgs {
    static void vaTest( String msg, int ... v) {
        System.out.println(msg + v.length);
        for (int i = 0; i < v.length; i++) {
            System.out.println("arg" + i + ": " + v[i]);
        }
    }
    
    public static void main(String args[]) {
        vaTest("One varag: ", 10);
        vaTest("Three varargs: ", 1,2,3);
        vaTest("No varags:");
    }
}
```

#### Overloading Varargs Methods

```java
static void vaTest(int ... v){
    //...
}
static void vaTest(boolean ... v){
    //...
}
static void vaTest(String msg, int ... v){
    //...
}
```

**Can cause ambiguity**:

* when no input
* when normal parameter can be consider as a vararg

