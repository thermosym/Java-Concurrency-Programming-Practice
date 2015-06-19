package ym.practice.forkjoin;

import java.util.concurrent.ForkJoinPool;

import org.junit.Test;

public class ForkJoinFibTest {

	private ForkJoinFib unit = new ForkJoinFib(40);

	@Test
	public void test1() {
		long result = unit.solve();
		System.out.println(result);
	}

	@Test
	public void test2() {
		FibonacciTask task = new FibonacciTask(unit);
		ForkJoinPool pool = new ForkJoinPool(10);
		long time0 = System.currentTimeMillis();
		pool.invoke(task);
		long time1 = System.currentTimeMillis();
		long result = task.result;
		System.out.println("result:" + result + "  time: " + (time1-time0)/1000.0 );
	}
}
