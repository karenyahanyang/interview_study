# Data Types and Operators

Java is **pass-by-value**

## Variable Types

* instance: declared inside a class but not within a method, **always get a default value** 
* local: declared within a method, **must be initizlied** before used (no default value). 

## 2 Data Types

* primitive: fundamental values
* object reference: reference to object

### 8 Primitive Data Types

| Type                       | Depth                      | Range                                                    |
| -------------------------- | -------------------------- | -------------------------------------------------------- |
| boolean                    | JVM-specific               | true/false                                               |
| char                       | unsigned 16 bits / 2 bytes | 0 to 65535                                               |
| **4 Integer Types**        |                            |                                                          |
| byte                       | 8 bits/ 1 byte             | -128 to 127                                              |
| short                      | 16 bits/ 2 bytes           | -32,768 to 32,767                                        |
| int                        | 32 bits/ 4 bytes           | -2,147,483,648 to 2,147,483,647                          |
| long                       | 64 bits/ 8 bytes           | -9,223,372,036,854,775,808 to -9,223,372,036,854,775,807 |
| **2 Floating point types** |                            | double is most commonly used                             |
| float                      | 32 bits/ 4 bytes           | varies                                                   |
| double                     | 64 bites/ 8 bytes          | varies                                                   |



```java
char ch;
ch = 'x'; // ch is x
ch++;// ch is now Y
```



### Type Conversion

- automatic type conversion:

  - the two types are compatible
  - the destination type is larger than the source type i.e. long to double

- casting incompatible types i.e. double to int


### Object Reference

* holds bits that represent a way to **access** an object like a pointer or an address
* all references for a given JVM will be the **same size** regardless of the objects they reference

```java
Book b = new Book();
Book c = new Book();
Book d = c;

//references: 3
//objects: 2
```

```java
Book b = new Book();
Book c = new Book();
b = c;
c = null;

//active reference : 1
//null reference: 1
//reachable objects: 1
//abandoned objects: 1
```



### Comparing Variables with ==

primitives: compares the bit pattern

```java
int a = 3;
byte b = 3;
if (a == b) {
    // 00000011 == 000000011 true
}
```

references: returns true if two reference variables refer to the same object

```java
Foo a = new Foo();
Foo b = new Foo();
Foo c = a;

if (a == b) { 
    //false 
}
if (a == c) {
    //true
}

```

## Arrays

**Irregular Arrays**

```java
int riders[][] = new int[2][];
riders[0] = new int[10];
rider[1] = new int[2];

for (int x[]: riders){
    for (int y: x) {
        System.out.println("value is:" + y);
    }
}
```

## Strings

* Strings are objects
* immutable: cannot be changed once created

```java
String str = new String("Hello");
String str2 = "World;"
```

Operands

| methods              |                                                              |
| -------------------- | ------------------------------------------------------------ |
| boolean equals(str)  |                                                              |
| int length()         |                                                              |
| char charAt(index)   |                                                              |
| int compareTo(str)   | returns less than zero if the invoking string is less thant str |
| int indexOf(str)     |                                                              |
| int lastIndexOf(str) |                                                              |



**Q: Why do we use *equals()* for strings instead of  *==* ?**

A: Applying the == to two String references simply determines whether the two references refer to the same object. *equals()* method compares the character sequences of two String objects for equality.

### String, StringBuffer & StringBuilder

*StringBuffer* and *StringBuilder* both uses AbstractStringBuilder. 

*StringBuffer* has lock -> safe for multithreading. 

1. little manipulation -> *String*
2. single threaded large manipulation -> *StringBuilder*
3. multithreaded large manipulatiion -> *StringBuffer*



### Type Wrappers to Convert Numeric Strings

| Wrapper | Conversion Method                     |
| ------- | ------------------------------------- |
| Double  | static double parseDouble(String str) |
| Float   | parseFloat(String str)                |
| Long    | parseLong(String str)                 |
| Integer | parseInteger(String str)              |
| Short   | parseShort(String str)                |
| Byte    | parseByte(String str)                 |

```java
String str = "1.1";
double t = Double.parseDouble(str);
```



## Operators

### Normal vs. Short-Circuit Logical Operators

Normal operands will always evaluate each operand but short-circuit versions will evalute the second operand only when necessary.



### Bitwise Operators

Can be used on vlaues of type **long, int, short, char** or **byte**.

Not on **boolean, float, double** or **class** type. 

| Operator | Result               |
| -------- | -------------------- |
| &        |                      |
| \|       |                      |
| ^        | XOR                  |
| >>       | Shift right          |
| >>>      | Unsigned shift right |
| <<       | shift left           |
| ~        | one's complement     |

#### AND 

Think AND as a way to turn bits off.

In Unicode/ASCII chracter set, the lowercase letters are the same as the uppercase ones except that the the lowercase ones are greater in value by exactly 32 => 2^5 => the 6th bits will be 1. We can transform a lowercase letter to uppercase by just turning off the 6th bits.

```java
char ch = (char)('a' + 0);
ch = (char) ((int) ch & 65503); // 65503 -> 1111111111011111
```

We can also use AND to determine whether a bit is on or off.

```java
if ((status) & 8 != 0) System.out.println("bit 4 is on");
```

#### OR

We can make use of the OR to turn on bits. 

```java
char ch = (char) ('A' + 0);
ch = (char) ((int) ch | 32);
```

#### XOR

R1 = X^Y; R2= R1^Y; then R2 is the same value X.

We can use XOR for simple cipher program.

#### Shifts

Since binary is based on powers of two, the shift operators can be used as a shortcut for multiplying or dividing an integer by 2. **A shift left doubles a value. A shift right halves it.** 