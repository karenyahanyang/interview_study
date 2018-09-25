# I/O

All I/O is byte-oriented. The Character stream simply provide a convenient and efficient means for handling characters.



![img](https://camo.githubusercontent.com/50f105c85f6b42d643d46e1ac7bb0f855b92cd9d/68747470733a2f2f757365722d676f6c642d63646e2e786974752e696f2f323031382f352f31362f313633363764346664316365316234363f773d37323026683d3130383026663d6a70656726733d3639353232)

![img](https://camo.githubusercontent.com/8957efacdf1cd4eac15d844da8353a7f77a3c863/68747470733a2f2f757365722d676f6c642d63646e2e786974752e696f2f323031382f352f31362f313633363764363733623065323638643f773d37323026683d35333526663d6a70656726733d3436303831)

## Byte Stream

Subclasses inherit two abstract classes: **InputStream** and **OutputStream**



| method                                            | description                                                  |
| ------------------------------------------------- | ------------------------------------------------------------ |
| int available()                                   | returns the number of bytes of input currently avaiable for reading |
| void close()                                      | closes the input source. Further read attemps will generate an IOException |
| void mark(int numBytes)                           |                                                              |
| boolean markSupported()                           |                                                              |
| int read()                                        | returns an integer representation of the next available byte of input. -1 is returned at the end of stream |
| int read(byte buffer[])                           | attempts to read up to buffer.length bytes into buffer and returns the actual number of bytes that were succesfully read. |
| int read(byte buffer[], int offset, int numBytes) | attemps to read up to numBytes bytes into buffer starting at buffer[offset] |
| void reset()                                      | resets the input pointer to the previously set mark          |
| long skip(long numbytes)                          |                                                              |

**Methods Defined by InputStream**



| Method                                              | Description                                                  |
| --------------------------------------------------- | ------------------------------------------------------------ |
| void close()                                        |                                                              |
| void flush()                                        | causes any output that has been buffered to be sent to its destination |
| void write(int b)                                   |                                                              |
| void write(byte buffer[])                           |                                                              |
| void write(byte buffer[], int offset, int numBytes) |                                                              |

**Method Defined by OutputStream**



### Predefined Stream

Standard input and output stream is the console by default.

**System.in** is an object of type **InputStream**.

**System.out** and **System.err** are obejcts of type **PrintStream**.



### Console I/O

```java
byte data[] = new byte[10];
System.out.println("Enter some characters.");
System.in.read(data); //<- read an array of bytes from the keyboard
System.out.print("You Entered: ");
for (int i = 0; i < data.length; i++) {
    System.out.print((char) data[i]);
}
System.out.write((int) 'X'); // also works 

```



### File I/O

**FileInputStream** or **FileOutputstream**

```java
import java.io.*;

class CopyFile {
    public static void main(String args[]) throws IOException{
        int i;
        FileInputStream fin = null;
        FileOutputstream fout = null;
        
        if (args.length != 2) {
            System.out.println("Usage: CopyFile from to");
            return;
        }
        
        try {
            fin = new FileInputStream(args[0]);
            fout = new FileOutputStream(args[1]);
           	
            do {
                i = fin.read();
                if (i != -1) fout.write(i);
            } while(i != -1);
            
        } catch(IOException exc) {
            //..
        } finally {
            try {
                if (fin != null) fin.close();
                // fin is non-null only if it was successfully opened
            } catch(IOException e) {
                //...
            }
            
            try {
                if (fout != null) fout.close();
            } catch(IOException e) {
                //...
            }
        }
    }
}
```



#### Automatically Closing a FIle

Begining with JDK 7, *automatic resource manage* or *try-with-resources* is introduced.

>  *try (resource-specification) {*
>
> ​	// use the resource
>
> *}* 



```java
import java.io.*;

class CopyFile {
    public static void main(String args[]) throws IOException{
        int i;
        
        if (args.length != 2) {
            System.out.println("Usage: CopyFile from to");
            return;
        }
        
        try (FileInputStream fin = new FileInputStream(args[0]);
            FileOutputStream fout = new FileOutputStream(args[1])){
            // files will be automatically closed when the try block is left.
            
            do {
                i = fin.read();
                if (i != -1) fout.write(i);
            } while(i != -1);
            
        } catch(IOException exc) {
            //..
        }
    }
}
```



#### Random-Access File

**RandomAccessFile** allows you to access the contents of a file in random order.

It implements DataInput and DataOutput.

use **seek()** to set the current position of the file pointer within the file

```java
import java.io.*;

class RandomAccessDemo {
    public static void main(String args[]) {
        double data[] = { 19.4, 10.1, 123.54, 33.0, 87.9, 74.25 };
        double d;

        try (RandomAccessFile raf = new RandomAccessFile("random.dat", "rw")){

            for(int i = 0; i < data.length; i++) {
                raf.writeDouble(data[i]);
            }

            //now read back specific values
            raf.seek(0); // seek to first double
            d = raf.readDouble(); // 19.4

            raf.seek(8*3);// seek to fourth double
            d = raf.readDouble(); // 33.0

            
        } catch(IOException exc) {
            //..
        }
    }
}
```



### Binary Data I/O

for binary values use **DataInputstream** and **DataOutputStream**

```java
import java.io.*;

class RWData {
    public static void main(String args[]) {
        int i = 10;
        double d = 1023.56;
        boolean b = true;

        try (DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("testdata"))){

            dataOut.writeInt(i);
            dataOut.writeDouble(d);
            dataOut.writeBoolean(b);
            
        } catch(IOException exc) {
            //..
        }

        try (DataInputStream dataIn = new DataInputStream(new FileInputStream("testdata"))){

            i = dataIn.readInt();
            d = dataIn.readDouble();
            b = dataIn.readBoolean();
            
        } catch(IOException exc) {
            //..
        }
    }
}
```



## Character Stream 

Subclasses inherit two abstract classes: **Reader** and **Writer**



### Console I/O

Reading characters:

```java
import java.io.*;

class charStream {
    public static void main(String args[]) throws IOException {
        char c;
        // create BufferedReader linked to System.in
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out, true);

        do {
            c = (char) br.read();
            pw.println(c);// no advantage to be gained
        } while(c != '.');

    }
}
```

Reading Strings:

```java
do {
    str. br.readLine();
    System.out.println(str);
} while (!str.equals("stop"));
```



### File I/O

use **FileReader** and **FileWriter**

```java
import java.io.*;

class fileCharacterStream {
    public static void main(String args[]) throws {
        String str;
        
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"); 
            FileWriter fw = new FileWriter("test2.txt"))) {

            while (str = br.readLine() != null) {
                fw.write(str);
            }
        } catch(IOException e) {
            //...
        }

    }
}
```

# NIO

contained in **java.nio**

built on:

- **buffers**: holds data
- **channels**: represents an open connection to an I/O device such as a file or a socket
- **charset**: defines the way that bytes are mapped to characters. Use *encoder* and *decoder*
- **selector**: perform I/O through multiple channels. Most applicable to socket-backed channels.