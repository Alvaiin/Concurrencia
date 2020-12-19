package punto2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ContadorPalabras implements Callable<Map<String, Integer>> {

	private File archivo;
	private Map<String, Integer> cantidadPalabra;
	
	public ContadorPalabras(File archivo) {
		this.archivo = archivo;
		this.cantidadPalabra = new HashMap<String, Integer>();
	}
	
	@Override
	public Map<String, Integer> call() throws Exception {
		
		if(!archivo.canRead())
			return null;
		BufferedReader fr = new BufferedReader(new FileReader(archivo));
		String linea;
		while((linea = fr.readLine())!= null){
			for (String palabra : linea.split(" ")) {
				contarPalabra(palabra);
			}
		}
		
		fr.close();
		return cantidadPalabra;
	}
	
	private void contarPalabra(String palabra) {
		if(cantidadPalabra.containsKey(palabra))
			cantidadPalabra.put(palabra, cantidadPalabra.get(palabra)+1);
		else
			cantidadPalabra.put(palabra,1);
	}

	
	
}
