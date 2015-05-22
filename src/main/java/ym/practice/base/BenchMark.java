package ym.practice.base;


public class BenchMark {

	public static void main(String[] args) {
		WordCounter counter = new WordCounter();
		
		long t0 = System.currentTimeMillis();
		WordCount wc = counter.count();
		long t1 = System.currentTimeMillis();
		double timeSeconds = (t1-t0)/1000.0;
		System.out.println( "time = " + timeSeconds );
	}

}
