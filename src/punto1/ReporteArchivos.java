package punto1;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

import punto1.archivoinfo.ArchivoInfoTask;

public class ReporteArchivos {

	
	public static void main(String[] args) {
		
		String path = "C:\\Users\\Alvain\\Documents\\EpiData\\AcademiaJava\\Tareas\\Concurrencia\\base";
		
		File archivo = new File(path);
		
		if(!archivo.exists()) {
			System.out.println("Path invalido");
			return;			
		}
		
		ArchivoInfoTask archivoInfoTask = new ArchivoInfoTask(archivo);
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		forkJoinPool.invoke(archivoInfoTask).stream().forEach(archivoInfo -> System.out.println(archivoInfo));
	}
	
}
