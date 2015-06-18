package ym.practice.java8;

import org.junit.Test;


public class CompletableFuturePracticeTest {
	private CompletableFuturePractice unit = new CompletableFuturePractice();
	
	@Test
	public void test1(){
		unit.creation();
	}
	
	@Test
	public void test2(){
		unit.applyAnother();
	}
	
	@Test
	public void test3(){
		unit.applyMultipleAnother();
	}
	
	@Test
	public void test4(){
		unit.thenCompose();
	}
	
	@Test
	public void test5(){
		unit.combineAndEither();
	}
	
	@Test
	public void test6(){
		unit.parallelSteam();
	}
}
