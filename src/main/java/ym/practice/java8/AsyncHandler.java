package ym.practice.java8;

import java.util.concurrent.CompletableFuture;

public interface AsyncHandler {
	CompletableFuture<String> handle(String request);
}
