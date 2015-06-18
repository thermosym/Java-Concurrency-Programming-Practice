package ym.practice.guava;

import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;


public class FuturePracticeTest {

	private FuturePractice unit;
	
	@Before
	public void setUp(){
		unit = new FuturePractice();
	}
	
	@Test
	public void testBasicFuture() throws Exception{
		unit.futureExample();
	}
	
	@Test
	public void testFutureLambdaExample() throws Exception{
		unit.futureLambdaExample();
	}
	
	@Test
	public void testFutureForkJoinExample() throws Exception{
		unit.futureForkJoinExample();;
	}
	
	@Test
	public void testListenableFuture(){
		unit.listenableFutureExample();
	}
	
	@Test
	public void testCompletableFuture(){
		try {
			unit.completableFutureExample();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
