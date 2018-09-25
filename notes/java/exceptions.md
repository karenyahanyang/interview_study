# Exception Handling

All exception classes are derived from a class called **Throwable**

- **Error**: related to errors that occur in the JVM, beyond your control
- **Exception**: erros that results from program activity



5 Exception Handling Keywords:

- **try**

- **catch**

- **throw**: to manually throw an exception

- **throws**: in some cases an exception that is thrown out of a method must be specified as such by a **throws** clause

- **finally**  


With JDK 7:

- *automatic resource management*: automates the process of releasing a resource such as a file when it is no longer needed
- *multi-catch*: allows 2 or more exceptions to be caught by the same catch clause.
- *final rethrow* or *more precise rethrow*: only those checked exceptions that the associated try block throws, that are not handled by a preceding catch clause, and that are a subtype or supertype of the parameter can be rethrown.



## Throwable Methods

| Method                                   | Description                                                  |
| ---------------------------------------- | ------------------------------------------------------------ |
| Throwable fillinStackTrace()             | Returns a Throwable object that contains a completed stack trace. This object can be rethrown |
| String getLocalizedMessage()             | Returns a localized description of the exception.            |
| String getMessage()                      | Returns a description of the exception                       |
| **void printStackTrace()**               | Displays the stack trace                                     |
| void printStackTrace(PrintStream stream) |                                                              |
| void printStackTrace(PrintWriter Stream) |                                                              |
| String toString()                        | returns as String object containing a complete description of the exception |



## Catching Exception

- can associate more than one **catch** statement with a **try**
- **try** blocks can be nested
- catch subclass type exception before superclass type exception
- **finally** block will be executed whenever execution leaves a try/catch block, no matter what conditions cause it

```java
class ExcDemo {
    public static void main(String args[]) {
        int numer[] = {4,8,16,32,64,128,256,512};
        int denom[] = {2,0,4,4,0,8};
        
        for (int i = 0; i < numer.length; i++) {
            try {
                System.out.println(numer[i]/denom[i]);
            } catch (ArrayIndexOutOfBoundsException exc) {
                //catch subclass ...
                exc.printStackTrace();
            } catch (Throwable exc) {
                //catch superclass...
            } finally {
                System.out.println("Leaving try.");
            }
        }
    }
}
```



### Multi-Catching

```java
try {
    //...
} catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
    //...
}
```



## Throw vs. Throws

Exceptions that are subclasses of **Error** or **RuntimeError** don't need to be specified in a **throws** list.

- unchecked exceptions: need not be included in any method's **throws** list 
- checked exceptions



| Checked Exceptions in java.lang | Meaning                                                      |
| ------------------------------- | ------------------------------------------------------------ |
| ClassNotFoundException          |                                                              |
| CloneNotSupportedException      | attempt to clone an object that does not implement the Cloneable interface |
| InstantiationException          | Attempt to create an object of an abstract class or interface |
| IllegalAccessException          | Access to a class is denied                                  |
| InterruptedException            | One thread has been interrupted by another thread            |
| NoSuchFieldException            |                                                              |
| NoSuchMethodException           |                                                              |
| ReflectiveOperationException    | Superclass of reflection-related exceptions                  |



### Throw

```java
// 'throw' example
class ThrowDemo {
    public static void main(String args[]) {
        try {
            System.out.println("Before throw.");
            throw new ArithmeticException();
        } catch (ArithmeticException e) {
            System.out.println("Exception caught");
        }
        
        System.out.println("After try/catch block");
    }
}
```

```tex
Before throw
Exception caught
After try/catch block
```



### Throws

A call to a method that might throw an exception must be enclosed within a **try** block. 

```java
// use throws
class ThrowsDemo {
    public static char prompt(String str) throws java.io.IOException {
        System.out.println(str + ": ");
        return (char) System.in.read();
    }
    
    public static void main(String args[]) {
        char ch;
        
        try{
            ch = prompt("Enter a letter");
        } catch (java.io.IOException exc) {
            System.out.println("I/O Exception occurred.");
            ch = 'X';
        }
        System.out.println("You pressed " + ch);
    }
}
```



### Rethrow

An exception caught by one catch statement can be rethrown so that it can be caught by an outer catch, allowing multiple handlers access to the exception.

```java
class ExcDemo {
    public static void genException() {
        int numer[] = {4,8,16,32,64,128,256,512};
        int denom[] = {2,0,4,4,0,8};
        
        for (int i = 0; i < numer.length; i++) {
            try {
                System.out.println(numer[i]/denom[i]);
            } catch (ArrayIndexOutOfBoundsException exc) {
                //catch subclass ...
                exc.printStackTrace();
                throw exc; // <- rethrow the exception
            } catch (Throwable exc) {
                //catch superclass...
            } finally {
                System.out.println("Leaving try.");
            }
        }
    }
}

class RethrowDemo {
    public static void main(String args[]) {
        try {
            System.out.println("Before throw.");
            ExcDemo.genException();
        } catch (ArrayIndexOutOfBoundsException e) {// <- catch rethrown exception
            System.out.println("Exception caught");
        }
        
        System.out.println("After try/catch block");
    }
}
```



## Chained Exceptions

2 constructors:

- Throwable(Throwable causeExc)
- Throwable(String msg, Throwable causeExc)

2 methods:

- Throwable getCause()
- Throwable initCause(Throwable causeExc)



```java
public class ExceptionHandling 
{ 
    public static void main(String[] args) 
    { 
        try
        { 
            // Creating an exception 
            NumberFormatException ex = new NumberFormatException("Exception"); 
  
            // Setting a cause of the exception 
            ex.initCause(new NullPointerException( 
                "This is actual cause of the exception")); 
  
            // Throwing an exception with cause. 
            throw ex; 
        } 
  
        catch(NumberFormatException ex) 
        { 
            // displaying the exception 
            System.out.println(ex); 
  
            // Getting the actual cause of the exception 
            System.out.println(ex.getCause()); 
        } 
    } 
} 
```



## Creating Exception Class

define a subclass of **Exception**

```java
class NonIntResultException extends Exception {
    int n;
    int d;
    
    NonIntResultException(int i, int j) {
        n = i;
        d = j;
    }
    
    public String toString() {
        return "Result of" + n+ "/" + d + " is non-integer";
    }
}

throw new NonIntResultException(i,j);
```

