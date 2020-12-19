package punto2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
	
	public static void main(String[] args) {
		
		List<File> archivos = new ArrayList<File>();
		archivos.add(new File("C:\\Users\\Alvain\\Documents\\EpiData\\AcademiaJava\\Tareas\\Concurrencia\\archivo1.txt"));
		archivos.add(new File("C:\\Users\\Alvain\\Documents\\EpiData\\AcademiaJava\\Tareas\\Concurrencia\\archivo2.txt"));
		
		AnalizadorArchivos analizador = new AnalizadorArchivos(archivos);
		
		Map<String, Integer> resultado = analizador.analizar();
		
		for (String palabra : resultado.keySet()) {
			System.out.println(String.format("%s aparece %d veces",palabra,resultado.get(palabra)));
		}
		
	}

	
	
	
	
}
