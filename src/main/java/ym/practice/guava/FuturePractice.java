package ym.practice.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class FuturePractice {
	private static final int NUMBER_OF_THREADS = 5;
	
	public void futureExample() throws Exception {
	    ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
	 
	    Callable<String> asyncTask_1 = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "done 1";
			}
	    };
	    
	    Callable<String> asyncTask_2 = new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "done 2";
			}
	    };
	 
	    Future<String> future1 = executor.submit(asyncTask_1);
	    Future<String> future2 = executor.submit(asyncTask_2);
	 	
	    try {
	    	System.out.println(future2.get());
	        System.out.println(future1.get());
	    } catch (Exception ignore) {
	    	throw ignore;
	    } finally {
	    	executor.shutdown();	    	
	    }
	 
	}
	
	public void futureLambdaExample() throws Exception{
		ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS) ;
		Future<String> future = executor.submit(()->{
	    	Thread.sleep(1000);
			return "done";
	    });
		
	    try {
	    	System.out.println(future.get());
	    } catch (Exception ignore){
	    	throw ignore;
	    } finally{
	    	executor.shutdown();
	    }
	}
	
	public void futureForkJoinExample() throws Exception{
		ForkJoinTask<Integer> task = ForkJoinPool.commonPool().submit(()->{
			Thread.sleep(1000);
			return 10;
		});
		Thread.sleep(10);
		task.complete(1);
		System.out.println(task.get());
		task.complete(2);
		System.out.println(task.get());
		task.complete(3);
		System.out.println(task.get());
		Thread.sleep(2000);
		System.out.println(task.get());
	}
	
	public void listenableFutureExample() {
		ListeningExecutorService executor = MoreExecutors
				.listeningDecorator(Executors
						.newFixedThreadPool(NUMBER_OF_THREADS));
	    Callable<String> asyncTask = new Callable<String>() {
	        @Override
	        public String call() throws Exception {
	        	// do something
	        	return "ok";
	        }
	    };
	 
	    ListenableFuture<String> listenableFuture = executor.submit(asyncTask);
	 
	    Futures.addCallback(listenableFuture, new FutureCallback<String>() {
	        public void onSuccess(String result) {
	            System.out.println("Success callback: "+result);;
	        }
	 
	        public void onFailure(Throwable thrown) {
	        	System.out.println("Failure callback: "+ thrown.getMessage());;
	        }
	    });
	    
	    Futures.addCallback(listenableFuture, new FutureCallback<String>() {
	        public void onSuccess(String result) {
	            System.out.println("Success callback - more: "+result);;
	        }
	 
	        public void onFailure(Throwable thrown) {
	        	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	System.out.println("Failure callback - more: "+ thrown.getMessage());;
	        }
	    });
	 
	    try {
	        String result = listenableFuture.get();
	        System.out.println("After future:"+ result);
	    } catch (ExecutionException e) {
	    	System.err.println("Task failed");
	    } catch (InterruptedException e) {
	    	System.err.println("Task interrupted");
	    }
	 
	    executor.shutdown();
	}
	
	public void completableFutureExample() throws InterruptedException, ExecutionException{
		
		CompletableFuture<String> future = ask();
		future.complete("1");
		System.out.println(future.get());
		future.complete("2");
		System.out.println(future.get());
	}
	
	private CompletableFuture<String> ask() {
	    final CompletableFuture<String> future = new CompletableFuture<>();
	    //...
	    return future;
	}
}
