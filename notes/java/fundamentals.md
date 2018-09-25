# Java Fundamentals

## The way Java works

4 steps: 

1. Create source document e.g. Party.java 
2. check by compiler by running ```% javac Party.java```
3. if no errors, compiler creates platform dependent bytecode: Party.class 
4. run Party.class by```% java Party```  with java virtual machine (JVM)



Translating a Java program into bytecode makes it much easier to run a program in a wide variety of environments because only the JVM needs to be implemented for each platform.

If a Java program were compiled to native code, then different versions of the same program would have to exist for each type of CPU.

JVM also helps to make it secure



## What is a Java Applet?

An applet is a special kind of Java program that is designed to be transmitted over the Internet and automatically executed by a Java-compatible web browser.

An applet is downloaded on demand, without further interaction with the user. If the user clicks a link that contains an pplet, the applet will be cautomatically downloaded and run in the rowser.

They are small programs, typically used to display data provided by the server, handle user input or provide simple functions such as a loan calculator. The applet allows some functionality to be moved from the server to the client



### Security

Java prevent an applet from launching an attack by confining an applet to the Java execution environment and not allowing it access to other parts of the computer.



## What's a Servlet

A servlet is a small program that executes on a server. Just as applets dynamically extend the functionality of a web browser, servlets dynamically extend the functionality of a web server.



## The Java Buzzwords

| Buzzwords            | Detail                                                       |
| -------------------- | ------------------------------------------------------------ |
| simple               | easy to learn and use                                        |
| secure               |                                                              |
| portable             | can execute in any environment for which there is a Java run-time system |
| OO                   |                                                              |
| Robust               | error-free programming by being strictly typed and performing run time checks |
| Multithreaded        |                                                              |
| Architecture-neutral | not tied to specific machine or OS arch.                     |
| Interpreted          | supports cross-platform code through the use of Java bytecode |
| High performance     | highly optimized for speed of execution                      |
| Distributed ?        | was designed with the distributed environment of the Internet in mind |
| Dynamic              |                                                              |



## The Principles of OOP: Encapsulation, Polymorphism, Inheritance

### Encapsulaiton

A programming mechanism that binds together code and the data it manipulates and that keeps both safe from outside interference and misues. 



### Polymorphism

Allows a general class to specify methods that will be common to all of its derivatives.



### Inheritance

One object can have the properties of another object.



## How to design an object class

* things the object knows : instance variables (state)
* things the object does : methods (behaviour)



## What's the difference between a class and an object?

A class is not an object , is a blueprint for an object.

## Making your first object	

```java
class Dog {
    int size;
    String breed;
    String name;
    
    void bark() {
        System.out.println("Ruff! Ruff!");
    }
}
```

```java
class DogTestDrive {
    public static void main (String[] args) {
        Dog d = new Dog();
        d.size = 40;
        d.bark();
    }
}
```



## Identifiers in Java

Variables names may start with any letter, an underscore or a dollar sign. 

**can't start an identifier with a digit**



## What if I have a 100 classes?

You can put all of your application files into a Java Archive : a *.jar* file in which yu can include a simple text file formatted as a *manifest* that defines which class in that jar holds the main() method that should run.