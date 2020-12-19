package punto2;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnalizadorArchivos{
	
	private List<File> archivos;
	
	private Map<String, Integer> palabrasAcumuladas;
	
	private int maxThreads=10; //aumentar para mayor paralelismo
	
	public AnalizadorArchivos(List<File> archivos) {
		this.archivos = archivos;
		this.palabrasAcumuladas = new HashMap<String, Integer>();
	}
	
	public Map<String, Integer> analizar() {
		
		ExecutorService executor = Executors.newFixedThreadPool(this.maxThreads);
		
		archivos.stream()
		.map(ContadorPalabras::new)
		.forEach(f -> {
			try {
				acumularPalabras(executor.submit(f).get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		executor.shutdown();
		
		return this.palabrasAcumuladas;
	}
	
	private void acumularPalabras(Map<String, Integer> palabrasContadas) {
		for (String palabra : palabrasContadas.keySet()) {
			if(palabrasAcumuladas.containsKey(palabra))
				palabrasAcumuladas.put(palabra, palabrasAcumuladas.get(palabra)+palabrasContadas.get(palabra));
			else
				palabrasAcumuladas.put(palabra,palabrasContadas.get(palabra));
		}
	}
	
}
