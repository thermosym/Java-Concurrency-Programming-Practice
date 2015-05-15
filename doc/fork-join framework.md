# Java Fork/Join Framework
[TOC]

## Java Traditional way
- You implement some Runnable objects / Thread objects.
- You control the creation, execution, and status of those threads in your program.

## Java 5 Executor / ExecutorService interfaces
- Executor framework separates the task creation and its execution
	- Implement **Runnable** objects, use an Executor object.
	- Send the Runnable tasks to the **Executor**.
	- Executor creates, manages, and finalizes the necessary threads for the tasks.

## Java 7 ExecutorService (Fork/Join framework)
-  Solve problems that can be broken into smaller tasks using the **divide** and **conquer** technique
	-  If task size is big --> **fork** it to smaller tasks
	-  If task size is small --> solve it --> return result
-  **ForkJoinPool** looks like a special kind of Executor.
	-  main difference between the Fork/Join and the Executor frameworks is the **work-stealing** algorithm
	-  Worker thread always looks for other tasks that have not been executed yet, is not blocked by **join** task.

-  Fork/Join framework limitations
	-  The only synchronization mechanisms: fork() and join() operations. (When use other synchronization mechanisms, such as **sleep()** in the Fork/Join framework, the worker thread will be blocked.
	-  Tasks should not perform I/O operations (why?)
	-  Tasks can't throw checked exceptions.

## The core of the Fork/Join framework
-  ForkJoinPool
-  ForkJoinTask
	-  RecursiveAction
	-  RecursiveTask
- - -
## Reference
- González, Javier Fernández. **Java 7 Concurrency Cookbook**. Packt Pub., 2012.


