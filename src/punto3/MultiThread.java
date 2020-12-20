package punto3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.time.StopWatch;

public class MultiThread {

	public static void main(String[] args) {

		final int BATCH_SIZE = 9999999;
		
		StopWatch timer = new StopWatch();
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		AtomicInteger trialCount = new AtomicInteger();
		AtomicInteger inCircleCount = new AtomicInteger();
		
		System.out.println("Iniciando calculo de Pi MultiThread");
		timer.start();
		
		for(int i = 0; i < BATCH_SIZE; i++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					double x = Math.random();
				    double y = Math.random();
				    trialCount.addAndGet(1);
				    if (x*x + y*y < 1)
				        inCircleCount.addAndGet(1);	
				}
			});
		}
		
		double estimateForPi = 4 * ((double)inCircleCount.get() / trialCount.get());
		
		timer.stop();
		
		System.out.println("Calculo de Pi MultiThread finalizado en "+timer.getTime()+" ms");
		System.out.println("Valor aproximado de Pi: "+estimateForPi);
		
		/*
		 * El programa MultiThread termina siendo mas lento debido a la gran cantidad de overhead
		 */


	}

}
