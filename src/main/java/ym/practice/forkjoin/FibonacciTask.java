package ym.practice.forkjoin;

import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<Long> {
	private static final long serialVersionUID = -3231181937423812056L;

	private static final int THRESHOLD = 2;

	private ForkJoinFib problem;
	public long result;

	public FibonacciTask(ForkJoinFib problem) {
		this.problem = problem;
	}

	@Override
	public Long compute() {
		if (problem.n < THRESHOLD) {
			result = problem.solve();
		} else {
			FibonacciTask worker1 = new FibonacciTask(new ForkJoinFib(problem.n - 1));
			FibonacciTask worker2 = new FibonacciTask(new ForkJoinFib(problem.n - 2));
			//worker1.fork();
			//result = worker2.compute() + worker1.join();
			result = worker1.compute() + worker2.compute();
		}
		return result;
	}

}
