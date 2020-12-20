package punto3;

import org.apache.commons.lang3.time.StopWatch;

public class MonoThread {

	public static void main(String[] args) {

		final int BATCH_SIZE = 9999999;

		
		int trialCount = 0;
		int inCircleCount = 0;
		
		StopWatch timer = new StopWatch();
		
		System.out.println("Iniciando calculo de Pi MonoThread");
		timer.start();
		
		for (int i = 0; i < BATCH_SIZE; i++) {
		    double x = Math.random();
		    double y = Math.random();
		    trialCount++;
		    if (x*x + y*y < 1)
		        inCircleCount++;                        
		}
		
		double estimateForPi = 4 * ((double)inCircleCount / trialCount);
		
		timer.stop();
		
		System.out.println("Calculo de Pi MonoThread finalizado en "+timer.getTime()+" ms");
		System.out.println("Valor aproximado de Pi: "+estimateForPi);		

	}

}
