package ym.practice.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinFib {

	public int n;

	public ForkJoinFib(int n) {
		this.n = n;
	}

	public long solve() {
		return fibonacci(n);
	}

	private long fibonacci(int n) {
		if (n <= 1)
			return n;
		else
			return fibonacci(n - 1) + fibonacci(n - 2);
	}
	
	public static void main(String[] args){
		ForkJoinFib fib = new ForkJoinFib(2);
		FibonacciTask task = new FibonacciTask(fib);
		ForkJoinPool pool = new ForkJoinPool(2);
		pool.invoke(task);
		long result = task.result;
		System.out.println(result);
	}

}
