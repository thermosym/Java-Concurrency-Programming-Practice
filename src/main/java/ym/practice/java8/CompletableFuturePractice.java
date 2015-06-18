package ym.practice.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import static java.util.concurrent.CompletableFuture.*;

public class CompletableFuturePractice {

	public void creation(){
		CompletableFuture<Integer> f1 = supplyAsync(()->{
			try {
				Thread.sleep(1000);
			} catch (Exception ignore) {}
			return 10;
		});
		//f1.complete(11);
		//f1.completeExceptionally(new Exception("bad"));
		try{
			int result = f1.join();
			System.out.println(result);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void applyAnother(){
		CompletableFuture<Integer> f1 = supplyAsync(()->{
			// ... do something else
			return 10;
		});
		CompletableFuture<Integer> f2 =  f1.thenApply((r)->r*r);
		//CompletableFuture<Integer> f2 =  f1.thenApply((r)->r*r, myExecutor);
		System.out.println(f2.join());
	}
	
	public void applyMultipleAnother(){
		CompletableFuture<Integer> f1 = supplyAsync(()->{
			// ... do something else
			return 10;
		});
		CompletableFuture<Integer> f2 =  f1.thenApply((r)->r*r);
		CompletableFuture<Integer> f3 =  f1.thenApply((r)->r*r+1);
		CompletableFuture<Integer> f4 =  f1.thenApply((r)->r*r+2);
		System.out.println(f2.join());
		System.out.println(f3.join());
		System.out.println(f4.join());
	}
	
	public void thenCompose(){
		CompletableFuture<Integer> f1 = supplyAsync(()->{
			// ... do something else
			return 10;
		});
		CompletableFuture<Integer> f2 =  f1.thenCompose((v)->
			supplyAsync(()-> {
				return v*v;
			}));
		System.out.println(f2.join());
	}
	
	public void combineAndEither(){
		CompletableFuture<Integer> f1 = supplyAsync(()->{ sleep(1000);return 10; });
		CompletableFuture<Integer> f2 = supplyAsync(()->{ sleep(200);return 5; });
		CompletableFuture<Integer> f3 = f1.thenCombine(f2, (r1,r2)-> r1+r2);
		CompletableFuture<Integer> f4 = f1.applyToEither(f2, (r)-> r*r);
		System.out.println(f3.join());
		System.out.println(f4.join());
	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void parallelSteam(){
		List<Integer> data = ImmutableList.of(1,2,3,4,5,6,7,8,9,10);
		List<Integer> result = 
				data.parallelStream()
					.unordered()
					.filter(n-> n>5)
					.map(n -> n*n)
					.collect(Collectors.toList());
		result.forEach(n->System.out.println(n));
	}
	
	public void sideEffect(){
		List<Integer> results = new ArrayList<>();
		List<Integer> data = ImmutableList.of(1,2,3,4,5,6,7,8,9,10);
		data.parallelStream()
			.filter(n-> n>5)
			.map(n -> n*n)
	        .forEach(n -> results.add(n));  // Unnecessary use of side-effects!
	}
}
