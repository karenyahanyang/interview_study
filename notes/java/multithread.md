# Multithreaded Programming

- process-based: computer run 2 or more programs concurrently
- thread-based: a single program can perform 2 or more tasks at once.

| Method                               | Meaning                                         |
| ------------------------------------ | ----------------------------------------------- |
| final String getName()               | Obtains a thread's name                         |
| final int getPriority()              | Obtains a thread's priority                     |
| final boolean isAlive()              | whether still running                           |
| final void join()                    | waits for a thread to terminate                 |
| void run()                           | entry point for the thread                      |
| static void sleep(long milliseconds) | suspends for period of milliseconds             |
| void start()                         | starts a thread by calling its **run()** method |



### Thread Creation

2 ways:

- implements **Runnable**: enable thread to inherit a class other than Thread
- extends **Thread**

```java
class MyThread implements Runnable {
	Thread thrd;

	MyThread(String name) {
		thrd = new Thread(this, name);
		thrd.start();
	}

	public void run() {
		//...
	}
}
```

```java
MyThread m1 = new MyThread("Child #1");
MyThread m2 = new MyThread("Child #2");
MyThread m3 = new MyThread("Child #3");
```

